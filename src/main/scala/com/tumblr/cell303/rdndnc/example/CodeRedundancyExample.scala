package com.tumblr.cell303.rdndnc.example

import scala.annotation.tailrec
import com.tumblr.cell303.rdndnc.method.{rdndnc, RandomInvoker}

object CodeRedundancyExample {

  val randomInvoker = new RandomInvoker(this)

  def main(args: Array[String]) = {
    for (i <- 1 to 100) {
      println(format("123"))
    }
  }

  /**
   * Formats a zip code to be 5 digits long
   *
   * @param digits A US zip code that has at least 3 digits
   * @return A 5 digit zip code prepended with 0s if necessary
   */
  def format(digits: String): String = {
    randomInvoker.apply("format", digits).asInstanceOf[String]
  }

  @rdndnc("format")
  def formatAgnostic(digits: String) = digits.size match {
    case 4 => "0" + digits
    case 3 => "00" + digits
  }

  @rdndnc("format")
  def formatAscetic(digits: String) = {
    @tailrec
    def helper(s: List[Char]): String =  {
      if (s.size >= 5) s.mkString
      else helper('0' :: s)
    }
    helper(digits.toList)
  }

  @rdndnc("format")
  def formatLibrarian(digits: String) = "%05d".format(digits.toInt)

  @rdndnc("format")
  def formatWTF(digits: String) = BaseZipCodeDigits(digits).toFormattedString

  @rdndnc("format")
  def faultyImplementation(digits: String) = throw new RuntimeException()
}
