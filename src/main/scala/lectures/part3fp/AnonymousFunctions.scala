package lectures.part3fp

object AnonymousFunctions extends App {

  // val doubler = new Function[Int, Int] {
  //   override def apply(x: Int): Int = x * 2
  // }

  // anonymous function (akka lambda function)
  val doubler: Int => Int = (x: Int) => x * 2

  // multiple parameters in lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no parameters
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // function called

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  println(niceAdder(3, 18))

  /*
    1. replace all FunctionX calls in MyList with lambdas
    2. rewrite the "special" adder as an anonymous function
   */

  // 2
  val supperAdderLambda: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  println(supperAdderLambda(6)(8))
}
