package lectures.part2oop

object Exceptions extends App {
  // Exceptions are Java specific and not Scala specific
  val x: String = null
  // println(x.length) // will crash with NullPointerException

  // 1. Throwing and catching exceptions
  // val aWeirdException = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception (something went wrong with the program) and Error (something went wrong with the system) are the major Throwable subtypes.

  // 2. How to catch exceptions
  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No int for you!") else 42
  }

  val potentialFail: Int = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 42 // println("Caught a Runtime exception")
  } finally {
    // finally block is optional and does not influence the return type, use only for side effects (e.g. log something)
    // code that will be executed no matter what
    println("finally!")
  }

  println(potentialFail)

  // 3. How to define your own exceptions?
  class MyException extends Exception

  val exception = new MyException
  // throw exception

  /*
    1. Crash your program with an OutOfMemoryError (try to allocate more memory than JVM has)
    2. Crash with SOError
    3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)
      Throw:
        - OverflowException if add(x, y) exceeds Int.MAX_VALUE
        - UnderflowException if subtract(x, y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
   */

  // 1. OOM
  // val array = Array.ofDim(Int.MaxValue)

  // 2. StackOverflow
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  // RuntimeException is the superclass of those exceptions that can be thrown during the normal operation of the JVM
  class OverflowException extends RuntimeException

  class UnderflowException extends RuntimeException

  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  // println(PocketCalculator.add(Int.MaxValue, 10)) //-2147483639

   println(PocketCalculator.divide(10, 0))
}
