package lambdaconf

import org.specs2.mutable.Specification

class RPNCalcSpec extends Specification {
  "RPNCalc" should {
    "throw an exception on empty input" in {
      RPNCalc.calculate(Nil) must throwAn[InvalidComputationException]
    }

    "handle basic addition" in {
      RPNCalc.calculate(List(Push(1), Push(1), Add)) mustEqual 2
    }

    "handle basic subtraction" in {
      RPNCalc.calculate(List(Push(20), Push(15), Subtract)) mustEqual 5
    }

    "handle basic division" in {
      RPNCalc.calculate(List(Push(8), Push(2), Divide)) mustEqual 4
    }

    "handle basic multiplication" in {
      RPNCalc.calculate(List(Push(6), Push(7), Multiply)) mustEqual 42
    }

    "reject invalid input" in {
      RPNCalc.calculate(List(Push(6), Push(7), Multiply, Divide)) must throwAn[InvalidComputationException]
    }

    "handle a more complex sequence" in {
      // 5 1 2 + 4 * + 3 -
      RPNCalc.calculate(List(Push(5), Push(1), Push(2), Add, Push(4), Multiply, Add, Push(3), Subtract)) mustEqual 14
    }
  }
}
