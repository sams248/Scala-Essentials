package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7, 5, 6))
  println(aSequence.sorted)

  // Range
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)
  (1 until 10).foreach(_ => println("Hello"))

  // List
  val aList = List(1, 2, 3, 4)
  val prepended = 42 :: aList
  println(prepended)
  println(22 +: aList :+ 44)
  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // Array
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)
  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))
  // arrays and seq
  val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq)

  // Vector
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getAverageWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // + keeps reference to tail
  // - updating an element in the middle takes a long time
  println(getAverageWriteTime(numbersList)) // 6438190.49
  // + depth of the tree is small
  // - needs to replace and entire 32-element chunk
  println(getAverageWriteTime(numbersVector)) // 3543.152 => this is why vector is the default implementation of immutable Seq

}
