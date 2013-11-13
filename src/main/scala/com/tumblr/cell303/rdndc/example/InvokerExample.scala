package com.tumblr.cell303.rdndc.example

import scala.annotation.tailrec
import com.tumblr.cell303.rdndc.invoker.{rdndc, RandomInvoker}

object InvokerExample {

  def main(args: Array[String]) = {
    for (i <- 1 to 100) {
      println(format("123"))
    }
  }

  val invoker = new RandomInvoker(this)

  /**
   * Formats a zip code to be 5 digits long
   *
   * @param digits A US zip code that has at least 3 digits
   * @return A 5 digit zip code prepended with 0s if necessary
   */
  def format(digits: String): String = {
    invoker.invoke("format", digits).asInstanceOf[String]
  }

  @rdndc("format")
  def formatAgnostic(digits: String) = digits.size match {
    case 4 => "0" + digits
    case 3 => "00" + digits
  }

  @rdndc("format")
  def formatAscetic(digits: String) = {
    @tailrec
    def helper(s: List[Char]): String =  {
      if (s.size >= 5) s.mkString
      else helper('0' :: s)
    }
    helper(digits.toList)
  }

  @rdndc("format")
  def formatLibrarian(digits: String) = "%05d".format(digits.toInt)

  @rdndc("format")
  def formatWTF(digits: String) = BaseZipCodeDigits(digits).toFormattedString

  @rdndc("format")
  def faultyImplementation(digits: String) = throw new RuntimeException()
}
