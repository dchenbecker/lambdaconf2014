package lambdaconf

/**
 * In this example we'll build an RPN (Reverse Polish Notation) calculator that operates on Inputs representing either
 * a Double or one of five operations: +, -, *, /, and SWAP. The calculate method takes a List of Input instances, and
 * must process each one while maintaining a stack. The processing rules are:
 *
 * - If a Push(double) is encountered, return a new stack by prepending the number to the existing stack
 * - If an operator is encountered, take the first two elements from the stack, perform the operation on them, and
 *   return a new stack comprised of the result of the computation prepended to the original stack with the first two
 *   elements removed (you're replacing the first two with the result). Note that since we're using a List as the stack,
 *   it's IN REVERSE ORDER FROM THE INPUTS, so make sure that an input of List(12, 8, -) returns 4, not -4.
 * - If an operator is encountered and there are not enough elements on the stack, throw an InvalidComputationException
 *
 * When no more elements remain on the input List, the head of the List is returned. If
 * an invalid input is encountered the calculate method must throw an InvalidComputationException.
 *
 * For example, if the input is List(1, 1, +) then the result should be 2, but if the input were List(1, +, 1) we
 * should get an exception because when the "+" is encountered there's only one element (1) on the stack.
 *
 * If you finish this and want to go further, try adding some unary operators (e.g. Negate, Sqrt) and the Swap operator
 * (swaps the top two elements on the stack).
 */
object RPNCalc {
  def calculate(input: List[Input]): Double = input.foldLeft(List.empty[Double] /* This is our initial stack */) {
    /*
    For those of you not familiar with foldLeft, it will basically apply this same pattern match block to each input
    in sequence, replacing the input stack with whatever we return from the pattern's execution. In other words, your
    patterns should be structured like:

    case (stack pattern, input pattern) => ...compute new stack here...

    Remember, Lists can be matched using the :: operator and Nil to represent the empty List
    */
    case (stack, Push(n)) => n :: stack
    case (second :: first :: rest, Add) => (first + second) :: rest
    case (second :: first :: rest, Subtract) => (first - second) :: rest
    case (second :: first :: rest, Multiply) => (first * second) :: rest
    case (second :: first :: rest, Divide) => (first / second) :: rest
    case (stack, op) => throw new InvalidComputationException(s"Invalid operand $op with stack $stack")
  }.headOption.getOrElse {
    throw new InvalidComputationException("No result was left on the stack after computation")
  }
}

sealed trait Input

case class Push(d: Double) extends Input

sealed trait Operator extends Input

case object Add extends Operator
case object Subtract extends Operator
case object Multiply extends Operator
case object Divide extends Operator


class InvalidComputationException(msg: String) extends Exception(msg)
