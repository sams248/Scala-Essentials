package exercises

/*
  All FunctionX calls replaced with lambdas
 */

abstract class MyListV4[+A] {
  def head: A

  def tail: MyListV4[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListV4[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // higher order functions
  def map[B](transformer: A => B): MyListV4[B]

  def flatMap[B](transformer: A => MyListV4[B]): MyListV4[B]

  def filter(predicate: A => Boolean): MyListV4[A]

  // concatenation
  def ++[B >: A](list: MyListV4[B]): MyListV4[B]
}

case object EmptyV4 extends MyListV4[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListV4[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListV4[B] = ConsV4(element, EmptyV4)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyListV4[Nothing] = EmptyV4

  def flatMap[B](transformer: Nothing => MyListV4[B]): MyListV4[B] = EmptyV4

  def filter(predicate: Nothing => Boolean): MyListV4[Nothing] = EmptyV4

  def ++[B >: Nothing](list: MyListV4[B]): MyListV4[B] = list
}

case class ConsV4[+A](h: A, t: MyListV4[A]) extends MyListV4[A] {
  def head: A = h

  def tail: MyListV4[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListV4[B] = ConsV4(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h else h + " " + t.printElements
  }

  def map[B](transformer: A => B): MyListV4[B] = {
    ConsV4(transformer(h), t.map(transformer))
  }

  def flatMap[B](transformer: A => MyListV4[B]): MyListV4[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  def filter(predicate: A => Boolean): MyListV4[A] = {
    if (predicate(h)) ConsV4(h, t.filter(predicate)) else t.filter(predicate)
  }

  def ++[B >: A](list: MyListV4[B]): MyListV4[B] = ConsV4[B](h, t ++ list)
}

object ListV4 extends App {
  val listOfIntegers: MyListV4[Int] = ConsV4(1, ConsV4(2, ConsV4(3, EmptyV4)))
  val listOfIntegersCloned: MyListV4[Int] = ConsV4(1, ConsV4(2, ConsV4(3, EmptyV4)))
  val anotherListOfIntegers: MyListV4[Int] = ConsV4(1, ConsV4(4, ConsV4(4, EmptyV4)))
  val listOfStrings: MyListV4[String] = ConsV4("hello", ConsV4("Scala", EmptyV4))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(element => element * 2))

  println(listOfIntegers.filter(element => element % 2 == 0))

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(element => ConsV4(element, ConsV4(element + 1, EmptyV4))).toString)

  println(listOfIntegers == listOfIntegersCloned)
}
