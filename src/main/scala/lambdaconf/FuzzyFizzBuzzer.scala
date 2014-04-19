package lambdaconf

/**
 * This is a variation on the fizz buzz game. The basic rules are:
 *
 * - If a number is evenly divisible by 3, return "Fizz"
 * - If a number is evenly divisible by 5, return "Buzz"
 * - If a number is evenly divisible by both 3 and 5, return "FizzBuzz"
 * - Otherwise just return the string representation of the number
 *
 * This logic needs to be implemented in the compute(i: Int) method.
 *
 * The twist here is that for the computeAny method the input is of type Any, which means the following rules also apply:
 *
 * - If the input is an Int, process it with the above rules
 * - If the input is a Double, and it's an integral value (e.g. isValidInt == true) convert it to an Int and process it
 * - If the input is a String, and it represents a numeric value (isDouble(str) == true), convert to a Double and process
 * - Anything else should be returned as its String representation (this case has been helpfully done for you)
 *
 */
object FuzzyFizzBuzzer {
  def compute(i: Int): String = ???

  def computeAny(a: Any): String = a match {
    case anythingElse => s"$anythingElse"
  }

  def isDouble(str: String) = try { str.toDouble ; true } catch { case nfe: NumberFormatException => false }
}
