package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') // appending and pre-pending are Scala specific (not in Java)
  println(str.reverse) // Scala specific
  println(str)
  print(str.take(2))

  // Scala-specific: String interpolator

  // S-interpolator
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old!" // s-interpolated string
  val anotherGreeting = s"Hello, my name is $name and I am ${age + 1} years old!"
  println(anotherGreeting)

  // F-interpolator
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute!" // 2 characters total, 2 decimal precision
  println(myth) // David can eat 1.20 burgers per minute!

  // Raw-interpolator
  println(raw"This is a \n newline") // prints it literally: This is a \n newline
  val escaped = "This is a \n newline"
  println(raw"$escaped") // will print in two lines

}
