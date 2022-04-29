package exercises

/*
  Expand MyList, add the following methods:
    - foreach method A => Unit
      [1, 2, 3].foreach(x => println(x))
    - sort function ((A, A) => Int) => MyList
      [1, 2, 3].sort((x, y) => y - x) = [3, 2, 1]
    - zipWith (list, (A, A) => B) => MyList[B]
      [1, 2, 3].zipWith([4, 5, 6], x*y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]
    - fold(start)(function) => a value
      [1, 2, 3].fold(0)(x + y) = 6
 */

abstract class MyListV5[+A] {
  def head: A

  def tail: MyListV5[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyListV5[B]

  def printElements: String

  override def toString: String = "[" + printElements + "]"

  // higher order functions
  def map[B](transformer: A => B): MyListV5[B]

  def flatMap[B](transformer: A => MyListV5[B]): MyListV5[B]

  def filter(predicate: A => Boolean): MyListV5[A]

  // concatenation
  def ++[B >: A](list: MyListV5[B]): MyListV5[B]

  // HOFs
  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyListV5[A] // compare returns a negative value if the first element is less than the second element

  def zipWith[B, C](list: MyListV5[B], zip: (A, B) => C): MyListV5[C]

  def fold[B](start: B)(operator: (B, A) => B): B
}

case object EmptyV5 extends MyListV5[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListV5[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyListV5[B] = ConsV5(element, EmptyV5)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyListV5[Nothing] = EmptyV5

  def flatMap[B](transformer: Nothing => MyListV5[B]): MyListV5[B] = EmptyV5

  def filter(predicate: Nothing => Boolean): MyListV5[Nothing] = EmptyV5

  def ++[B >: Nothing](list: MyListV5[B]): MyListV5[B] = list

  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyListV5[Nothing] = EmptyV5

  def zipWith[B, C](list: MyListV5[B], zip: (Nothing, B) => C): MyListV5[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do no have the same length!")
    else EmptyV5
  }

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class ConsV5[+A](h: A, t: MyListV5[A]) extends MyListV5[A] {
  def head: A = h

  def tail: MyListV5[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyListV5[B] = ConsV5(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h else h + " " + t.printElements
  }

  def map[B](transformer: A => B): MyListV5[B] = {
    ConsV5(transformer(h), t.map(transformer))
  }

  def flatMap[B](transformer: A => MyListV5[B]): MyListV5[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }

  def filter(predicate: A => Boolean): MyListV5[A] = {
    if (predicate(h)) ConsV5(h, t.filter(predicate)) else t.filter(predicate)
  }

  def ++[B >: A](list: MyListV5[B]): MyListV5[B] = ConsV5[B](h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyListV5[A] = {
    def insert(x: A, sortedList: MyListV5[A]): MyListV5[A] = {
      if (sortedList.isEmpty) ConsV5(x, sortedList)
      else if (compare(x, sortedList.head) < 0) ConsV5(x, sortedList)
      else ConsV5(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyListV5[B], zip: (A, B) => C): MyListV5[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do no have the same length!")
    else ConsV5(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B = {
    t.fold(operator(start, h))(operator)
  }
}

object ListV5 extends App {
  val listOfIntegers: MyListV5[Int] = ConsV5(1, ConsV5(2, ConsV5(3, EmptyV5)))
  val listOfIntegersCloned: MyListV5[Int] = ConsV5(1, ConsV5(2, ConsV5(3, EmptyV5)))
  val anotherListOfIntegers: MyListV5[Int] = ConsV5(4, ConsV5(5, EmptyV5))
  val listOfStrings: MyListV5[String] = ConsV5("hello", ConsV5("Scala", EmptyV5))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(element => element * 2))

  println(listOfIntegers.filter(element => element % 2 == 0))

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(element => ConsV5(element, ConsV5(element + 1, EmptyV5))).toString)

  println(listOfIntegers == listOfIntegersCloned)

  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x, y) => y - x))

  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  println(listOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val combinations = for {
    n <- listOfIntegers
    s <- listOfStrings
  } yield n + "-" + s
  println(combinations)
}
