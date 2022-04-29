package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // Expression
  println(x)

  println(2 + 3 + 4)
  // + - * / & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= ... side effects
  println(aVariable)

  // Instructions (DO) vs. Expressions (VALUES)

  // IF expressions
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if (aCondition) 5 else 3)

  var i = 0
  val aWhile: Unit = while (i < 10) {
    println(i)
    i += 1
  }
  // NEVER WRITE THIS AGAIN!
  // Don't use imperative syntax in Scala!
  // EVERYTHING in Scala is an Expression!

  val aWeirdValue: Unit = aVariable = 3 // Unit == void
  println(aWeirdValue) // The only value a Unit type can hold is ()
  // Side effects are expressions that return Unit, e.g., whiles, reassigning, println()

  // Code blocks

  val aCodeBlock = {
    val y = 2
    val z = y + 3

    if (z > 2) "hello" else "goodbye"
  }

  // 1. difference between "hello world" vs println("hello world") => expression of type String vs. a side effect (Unit type)
  // 2. what is the value of:
  val someValue = {
    2 < 3
  } // Boolean

  val someOtherValue = {
    if (someValue) 239 else 947
    44
  } // Int

}
