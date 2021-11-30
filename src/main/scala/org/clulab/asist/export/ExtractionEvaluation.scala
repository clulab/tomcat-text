package org.clulab.asist.`export`

import java.io.File
import java.util.Calendar

import ai.lum.common.StringUtils._
import org.clulab.odin.{EventMention, Mention}
import org.clulab.struct.Counter
import org.clulab.utils.FileUtils
import org.clulab.utils.Closer._
import org.clulab.utils.DisplayUtils.shortDisplay

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

    val timestamp = Calendar.getInstance.getTime

    // Annotation sheet
    FileUtils.printWriterFromFile(s"$outputDir/rule_annotation.csv").autoClose { csvWriter1 =>
      // Summary sheet
      FileUtils.printWriterFromFile(s"$outputDir/rule_summary.csv").autoClose { csvWriter2 =>
        // Strict eval summary sheet
        FileUtils.printWriterFromFile(s"$outputDir/rule_summary_strict.csv").autoClose { csvWriter3 =>

          // Sheet 1 -- Extraction Data
          csvWriter1.println(s"ToMCAT-text Rule Annotation -- generated $timestamp")
          csvWriter1.println(header1)
          rows.foreach(csvWriter1.println)
          // Sheet 2 -- Summary Statistics
          csvWriter2.println(header2)
          val summaryRows = counterToRows(ruleCounter, correctColumn = "G", incorrectColumn = "N")
          summaryRows.foreach(csvWriter2.println)
          // Sheet 3 -- Strict eval summary statistics
          csvWriter3.println(header2)
          val summaryRowsStrict = counterToRows(ruleCounter, correctColumn = "O", incorrectColumn = "P")
          summaryRowsStrict.foreach(csvWriter3.println)
        }
      }
    }
  }

  def getCSVRows(mentions: Seq[Mention]): Seq[String] = {
    // note: we escape strings for CSV
    for {
      (mention, i) <- mentions.zipWithIndex
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
          case (name, argMentions) => s"ARG($name): ${argMentions.map(shortDisplay).mkString("; ")}"
        }
        texts.mkString("\n").escapeCsv
      }

      // Text representation of attachments
      attachmentText = mention.attachments.map(_.toString).mkString("\n").escapeCsv

      // sentence text
      evidence = mention.sentenceObj.getSentenceText.normalizeSpace.escapeCsv

      // Column N -- the tallying of the strict score from the mention and args annotation
      rowIdx = i + 3
      incorrect = s"""=IF(ISBLANK(G${rowIdx}),0,INT(NE(G${rowIdx},1)))""".escapeCsv
      strictCorrect = s"""=IF(ISBLANK(G$rowIdx),"",IF(ISBLANK(H$rowIdx),INT(EQ(G$rowIdx,1)), INT(AND(G$rowIdx=1,H$rowIdx=1))))""".escapeCsv
      strictIncorrect = s"""=IF(ISBLANK(G${rowIdx}),0,IF(ISBLANK(H$rowIdx),INT(NE(G$rowIdx,1)), INT(NE(G$rowIdx+H$rowIdx,2))))""".escapeCsv

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
      mention.foundBy.escapeCsv,
      incorrect,
      strictCorrect,
      strictIncorrect,
    ).mkString(",")
  }

  // Sorted...
  // strict the correctColumn = N
  def counterToRows(ruleCounter: Counter[String], ruleColumn: String = "M", correctColumn: String = "G", incorrectColumn: String = "N"): Seq[String] = {
    val total = ruleCounter.getTotal
    val rows = ruleCounter.toSeq
      .sortBy(- _._2)
      .zipWithIndex
      .map(ruleInfo => ruleRow(ruleInfo._1._1, ruleInfo._1._2, total, ruleInfo._2, ruleColumn, correctColumn, incorrectColumn))
    (rows :+ Seq("Grand Total", total.toString)).map(_.mkString(","))
  }

  def ruleRow(rule: String, count: Double, total: Double, i: Int, ruleColumn: String, correctColumn: String, incorrectColumn: String): Seq[String] = {
    val j = i + 2 // account for the header and 1-indexing of google sheets
    val percAll = count / total
    val numCorrect = "=SUMIF(rule_annotation!$" +
      ruleColumn + ":$" + ruleColumn + ",A" + j +
      ",rule_annotation!$" + correctColumn + ":$" +
      correctColumn + ")"
    val numIncorrect = "=SUMIF(rule_annotation!$" +
      ruleColumn + ":$" + ruleColumn + ",A" + j +
      ",rule_annotation!$" + incorrectColumn + ":$" +
      incorrectColumn + ")"
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
    ).map(_.escapeCsv)
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
      "Rule", // M: ruleColumn
      "Incorrect", // N: 1 if incorrect, 0 if correct
      "Strict Correct", // O: if args there, both that and mentions score must be 1
      "Strict Incorrect" // P: if args there, both that and mentions score must be 1
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
