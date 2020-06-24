package org.clulab.sentiment

import java.io.PrintWriter

import org.clulab.processors.clu.CluProcessor
import org.clulab.sequences.LexiconNER
import org.clulab.struct.Counter
import org.slf4j.{Logger, LoggerFactory}

import scala.io.Source
import LexiconSentimentAnalyzer._
import org.clulab.processors.Processor
import org.clulab.utils.MathUtils

import scala.collection.mutable.ArrayBuffer

class LexiconSentimentAnalyzer {
  val lexiconNer: LexiconNER = mkLexicon()
  val proc: Processor = new CluProcessor()

  private def mkLexicon(): LexiconNER = {
    // Bing Liu's words are case insensitive, so we must have caseInsensitiveMatching = true
    val lex = LexiconNER(
      List(s"org/clulab/sentiment/$POSITIVE_LABEL.txt", s"org/clulab/sentiment/$NEGATIVE_LABEL.txt"),
      useLemmasForMatching = false, caseInsensitiveMatching = true)

    lex
  }

  def annotate(text: String): TextStats = {
    val doc = proc.mkDocument(text)

    val counts = new Counter[String]()
    val distribution = new Counter[String]()

    for(sentence <- doc.sentences) {
      val labels = lexiconNer.find(sentence)
      // println(labels.mkString(", "))
      for(label <- labels) {
        if(label.startsWith("B-")) {
          counts.incrementCount(label.substring(2))
        }
      }
    }

    if(! counts.contains(POSITIVE_LABEL)) {
      counts.setCount(POSITIVE_LABEL, 0)
    }
    if(! counts.contains(NEGATIVE_LABEL)) {
      counts.setCount(NEGATIVE_LABEL, 0)
    }

    if(counts.getCount(NEGATIVE_LABEL) == 0 && counts.getCount(POSITIVE_LABEL) == 0) {
      distribution.setCount(POSITIVE_LABEL, 0.5)
      distribution.setCount(NEGATIVE_LABEL, 0.5)
    } else if(counts.getCount(NEGATIVE_LABEL) == 0) {
      distribution.setCount(POSITIVE_LABEL, 1.0)
      distribution.setCount(NEGATIVE_LABEL, 0.0)
    } else if(counts.getCount(POSITIVE_LABEL) == 0) {
      distribution.setCount(POSITIVE_LABEL, 0.0)
      distribution.setCount(NEGATIVE_LABEL, 1.0)
    } else {
      val in = new ArrayBuffer[Double]()
      in += counts.getCount(POSITIVE_LABEL)
      in += counts.getCount(NEGATIVE_LABEL)
      val dist = MathUtils.softmax(in)
      distribution.setCount(POSITIVE_LABEL, dist.head)
      distribution.setCount(NEGATIVE_LABEL, dist.last)
    }

    TextStats(counts, distribution)
  }
}

case class TextStats(counts: Counter[String],
                     distribution: Counter[String]) {
  override def toString: String = {
    val labels = counts.keySet.toList.sorted
    val builder = new StringBuilder

    for(label <- labels) {
      builder.append(counts.getCount(label).toInt)
      builder.append(" ")
    }

    for(label <- labels) {
      builder.append(distribution.getCount(label))
      builder.append(" ")
    }

    builder.toString()
  }
}

object LexiconSentimentAnalyzer {
  val logger: Logger = LoggerFactory.getLogger(classOf[LexiconSentimentAnalyzer])

  val POSITIVE_LABEL = "positive"
  val NEGATIVE_LABEL = "negative"

  def main(args: Array[String]): Unit = {
    if(args.length != 2) {
      logger.error("Usage: org.clulab.sentiment.LexiconSentimentAnalyzer <input-file-with-one-sentence-per-line> <output-file>")
      System.exit(1)
    }

    val analyzer = new LexiconSentimentAnalyzer()

    val inputFile = args(0)
    val outputFile = args(1)

    val in = Source.fromFile(inputFile)
    val out = new PrintWriter(outputFile)

    for(line <- in.getLines()) {
      val stats = analyzer.annotate(line)
      out.println(stats)
    }

    in.close()
    out.close()
  }
}
