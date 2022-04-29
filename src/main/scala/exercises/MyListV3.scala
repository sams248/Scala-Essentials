package exercises

/*
  MyPredicate and MyTransformer transformed to function types
 */

abstract class MyListV3[+A] {
  def head: A

  def tail: MyListV3[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListV3[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // higher order functions
  def map[B](transformer: A => B): MyListV3[B]

  def flatMap[B](transformer: A => MyListV3[B]): MyListV3[B]

  def filter(predicate: A => Boolean): MyListV3[A]

  // concatenation
  def ++[B >: A](list: MyListV3[B]): MyListV3[B]
}

case object EmptyV3 extends MyListV3[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListV3[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListV3[B] = ConsV3(element, EmptyV3)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyListV3[Nothing] = EmptyV3

  def flatMap[B](transformer: Nothing => MyListV3[B]): MyListV3[B] = EmptyV3

  def filter(predicate: Nothing => Boolean): MyListV3[Nothing] = EmptyV3

  def ++[B >: Nothing](list: MyListV3[B]): MyListV3[B] = list
}

case class ConsV3[+A](h: A, t: MyListV3[A]) extends MyListV3[A] {
  def head: A = h

  def tail: MyListV3[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListV3[B] = ConsV3(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h else h + " " + t.printElements
  }

  def map[B](transformer: A => B): MyListV3[B] = {
    ConsV3(transformer(h), t.map(transformer))
  }

  def flatMap[B](transformer: A => MyListV3[B]): MyListV3[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  def filter(predicate: A => Boolean): MyListV3[A] = {
    if (predicate(h)) ConsV3(h, t.filter(predicate)) else t.filter(predicate)
  }

  def ++[B >: A](list: MyListV3[B]): MyListV3[B] = ConsV3[B](h, t ++ list)
}

object ListTestV3 extends App {
  val listOfIntegers: MyListV3[Int] = ConsV3(1, ConsV3(2, ConsV3(3, EmptyV3)))
  val listOfIntegersCloned: MyListV3[Int] = ConsV3(1, ConsV3(2, ConsV3(3, EmptyV3)))
  val anotherListOfIntegers: MyListV3[Int] = ConsV3(1, ConsV3(4, ConsV3(4, EmptyV3)))
  val listOfStrings: MyListV3[String] = ConsV3("hello", ConsV3("Scala", EmptyV3))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }))

  println(listOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(element: Int): Boolean = element % 2 == 0
  }))

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new Function1[Int, MyListV3[Int]] {
    override def apply(element: Int): MyListV3[Int] = ConsV3(element, ConsV3(element + 1, EmptyV3))
  }).toString)

  println(listOfIntegers == listOfIntegersCloned)
}
