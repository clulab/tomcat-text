package org.clulab.asist.`export`

import java.io.File
import java.util.Calendar

import ai.lum.common.StringUtils._
import org.clulab.odin.{EventMention, Mention}
import org.clulab.struct.Counter
import org.clulab.utils.FileUtils
import org.clulab.utils.Closer._

object ExtractionEvaluation {

  def exportExtractionAnnotationSheets(mentions: Seq[Mention], outputDir: String): Unit = {
    exportExtractionAnnotationSheets(mentions, new File(outputDir))
  }
  def exportExtractionAnnotationSheets(mentions: Seq[Mention], outputDir: File): Unit = {
    // check dir exists
    if (!outputDir.exists()) outputDir.mkdirs()


    // tally the rules in the found mentions
    val ruleCounter = new Counter[String]
    mentions.foreach(m => ruleCounter.incrementCount(m.foundBy))

    // Generate the rows for the mentions
    val rows = getCSVRows(mentions)
    val shuffledRows = scala.util.Random.shuffle(rows)

    val timestamp = Calendar.getInstance.getTime

    FileUtils.printWriterFromFile(s"$outputDir/rule_annotation.tsv").autoClose { csvWriter1 =>
      FileUtils.printWriterFromFile(s"$outputDir/rule_summary.tsv").autoClose { csvWriter2 =>

        // Sheet 1 -- Extraction Data
        csvWriter1.println(s"ToMCAT-text Rule Annotation -- generated $timestamp")
        csvWriter1.println(header1)
        shuffledRows.foreach(csvWriter1.println)
        // Sheet 2 -- Summary Statistics
        csvWriter2.println(header2)
        val summaryRows = counterToRows(ruleCounter)
        summaryRows.foreach(csvWriter2.println)
      }
    }

  }

  def getCSVRows(mentions: Seq[Mention]): Seq[String] = {
    // note: we escape strings for CSV
    for {
      mention <- mentions
      sentenceId = mention.sentence
      docID = mention.document.id.getOrElse("NONE").escapeCsv

      // mention label
      label = mention.label.escapeCsv

      // mention text
      text = mention.text.escapeCsv

      // triggerOpt
      trigger = mention match {
        case em: EventMention => em.trigger.text.escapeCsv
        case _ => ""
      }

      // Text representation of arguments
      argText = {
        val texts = mention.arguments.map {
          case (name, argMentions) => s"ARG($name): ${argMentions.map(_.text).mkString("; ")}"
        }
        texts.mkString("\n").escapeCsv
      }

      // Text representation of attachments
      attachmentText = mention.attachments.map(_.toString).mkString("\n").escapeCsv

      // sentence text
      evidence = mention.sentenceObj.getSentenceText.normalizeSpace.escapeCsv

    } yield Seq(
      docID,
      sentenceId.toString,
      label,
      text,
      argText,
      attachmentText,
      "", // mention score
      "", // args score
      "", // annotator
      evidence,
      "", // comments
      trigger,
      mention.foundBy.escapeCsv
    ).mkString(",")
  }

  // Sorted...
  def counterToRows(ruleCounter: Counter[String], ruleColumn: String = "M", correctColumn: String = "G"): Seq[Seq[String]] = {
    val total = ruleCounter.getTotal
    val rows = ruleCounter.toSeq
      .sortBy(- _._2)
      .zipWithIndex
      .map(ruleInfo => ruleRow(ruleInfo._1._1, ruleInfo._1._2, total, ruleInfo._2, ruleColumn, correctColumn))
    rows :+ Seq("Grand Total", total.toString)
  }

  def ruleRow(rule: String, count: Double, total: Double, i: Int, ruleColumn: String, correctColumn: String): Seq[String] = {
    val j = i + 2 // account for the header and 1-indexing of google sheets
    val percAll = count / total
    val numCorrect = "=SUMIF(rule_annotation!$" +
      ruleColumn + ":$" + ruleColumn + ",A" + j +
      ",rule_annotation!$" + correctColumn + ":$" +
      correctColumn + ")"
    val numIncorrect = s"=COUNTIFS(rule_annotation!${ruleColumn}"+"$3:"+ s"${ruleColumn},A$j,rule_annotation!${correctColumn}" + "$3:" + s"${correctColumn}," + """"<>1")"""
    val percCorr = s"=IF(D$j+E$j>0, D$j/(D$j+E$j), " + """"")"""
    val percCurated = s"=(D$j+E$j)/B$j"

    Seq(
      rule,
      count.toString,
      percAll.toString,
      numCorrect,
      numIncorrect,
      percCorr,
      percCurated
    )
  }

  // FIXME
  val header1 = List(
      "DocID",
      "Sentence ID",
      "Label",
      "Mention text (full)",
      "Arguments",
      "Attachments",
      "Mention Score", // G: correctColumn
      "Arguments Score",
      "Annotator",
      "Evidence",
      "Comments",
      "Trigger (if applicable)",
      "Rule" // M: ruleColumn
    ).mkString(",")

  val header2 = List(
    "RULE",
    "COUNT of RULE",
    "% of all",
    "Num correct",
    "Num incorrect",
    "% correct",
    "% curated"
  ).mkString(",")

}
