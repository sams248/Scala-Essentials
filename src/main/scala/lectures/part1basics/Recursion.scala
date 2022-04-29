package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n == 1) n else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }
  }

  println(factorial(10))
  // With this implementation, factorial(5000) will lead to StackOverFlowError, recursive depth is too big!

  def anotherFactorial(n: Int): BigInt = {
    @tailrec // tells the compiler that this functions is supposed to be tail recursive.
    // If the function is not tail recursive, adding @tailrec will throw an error.
    def factorialHelper(m: Int, accumulator: BigInt): BigInt = {
      if (m <= 1) accumulator
      else factorialHelper(m - 1, m * accumulator) // Tail recursion (use recursive call as the LAST expression). It does not need the intermediate results.
    }

    factorialHelper(n, accumulator = 1)
  }

  /*
    anotherFactorial(10) = factorialHelper(10,1)
      = factorialHelper(9, 10 * 1)
      = factorialHelper(8, 9 * 10 * 1)
      = factorialHelper(7, 8 * 9 * 10 * 1)
      = ...
      = factorialHelper(2, 3 * 4 * ... * 10)
      = factorialHelper(1, 1 * 2 * 3 * ... * 10)
      = 1 * 2 * 3 * ... * 10
  */
  println(anotherFactorial(5000)) // When you need loop use tail recursion!

  /*
    Concatenate a string n times
   */
  @tailrec
  def concatTailRec(s: String, n: Int, accumulator: String): String = {
    if (n <= 0) accumulator
    else concatTailRec(s, n - 1, s + accumulator)
  }

  println(concatTailRec("Hello", 3, accumulator = ""))


  /*
   Tail recursive isPrime function
   */
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(m: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (m <= 1) true
      else isPrimeTailRec(m - 1, n % m != 0 && isStillPrime)
    }

    isPrimeTailRec(n / 2, isStillPrime = true)
  }

  println(isPrime(2003))
  println(isPrime(68))

  /*
   Tail recursive Fibonacci function
   */
  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciTailRec(m: Int, last: Int, nextToLast: Int): Int = {
      if (m >= n) last
      else fibonacciTailRec(m + 1, last + nextToLast, last)
    }

    if (n <= 2) 1 else fibonacciTailRec(2, 1, 1)
  }

  println(fibonacci(8))
}
