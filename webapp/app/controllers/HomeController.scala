package controllers

import java.time.Clock
import javax.inject._
import org.clulab.asist.agents.DialogAgent
import org.clulab.asist.messages.DialogAgentMessage
import org.clulab.asist.extraction.TomcatRuleEngine
import org.clulab.odin.{
  Attachment,
  EventMention,
  Mention,
  RelationMention,
  TextBoundMention
}
import org.clulab.processors.{Document, Sentence}
import org.clulab.utils.DisplayUtils
import play.api.mvc._
import play.api.libs.json._

import org.clulab.asist.messages._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write, writePretty}

import org.json4s.JField
import spray.json.DefaultJsonProtocol._
import spray.json.JsonParser

import scala.collection.immutable
import scala.io.Source
import scala.util.control.NonFatal

import buildinfo.BuildInfo


/** This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject() (cc: ControllerComponents)
    extends AbstractController(cc) {

  implicit val formats = DefaultFormats
  def writeJson[A <: AnyRef](a: A)(implicit formats: Formats): String = {
      writePretty(a)
  }

  // Initialize the TomcatRuleEngine
  // -------------------------------------------------
  println("[TomcatRuleEngine] Initializing the TomcatRuleEngine ...")
  val engine = new TomcatRuleEngine()
  val agent = new DialogAgent(engine)
  println("[TomcatRuleEngine] Completed initialization ...")
  // -------------------------------------------------

  type Trigger = String

  /** Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index(): Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.index())
  }

  // FIXME
  def getEntityLinkerEvents(
      mentions: Seq[Mention]
  ): Seq[(Trigger, Map[String, String])] = {
    val events = mentions.filter(_ matches "Event")
    val entityLinkingEvents = events.map { e =>
      val event = e.asInstanceOf[EventMention]
      val trigger = event.trigger.text
      val arguments = event.arguments.map { a =>
        val name = a._1
        val arg_mentions = a._2.map(_.text).mkString(" ")
        (name, arg_mentions)
      }
      (trigger, arguments)
    }

    entityLinkingEvents
  }

  def processPlaySentence(
      engine: TomcatRuleEngine,
      text: String
  ): (Document, Seq[Mention], Seq[(Trigger, Map[String, String])], String) = {
    // Preprocessing
    println(s"Processing sentence : ${text}")
    val doc = engine.annotate(text)


    println(s"DOC : ${doc}")
    // Extract mentions from annotated document
    val mentions = agent.extractMentions(doc) 
    val messageData = DialogAgentMessageData(
      participant_id = "P00012",
      asr_msg_id = "bc36d1aa-25e6-11ec-ab58-7831c1b845fe",
      text = text,
      dialog_act_label = "N/A",
      utterance_source = DialogAgentMessageUtteranceSource(
        source_type = "message_bus",
        source_name = "agent/asr/final"
      ),
      extractions = agent.getExtractions(mentions)
    )
    val timestamp = Clock.systemUTC.instant.toString
    val message = DialogAgentMessage(
      CommonHeader(timestamp, DialogAgentMessage.header.message_type),
      CommonMsg(
        experiment_id = "367624f8-81cd-4661-a03f-b61908c39581",
        trial_id = "78822ceb-448a-436e-a1f1-f154f2066261",
        timestamp = timestamp,
        source = "tomcat_textAnalyzer",
        sub_type = "Event:dialogue_event",
        version = BuildInfo.version,
        replay_root_id = "",
        replay_id = "",
      ),
      messageData
      )

    val extractionJsons = write(message)
    println(s"Done extracting the mentions ... ")
    println(s"They are : ${mentions.map(m => m.text).mkString(",\t")}")

    println(s"Getting entity linking events ... ")
    val events = getEntityLinkerEvents(mentions)

    println("DONE .... ")
    // return the sentence and all the mentions extracted ... TODO: fix it to process all the sentences in the doc
    (doc, mentions.sortBy(_.start), events, extractionJsons)
  }

  def parseSentence(text: String) = Action {
    val (doc, mentions, events, extractionJsons) = processPlaySentence(engine, text)
    println(
      s"Sentence returned from processPlaySentence : ${doc.sentences.head.getSentenceText}"
    )
    val json = mkJson(
      text,
      doc,
      mentions,
      events,
    ) // We only handle a single sentence

    val updatedJson = json.as[JsObject]  ++ Json.obj("extractionJsons" -> extractionJsons)

    // Return JSON
    Ok(updatedJson)
  }

  protected def mkParseObj(sentence: Sentence, sb: StringBuilder): Unit = {
    def getTdAt(option: Option[Array[String]], n: Int): String = {
      val text =
        if (option.isEmpty) ""
        else option.get(n)

        "<td>" + xml.Utility.escape(text) + "</td>"
    }

    sentence.words.indices.foreach { i =>
      sb
        .append("<tr>")
        .append("<td>" + xml.Utility.escape(sentence.words(i)) + "</td>")
        .append(getTdAt(sentence.tags, i))
        .append(getTdAt(sentence.lemmas, i))
        .append(getTdAt(sentence.entities, i))
        .append(getTdAt(sentence.norms, i))
        .append(getTdAt(sentence.chunks, i))
        .append("</tr>")
    }
  }

  protected def mkParseObj(doc: Document): String = {
    val header =
      """
        <h2>Parse:</h2>
        |  <tr>
        |    <th>Word</th>
        |    <th>Tag</th>
        |    <th>Lemma</th>
        |    <th>Entity</th>
        |    <th>Norm</th>
        |    <th>Chunk</th>
        |  </tr>
      """.stripMargin
    val sb = new StringBuilder(header)

    doc.sentences.foreach(mkParseObj(_, sb))
    sb.toString
  }

  def mkJson(
      text: String,
      doc: Document,
      mentions: Seq[Mention],
      events: Seq[(String, Map[String, String])],
  ): JsValue = {
    println("Found mentions (in mkJson):")
    mentions.foreach(DisplayUtils.displayMention)

    val sent = doc.sentences.head
    val syntaxJsonObj = Json.obj(
      "text" -> text,
      "entities" -> mkJsonFromTokens(doc),
      "relations" -> mkJsonFromDependencies(doc)
    )
    val mentionsJsonObj = mkMentionsJsonObj(text, sent, mentions)
    val mentionsDetails = mkMentionDetailTextDisplay(mentions, events)
    val parseObj = mkParseObj(doc)

    Json.obj(
      "syntax" -> syntaxJsonObj,
      "mentions" -> mentionsJsonObj,
      "mentionDetails" -> mentionsDetails,
      "parse" -> parseObj,
    )
  }

  def mkMentionDetailTextDisplay(
      mentions: Seq[Mention],
      events: Seq[(String, Map[String, String])]
  ): String = {
    var objectToReturn = ""

    // Mention display format
    if (mentions.nonEmpty) {
      objectToReturn += "<h2>Extractions:</h2>"
      for (m <- mentions) {
        objectToReturn += s"${DisplayUtils.webAppMention(m)}"
      }
    }

    objectToReturn += "<br>"
    objectToReturn
  }

  def mkMentionsJsonObj(
      sentenceText: String,
      sent: Sentence,
      mentions: Seq[Mention]
  ): Json.JsValueWrapper = {
    val topLevelTBM = mentions.flatMap {
      case m: TextBoundMention => Some(m)
      // FIXME: real brat solution for the display
      case r: RelationMention =>
        Some(
          new TextBoundMention(
            r.labels,
            r.tokenInterval,
            r.sentence,
            r.document,
            r.keep,
            r.foundBy
          )
        )
      case _ => None
    }
    // Collect event mentions for display
    val events = mentions.flatMap {
      case m: EventMention => Some(m)
      case _               => None
    }
    // Collect triggers for event mentions
    val triggers = events.flatMap { e =>
      val argTriggers = for {
        a <- e.arguments.values
        if a.isInstanceOf[EventMention]
      } yield a.asInstanceOf[EventMention].trigger
      e.trigger +: argTriggers.toSeq
    }
    // collect event arguments as text bound mentions
    val entities = for {
      e <- events
      a <- e.arguments.values.flatten
    } yield a match {
      case m: TextBoundMention => m
      case m: RelationMention =>
        new TextBoundMention(
          m.labels,
          m.tokenInterval,
          m.sentence,
          m.document,
          m.keep,
          m.foundBy
        )
      case m: EventMention => m.trigger
    }
    // generate id for each textbound mention
    val tbMentionToId =
      (entities ++ triggers ++ topLevelTBM).distinct.zipWithIndex.map {
        case (m, i) => (m, i + 1)
      }.toMap
    // return brat output
    Json.obj(
      "text" -> sentenceText,
      "entities" -> mkJsonFromEntities(entities ++ topLevelTBM, tbMentionToId),
      "triggers" -> mkJsonFromEntities(triggers, tbMentionToId),
      "events" -> mkJsonFromEventMentions(events, tbMentionToId)
    )
  }

  def mkJsonFromEntities(
      mentions: Seq[TextBoundMention],
      tbmToId: Map[TextBoundMention, Int]
  ): Json.JsValueWrapper = {
    val entities = mentions.map(m => mkJsonFromTextBoundMention(m, tbmToId(m)))
    Json.arr(entities: _*)
  }

  def mkJsonFromTextBoundMention(
      m: TextBoundMention,
      i: Int
  ): Json.JsValueWrapper = {
    Json.arr(
      s"T$i",
      HomeController.statefulRepresentation(m).label,
      Json.arr(Json.arr(m.startOffset, m.endOffset))
    )
  }

  def mkJsonFromEventMentions(
      ee: Seq[EventMention],
      tbmToId: Map[TextBoundMention, Int]
  ): Json.JsValueWrapper = {
    var i = 0
    val jsonEvents = for (e <- ee) yield {
      i += 1
      mkJsonFromEventMention(e, i, tbmToId)
    }
    Json.arr(jsonEvents: _*)
  }

  def mkJsonFromEventMention(
      ev: EventMention,
      i: Int,
      tbmToId: Map[TextBoundMention, Int]
  ): Json.JsValueWrapper = {
    Json.arr(
      s"E$i",
      s"T${tbmToId(ev.trigger)}",
      Json.arr(mkArgMentions(ev, tbmToId): _*)
    )
  }

  def mkArgMentions(
      ev: EventMention,
      tbmToId: Map[TextBoundMention, Int]
  ): Seq[Json.JsValueWrapper] = {
    val args = for {
      argRole <- ev.arguments.keys
      m <- ev.arguments(argRole)
    } yield {
      val arg = m match {
        case m: TextBoundMention => m
        case m: RelationMention =>
          new TextBoundMention(
            m.labels,
            m.tokenInterval,
            m.sentence,
            m.document,
            m.keep,
            m.foundBy
          )
        case m: EventMention => m.trigger
      }
      mkArgMention(argRole, s"T${tbmToId(arg)}")
    }
    args.toSeq
  }

  def mkArgMention(argRole: String, id: String): Json.JsValueWrapper = {
    Json.arr(argRole, id)
  }

  def mkJsonFromTokens(doc: Document): Json.JsValueWrapper = {
    var offset = 0

    val tokens = doc.sentences.flatMap { sent =>
      val tokens = sent.words.indices.map(i => mkJsonFromToken(sent, offset, i))
      offset += sent.words.size
      tokens
    }
    Json.arr(tokens: _*)
  }

  def mkJsonFromToken(
      sent: Sentence,
      offset: Int,
      i: Int
  ): Json.JsValueWrapper = {
    Json.arr(
      s"T${offset + i + 1}", // token id (starts at one, not zero)
      sent.tags.get(i), // lets assume that tags are always available
      Json.arr(Json.arr(sent.startOffsets(i), sent.endOffsets(i)))
    )
  }

  def mkJsonFromDependencies(doc: Document): Json.JsValueWrapper = {
    var offset = 1

    val rels = doc.sentences.flatMap { sent =>
      var relId = 0
      val deps =
        sent.dependencies.get // lets assume that dependencies are always available
      val rels = for {
        governor <- deps.outgoingEdges.indices
        (dependent, label) <- deps.outgoingEdges(governor)
      } yield {
        val json = mkJsonFromDependency(
          offset + relId,
          offset + governor,
          offset + dependent,
          label
        )
        relId += 1
        json
      }
      offset += sent.words.size
      rels
    }
    Json.arr(rels: _*)
  }

  def mkJsonFromDependency(
      relId: Int,
      governor: Int,
      dependent: Int,
      label: String
  ): Json.JsValueWrapper = {
    Json.arr(
      s"R$relId",
      label,
      Json.arr(
        Json.arr("governor", s"T$governor"),
        Json.arr("dependent", s"T$dependent")
      )
    )
  }

  def tab(): String = "&nbsp;&nbsp;&nbsp;&nbsp;"
}

object HomeController {

  // fixme: ordering/precedence...
  def statefulRepresentation(m: Mention): Mention = {
    val stateAffix = ""

    // If you found something, append the affix to top label and add to the Seq of labels
    if (stateAffix.nonEmpty) {
      val modifiedLabels = Seq(m.label ++ stateAffix) ++ m.labels
      val out = m match {
        case tb: TextBoundMention =>
          m.asInstanceOf[TextBoundMention].copy(labels = modifiedLabels)
        case rm: RelationMention =>
          m.asInstanceOf[RelationMention].copy(labels = modifiedLabels)
        case em: EventMention => em.copy(labels = modifiedLabels)
      }

      return out
    }

    // otherwise, return original
    m
  }

}
