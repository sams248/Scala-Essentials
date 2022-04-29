package exercises

// Linked list that holds integers
abstract class MyListV0 {
  /*
   Methods:
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty?
    add(int) => new list with this element added
    toString => a string representation of the list
   */
  def head: Int

  def tail: MyListV0

  def isEmpty: Boolean

  def add(element: Int): MyListV0

  def printElements: String

  // Polymorphic call
  override def toString: String = "[" + printElements + "]"
}

// Object can extend a class
// ??? is equivalent to nothing
object EmptyV0 extends MyListV0 {
  def head: Int = throw new NoSuchElementException

  def tail: MyListV0 = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add(element: Int): MyListV0 = new ConsV0(element, EmptyV0)

  def printElements: String = ""
}

class ConsV0(h: Int, t: MyListV0) extends MyListV0 {
  def head: Int = h

  def tail: MyListV0 = t

  def isEmpty: Boolean = false

  def add(element: Int): MyListV0 = new ConsV0(element, this)

  def printElements: String = {
    if (t.isEmpty) "" + h else h + " " + t.printElements
  }
}

object ListTestV0 extends App {
  val list = new ConsV0(1, new ConsV0(2, new ConsV0(3, EmptyV0)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)
}