package lectures.part2oop

object Inheritance extends App {

  // Single class inheritance : extend one class at a time
  sealed class Animal {
    val creatureType = "wild"

    def sleep(): Unit = println("Sleeping...")

    def eat(): Unit = println("Eating...")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.sleep()
  cat.crunch()

  // sub-class only inherits non-private members of a super class
  // protected makes a class accessible within itself and [inside] its subclasses

  // Overriding
  class Dog(override val creatureType: String) extends Animal {
    // override val creatureType = "domestic"
    override def eat(): Unit = {
      super.eat() // super: used when we want to reference a method of field from parent class
      println("crunch, crunch")
    }
  }

  val dog = new Dog("domestic")
  dog.eat()
  println(dog.creatureType)

  // Type substitution (in a broad sense: polymorphism)
  val unknownAnimal: Animal = new Dog("German Shepherd")
  unknownAnimal.eat() // goes to the most overridden version of the method

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  // extending a class with parameters
  class Adult(name: String, age: Int, idCard: String) extends Person(name) // JVM needs to call the constructor of Person,
  // before calling the constructor of Adult, so we must pass correct arguments to Person.

  // Note the difference between overriding vs. overloading

  /*
   Preventing overrides:
     1. use keyword final on member
     2. use final on class entire class
     3. seal the class = you can extend classes in this file, but prevents extension in other files
   */

}
