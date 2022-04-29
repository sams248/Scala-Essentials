package lectures.part3fp

object AnonymousFunctions extends App {

  // val doubler = new Function[Int, Int] {
  //   override def apply(x: Int): Int = x * 2
  // }

  // Anonymous function (akka lambda function)
  val doubler: Int => Int = (x: Int) => x * 2

  // Multiple parameters in lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // No parameters
  val justDoSomething: () => Int = () => 3

  // Notice:
  println(justDoSomething) // function itself
  println(justDoSomething()) // function called

  // Curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // Syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  println(niceAdder(3, 18))

  /*
    1. Replace all FunctionX calls in MyList with lambdas (MyListV4)
    2. Rewrite the "special" adder as an anonymous function
   */

  // 2
  val supperAdderLambda: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  println(supperAdderLambda(6)(8))
}
