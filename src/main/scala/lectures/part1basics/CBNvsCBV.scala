package lectures.part1basics

object CBNvsCBV extends App {
  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
  }

  // delays the evaluation of the parameter until it is used in function definition
  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFist(x: Int, y: => Int): Unit = println(x)

  // printFist(infinite(), 34) // will cause a crash
  printFist(34, infinite()) // will not cause a crash because of lazy evaluation of y (never actually evaluated)
}
