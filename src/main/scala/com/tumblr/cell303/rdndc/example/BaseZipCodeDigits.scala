package com.tumblr.cell303.rdndc.example

trait BaseZipCodeDigits {
  def toFormattedString: String
}

class ThreeZipCodeDigits(digits: String) extends BaseZipCodeDigits {
  def toFormattedString = s"00$digits"
}

class FourZipCodeDigits(digits: String) extends BaseZipCodeDigits {
  def toFormattedString = s"0$digits"
}

class FiveZipCodeDigits(digits: String) extends BaseZipCodeDigits {
  def toFormattedString = digits
}

object BaseZipCodeDigits {
  def apply(digits: String) = {
    if (digits.length == 3) new ThreeZipCodeDigits(digits)
    else if (digits.length == 4) new FourZipCodeDigits(digits)
    else new FiveZipCodeDigits(digits)
  }
}
