package lectures.part3fp

import scala.annotation.tailrec

object HOFsCurries extends App {

  val superFunction: (Int, (String, Int => Boolean) => Int) => (Int => Int) = null
  // Higher Order Function (HOF) : has function(s) as parameter or return a function
  // map, flatMap, filter

  // Function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))

  @tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x)) // f(x) is f.apply(x)

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // nTimesBetter(f, n) = x => f(f(f(...(x)))
  // plus10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne...(x))
  // val y = plus10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // Curried functions
  // Currying is the technique of translating the evaluation of a function that takes multiple arguments into evaluating
  // a sequence of functions, each with a single argument.
  val supperAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = supperAdder(3) // y => 3 + y
  println(add3(10))
  println(supperAdder(3)(10))

  // Function with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1. Expand MyList (Done in MyListV5)
        - foreach method A => Unit
          [1, 2, 3].foreach(x => println(x))

        - sort function ((A, A) => Int) => MyList
          [1, 2, 3].sort((x, y) => y - x) = [3, 2, 1]

        - zipWith (list, (A, A) => B) => MyList[B]
          [1, 2, 3].zipWith([4, 5, 6], x*y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

        - fold(start)(function) => a value
          [1, 2, 3].fold(0)(x + y) = 6

    2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int))
       fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

    3. compose(f, g) = x => f(g(x))
       andThen(f, g) = x => g(f(x))
   */

  // 2
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
    x => y => f(x, y)
  }

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
    (x, y) => f(x)(y)
  }

  // 3
  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int = x => g(f(x))

  def composeGeneralized[A, B, T](f: A => B, g: T => A): T => B = x => f(g(x))

  def andThenGeneralized[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)

  def add4 = superAdder2(4)

  println(add4(17))

  val simpleAdder = fromCurry(supperAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
