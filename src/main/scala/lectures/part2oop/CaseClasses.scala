package lectures.part2oop

object CaseClasses extends App {

  /*
   Case classes are like regular classes with a few key differences. Case classes are good for modeling immutable data.
   */

  case class Person(name: String, age: Int)

  // 1. case classes promote all parameters to fields
  val jim = Person("Jim", 34)
  println(jim.name) // Person(Jim,34) instead of something cryptic

  // 2. sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar, e.g. we can println(jim)
  println(jim.toString)
  println(jim) // Person(Jim,34)

  // 3. equals and hashCode implemented out of the box
  val jim2 = Person("Jim", 34)
  println(jim == jim2) // true, it would false if they are two instances of a class

  // 4. case classes have handy copy methods
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. case classes have companion objects
  val thePerson = Person // is a valid definition, Person is the (automatically created) companion object of case class Person
  // companion objects have handy factory methods
  val mary = Person("Mary", 22) // delegates to Person.apply(), which does the same thing as constructor
  // so for case classes we don't use key word new

  // 6. case classes are serializable, make them a good candidate for distributed systems such as Akka
  // Akka sends serializable messages (often in general case classes) through a network

  // 7.  case classes have extractor patterns =  can be used in pattern matching

  // case objects exist, they are similar to case classes except that they don't have companion objects, because they are companion objects!
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
   Expand MyList to use case classes and case objects whenever you see fit
   */
}
