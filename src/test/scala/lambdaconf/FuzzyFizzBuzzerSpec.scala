package lambdaconf

import org.scalacheck._
import org.specs2.mutable.Specification
import org.specs2.ScalaCheck

class FuzzyFizzBuzzerSpec extends Specification with ScalaCheck {
  "FuzzyFizzBuzzer" should {
    "match the reference impl for Ints" in check {
      i: Int => FuzzyFizzBuzzer.compute(i) mustEqual reference(i)
    }

    implicit val arbAny = Arbitrary(Gen.oneOf(Gen.posNum[Int], Gen.posNum[Double], Gen.alphaStr, Gen.posNum[Double].map {
      dbl => dbl.toString
    }))

    "match the reference impl for computeAny" in check {
      a: Any => FuzzyFizzBuzzer.computeAny(a) mustEqual reference(a)
    }
  }

  def reference(a: Any): String = if (a.isInstanceOf[Int]) {
    val i = a.asInstanceOf[Int]
    if (i % 3 == 0 && i % 5 == 0) {
      "FizzBuzz"
    } else if (i % 3 == 0) {
      "Fizz"
    } else if (i % 5 == 0) {
      "Buzz"
    } else i.toString
  } else if (a.isInstanceOf[Double] && a.asInstanceOf[Double].isValidInt) {
    reference(a.asInstanceOf[Double].toInt)
  } else if (a.isInstanceOf[String] && FuzzyFizzBuzzer.isDouble(a.asInstanceOf[String])) {
    reference(a.asInstanceOf[String].toDouble)
  } else "%s".format(a)
}
