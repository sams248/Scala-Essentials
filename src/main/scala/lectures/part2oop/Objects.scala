package lectures.part2oop

object Objects {
  // Scala does not have class-level functionality (the concept of "static")
  // Instead, objects in Scala have static like functionality
  // Objects do not receive parameters
  object Person { // type + its only instance
    // "static"/"class" -  level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // factory method: builds person given some parameters (most times called apply)
    def apply(mother: Person, father: Person): Person = new Person("Bob")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // Companions: the pattern of having a class and an object with the same name in the same scope

  def main(args: Array[String]): Unit = {
    print(Person.N_EYES)
    println(Person.canFly)

    // Scala object = Singleton instance
    val mary = Person
    val joe = Person
    println(mary == joe) // They point to the same instance, which is the Person object

    val sarah = new Person("Sarah")
    val jack = new Person("Jack")
    println(sarah == jack)

    val bob = Person(sarah, jack) // or Person.apply(sarah, jack)
  }

  // Scala Application = Scala object with def main(args: Array[String]): Unit
  // Turns scala applications into JVM applications
  // I can replace extends App with this

}
