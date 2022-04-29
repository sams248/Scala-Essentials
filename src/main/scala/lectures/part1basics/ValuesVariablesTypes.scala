package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x = 45
  println(x)

  // vals are immutable

  // Compiler can infer types

  val aString: String = "hello"
  val anotherString: String = "goodbye"

  val aBoolean: Boolean = false

  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 6374 // The compiler will complain if the number is too big
  val aLong: Long = 273648726487326L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 5 // side effects

}
