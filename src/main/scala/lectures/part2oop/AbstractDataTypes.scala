package lectures.part2oop

object AbstractDataTypes extends App {

  /*
    There are situations where you need to leave some fields or methods blank or unimplemented.
    Classes that contain abstract fields or methods are called abstract classes.
    Abstract classes cannot be instantiated.
  */
  abstract class Animal {
    val creatureType: String = "wild"

    def eat(): Unit
  }

  // Abstract classes are meant to be extended later
  class Dog extends Animal {
    override val creatureType: String = "canine"

    def eat(): Unit = println("crunch crunch") // override key word is not required
  }

  /*
    Traits are the ultimate abstract data types in Scala.
    Just like abstract classes, traits have abstract fields and methods.
   */
  trait Carnivore {
    def eat(animal: Animal): Unit

    val preferredMeal: String = "fresh meat" // non-abstract member
  }

  trait ColdBlooded // we can mixin with as many traits as we want

  // What is special about traits is that, unlike abstract classes, they can be inherited along classes.
  // This class will inherit from both class and trait.
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "crocodile"

    def eat(): Unit = println("nom")

    def eat(animal: Animal): Unit = println(s"I'm a ${this.creatureType} and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

 /*
   Traits vs. abstract classes:
     Both traits and abstract classes can have abstract and non-abstract members.
     Differences:
       1. Traits do not have constructor parameters
       2. You can only extend one class but you can mixin multiple traits (multiple traits may be inherited by the same class)
       3. Traits = behavior (describe what a thing does), abstract class = "thing"
  */
}
