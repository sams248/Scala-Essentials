package lectures.part2oop

object Objects {

  // Scala does not have class-level functionality (the concept of "static")
  // Instead, objects in Scala have static-like functionality
  // Objects do not receive parameters
  object Person {
    // "static"/"class" -  level functionality
    val N_EYES = 2

    // Objects can also have method definitions
    def canFly: Boolean = false

    // Factory method: builds person given some parameters (most times called apply)
    def apply(mother: Person, father: Person): Person = new Person("Bob")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // Companions: the pattern of having a class and an object with the same name in the same scope

  // Scala Application = Scala object with a particular method: def main(args: Array[String]): Unit
  // Turns scala applications into JVM applications
  // I can replace extends App with this

  def main(args: Array[String]): Unit = {
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = Singleton instance, i.e., when we define an object, we define its type as well as its only instance.
    // A singleton is a class that allows only a single instance of itself to be created and gives access to that created instance.
    val mary = Person
    val joe = Person
    println(mary == joe) // true: They point to the same instance, which is the Person object.

    val sarah = new Person("Sarah")
    val jack = new Person("Jack")
    println(sarah == jack) // false

    val bob = Person(sarah, jack) // or Person.apply(sarah, jack)
  }

}
