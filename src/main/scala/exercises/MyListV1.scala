package exercises

// Make MyListV0 generic and covariant
abstract class MyListV1[+A] {
  def head: A

  def tail: MyListV1[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListV1[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"
}

object EmptyV1 extends MyListV1[Nothing] {
  // Nothing is a proper substitute for any type
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListV1[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListV1[B] = new ConsV1(element, EmptyV1)

  def printElements: String = ""
}

class ConsV1[+A](h: A, t: MyListV1[A]) extends MyListV1[A] {
  def head: A = h

  def tail: MyListV1[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListV1[B] = new ConsV1(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h else h + " " + t.printElements
  }
}

object ListTestV1 extends App {
  val listOfIntegers: MyListV1[Int] = new ConsV1(1, new ConsV1(2, new ConsV1(3, EmptyV1)))
  val listOfStrings: MyListV1[String] = new ConsV1("Hello", new ConsV1("Scala", EmptyV1))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
}