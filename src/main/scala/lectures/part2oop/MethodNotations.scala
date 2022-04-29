package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    // def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = {
      new Person(s"$name ($nickname)", favoriteMovie)
    }

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1) // note the space before :

    def unary_! : String = s"$name, what the heck!?"

    def isAlive: Boolean = true

    def learns(subject: String) = s"$name learns $subject."

    def learnsScala: String = this learns "Scala"

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie!" // () is important

    def apply(n: Int) = s"$name watched $favoriteMovie $n times!"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // This is called infix notation or operator notation (only works for methods that have only one parameter)
  // This is an example of syntactic sugar: nicer way of writing complex more code

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  //  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // All operators are methods!
  // Akka actors have operators like ! or ? (tell and ask patters)

  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_- // unary operators are actually methods with unary_ prefix
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation (only available for methods without parameters)
  print(mary.isAlive)
  println(mary isAlive) // not common

  // apply
  println(mary.apply())
  println(mary()) // equivalent
  // When an object is called like a function, the compiler will look for a definition of apply in that object

  /*
    1. Overload the + operator
        mary + "the rockstar" =? new person "Mary (the rockstar)"
    2. Add an age to the Person class, with default 0
         Add a unary + operator => new Person with age + 1
         +mary => mary with the age incremented
    3. Add a "learns" method in the Person class => Mary learns Scala"
         Add a learnsScala method, calls learns method with "Scala".
         Use it in postfix notation.
    4. Overload the apply method
        mary.apply(2) => "Mary watched Inception 2 times"
   */

  println((mary + "the Rockstar") ())

  println((+mary).age)

  println(mary learnsScala)

  println(mary.apply(2))

}
