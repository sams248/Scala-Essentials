package exercises

/*
 1. Generic trait MyPredicate[-T] (contravariant in type T) with a little method test(T) => Boolean
 2. Generic trait MyTransformer[-A, B] (contravariant in type A) with a method transform(A) => B
 3. MyList:
    - map(transformer) => MyList
    - filter(predicate) => MyList
    - flatMap(transformer from A to MyList[B]) => MyList[B]

  For example:
    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1, 2, 3].map(n * 2) = [2, 4, 6]
    [1, 2, 3, 4].filter(n % 2) = [2, 4]
    [1, 2, 3].flatMap(n => [n, n+1]) => [1, 2, 2, 3, 3, 4]
 */

abstract class MyListV2[+A] {
  def head: A

  def tail: MyListV2[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListV2[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: MyTransformer[A, B]): MyListV2[B]

  def flatMap[B](transformer: MyTransformer[A, MyListV2[B]]): MyListV2[B]

  def filter(predicate: MyPredicate[A]): MyListV2[A]

  // concatenation
  def ++[B >: A](list: MyListV2[B]): MyListV2[B]
}

case object EmptyV2 extends MyListV2[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListV2[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListV2[B] = ConsV2(element, EmptyV2)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyListV2[Nothing] = EmptyV2

  def flatMap[B](transformer: MyTransformer[Nothing, MyListV2[B]]): MyListV2[B] = EmptyV2

  def filter(predicate: MyPredicate[Nothing]): MyListV2[Nothing] = EmptyV2

  def ++[B >: Nothing](list: MyListV2[B]): MyListV2[B] = list
}

case class ConsV2[+A](h: A, t: MyListV2[A]) extends MyListV2[A] {
  def head: A = h

  def tail: MyListV2[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListV2[B] = ConsV2(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h else h + " " + t.printElements
  }

  /*
    [1, 2, 3].map(n * 2)
      = new ConsV2(2, [2, 3].map(n * 2))
      = new ConsV2(2, ConsV2(4, [3].map(n * 2)))
      = new ConsV2(2, ConsV2(4, ConV2(6, EmptyV2.map((n * 2))))
      = new ConsV2(2, ConsV2(4, ConV2(6, EmptyV2)))
   */
  def map[B](transformer: MyTransformer[A, B]): MyListV2[B] = {
    ConsV2(transformer.transform(h), t.map(transformer))
  }

  /*
    [1, 2].flatMap(n => [n, n+1])
    = [1, 2] ++ [2].flatMap(n => [n, n+1])
    = [1, 2] ++ [2, 3] ++ EmptyV2.flatMap(n => [n, n + 1])
    = [1, 2] ++ [2, 3] ++ EmptyV2
    = [1, 2, 2, 3]
   */

  def flatMap[B](transformer: MyTransformer[A, MyListV2[B]]): MyListV2[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }

  /*
    [1, 2, 3].filter(n % 2 == 0)
      = [2, 3].filter(n % 2 == 0)]
      = new ConsV2(2, [3].filter(n % 2 == 0))
      = new ConsV2(2, EmptyV2.filter(n % 2 == 0))
      = new ConsV2(2, EmptyV2)
   */
  def filter(predicate: MyPredicate[A]): MyListV2[A] = {
    if (predicate.test(h)) ConsV2(h, t.filter(predicate)) else t.filter(predicate)
  }

  /*
   [1, 2] ++ [3, 4, 5]
    = new ConsV2(1, [2] ++ [3, 4, 5]))
    = new ConsV2(1, new ConsV2(2, EmptyV2 ++ [3, 4, 5]))
    = new ConsV2(1, new ConsV2(3, new ConsV2(4, new ConsV2(5))))
   */
  def ++[B >: A](list: MyListV2[B]): MyListV2[B] = ConsV2[B](h, t ++ list)
}

trait MyPredicate[-T] {
  def test(element: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(element: A): B
}

class EvenPredicate extends MyPredicate[Int] {
  override def test(value: Int): Boolean = value % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  override def transform(input: String): Int = input.toInt
}

object ListTestV2 extends App {
  val listOfIntegers: MyListV2[Int] = ConsV2(1, ConsV2(2, ConsV2(3, EmptyV2)))
  val listOfIntegersCloned: MyListV2[Int] = ConsV2(1, ConsV2(2, ConsV2(3, EmptyV2)))
  val anotherListOfIntegers: MyListV2[Int] = ConsV2(1, ConsV2(4, ConsV2(4, EmptyV2)))
  val listOfStrings: MyListV2[String] = ConsV2("hello", ConsV2("Scala", EmptyV2))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }))

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }))

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListV2[Int]] {
    override def transform(element: Int): MyListV2[Int] = ConsV2(element, ConsV2(element + 1, EmptyV2))
  }).toString)

  println(listOfIntegers == listOfIntegersCloned)
}
