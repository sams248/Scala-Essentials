package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterLessFunction(): Int = 24

  println(aParameterLessFunction())
  println(aParameterLessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // When you need loops, use recursion!

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  /*
  1. A greeting function (name, age) = > "Hi, my name is $name and I am $age years old."
  2. Factorial function 1 * 2 * 3 * ... * n
  3. A fibonacci function
    f(1) = 1
    f(2) = 1
    f(n) = f(n-1) + f(n-2)
   4. Test if a number is Prime.
   */

  def greet(name: String, age: Int): Unit = {
    println(s"Hi, my name is $name and I am $age years old.")
  }

  greet("Helen", 13)

  def factorial(n: Int): Int = {
    if (n == 1) n else n * factorial(n - 1)
  }

  println(factorial(8))

  def fibonacci(n: Int): Int = {
    if (n <= 1) 1 else fibonacci(n - 1) + fibonacci(n - 2)
  }

  println(fibonacci(7))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUntil(m: Int): Boolean = {
      if (m <= 1) true
      else n % m != 0 && isPrimeUntil(m - 1)
    }

    isPrimeUntil(n / 2)
  }

  println(isPrime(13))
  println(isPrime(18))
  println(isPrime(2003))
}
