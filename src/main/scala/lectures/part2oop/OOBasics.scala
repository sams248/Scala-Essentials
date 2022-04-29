package lectures.part2oop

object OOBasics extends App {

  // constructor
  class Person(name: String, val age: Int) {
    //body
    val x = 2 // this is going to be a class field
    println(1 + 3)

    // method
    def greet(name: String): Unit = {
      println(s"${this.name} says: Hi, $name!")
    }

    // overloading
    def greet(): Unit = print(s"Hi, I am $name")

    // multiple constructors
    def this(name: String) = this(name, 0)

    def this() = this("John Snow")
  }

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

/*
  Implement Novel and Writer classes

  Writer: first name, surname, year
  - method fullName

  Novel: name, year of release, author
  - authorAge
  - iWrittenBy (author)
  - copy (new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val yearOfBirth: Int) {
  def fullName: String = firstName + ' ' + surname
}

class Novel(name: String, releaseYear: Int, author: Writer) {
  def authorAge: Int = {
    releaseYear - author.yearOfBirth
  }

  def isWrittenBy(author: Writer): Boolean = {
    author == this.author
  }

  def copy(newReleaseYear: Int): Novel = {
    new Novel(name, newReleaseYear, author)
  }
}

/*
  Counter class
  - receives an int value
  - method current count
  - method to increment/decrement => new Counter
  - overload inc/dec to receive an amount (inc/dec n times)
 */

class Counter(val count: Int = 0) {

  def increment: Counter = {
    println("Incrementing...")
    new Counter(count + 1) // immutability
    // whenever you need to modify the content of an instance, you need to create a new instance
  }

  def decrement: Counter = {
    println("Decrementing...")
    new Counter(count - 1)
  }

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








