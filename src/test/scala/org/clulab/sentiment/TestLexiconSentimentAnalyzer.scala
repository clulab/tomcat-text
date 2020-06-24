package org.clulab.sentiment

import org.scalatest.{FlatSpec, Matchers}

import LexiconSentimentAnalyzer._

class TestLexiconSentimentAnalyzer extends FlatSpec with Matchers {
  val analyzer = new LexiconSentimentAnalyzer

  "The sentiment analyzer" should "analyze positive sentences correctly" in {
    val stats = analyzer.annotate("I am happy.")

    stats.counts.getCount(POSITIVE_LABEL).toInt should be (1)
    stats.counts.getCount(NEGATIVE_LABEL).toInt should be (0)
  }

  it should "analyze negative sentences correctly" in {
    val stats = analyzer.annotate("This is a BAD movie.")

    stats.counts.getCount(POSITIVE_LABEL).toInt should be (0)
    stats.counts.getCount(NEGATIVE_LABEL).toInt should be (1)
  }

  it should "analyze mixed sentences correctly" in {
    val stats = analyzer.annotate("This is a bad movie but with a nice and happy ending.")

    stats.counts.getCount(POSITIVE_LABEL).toInt should be (2)
    stats.counts.getCount(NEGATIVE_LABEL).toInt should be (1)
  }

  it should "analyze neutral sentences correctly" in {
    val stats = analyzer.annotate("A neutral sentence.")

    stats.counts.getCount(POSITIVE_LABEL).toInt should be (0)
    stats.counts.getCount(NEGATIVE_LABEL).toInt should be (0)
  }
}
