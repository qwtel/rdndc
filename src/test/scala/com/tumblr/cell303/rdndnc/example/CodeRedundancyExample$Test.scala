package com.tumblr.cell303.rdndnc.example

import org.scalatest.{Matchers, FlatSpec}

class CodeRedundancyExample$Test extends FlatSpec with Matchers {

  "A format implementation" should "format a 3 digit zip code" in {
    CodeRedundancyExample.format("123") should be ("00123")
  }

  it should "format a 4 digit zip code" in {
    CodeRedundancyExample.format("1234") should be ("01234")
  }

  "A faulty implementation" should "throw an exception" in {
    a [RuntimeException] should be thrownBy {
      CodeRedundancyExample.faultyImplementation("123")
    }
  }
}
