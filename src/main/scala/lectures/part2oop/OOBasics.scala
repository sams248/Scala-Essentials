package lectures.part2oop

object OOBasics extends App {

  val person = new Person("Sam", 25)
  println(person.age)
  println(person.x)
  person.greet("Daniel")

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  val counter = new Counter
  counter.increment.print()
  counter.increment.increment.increment.print()
  counter.increment(10).print()

}

// class parameters are not fields, add key word val to convert a class parameter to a class field if you want
class Person(name: String, val age: Int) { // constructor
  // body (defines the implementation of the class)
  val x = 2 // val and var definitions inside a class are fields
  println(1 + 3) // at every instantiation of the class, every expression in the body will be executed

  // class method
  def greet(name: String): Unit = {
    println(s"${this.name} says: Hi, $name!")
  }

  // overloading (defining methods with the same names and different signatures, i.e. different number of parameters or return types)
  def greet(): Unit = print(s"Hi, I am $name") // it is implied that name is this.name here

  // multiple constructors (overloading constructors)
  def this(name: String) = this(name, 0) // calls the primary constructor
  // better to use default for age instead

  def this() = this("John Snow")
}

/*
  Implement Novel and Writer classes

    Writer: first name, surname, year of birth
      - method fullName

    Novel: name, year of release, author
      - authorAge
      - isWrittenBy (author)
      - copy (receives new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val yearOfBirth: Int) {
  def fullName: String = firstName + ' ' + surname
}

class Novel(name: String, releaseYear: Int, author: Writer) {

  def authorAge: Int = releaseYear - author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newReleaseYear: Int): Novel = new Novel(name, newReleaseYear, author)
}

/*
  Counter class
    - receives an Int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount (inc/dec n times)
 */

class Counter(val count: Int = 0) {

  def increment: Counter = {
    println("Incrementing...")
    new Counter(count + 1) // immutability
    // instances are fixed and cannot be modified
    // whenever you need to modify the content of an instance, you need to create a new instance
  }

  def decrement: Counter = {
    println("Decrementing...")
    new Counter(count - 1)
  }

  // n: number of times
  def increment(n: Int): Counter = {
    if (n <= 0) this
    else increment.increment(n - 1)
  }

  def decrement(n: Int): Counter = {
    if (n <= 0) this
    else decrement.decrement(n - 1)
  }

  def print(): Unit = println(count)
}








