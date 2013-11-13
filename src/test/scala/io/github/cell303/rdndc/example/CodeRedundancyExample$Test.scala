package io.github.cell303.rdndc.example

import org.scalatest.{Matchers, FlatSpec}
import io.github.cell303.rdndc.util.RedundancyException

class CodeRedundancyExample$Test extends FlatSpec with Matchers {

  "A format implementation" should "format a 3 digit zip code" in {
    InvokerExample.format("123") should be ("00123")
  }

  it should "format a 4 digit zip code" in {
    InvokerExample.format("1234") should be ("01234")
  }

  "A faulty implementation" should "throw an exception" in {
    a [RedundancyException] should be thrownBy {
      InvokerExample.faultyImplementation("123")
    }
  }
}
