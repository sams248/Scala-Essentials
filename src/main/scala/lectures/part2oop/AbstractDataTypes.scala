package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract (there are situations where you need to leave some fields or methods blank or unimplemented
  // classes that contain abstract fields or methods are called abstract classes
  // abstract classes cannot be instantiated
  abstract class Animal {
    val creatureType: String = "wild"

    def eat(): Unit
  }

  // abstract classes are meant to be extended
  class Dog extends Animal {
    override val creatureType: String = "canine"

    def eat(): Unit = println("crunch crunch") // override key word is not required
  }

  // traits are the ultimate abstract data types in Scala
  // just like abstract classes, traits have abstract fields and methods
  trait Carnivore {
    def eat(animal: Animal): Unit

    val preferredMeal: String = "fresh meat" // non-abstract member
  }

  trait ColdBlooded // we can mixin with as many traits as we want

  // unlike abstract classes, traits can be inherited along classes
  class Crocodile extends Animal with Carnivore with ColdBlooded { // this class will inherit from both class and trait
    override val creatureType: String = "crocodile"

    def eat(): Unit = println("nom")

    def eat(animal: Animal): Unit = println(s"I'm a ${this.creatureType} and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val crocodile = new Crocodile
  crocodile.eat(dog)

  // traits vs. abstract classes
  // both traits and abstract classes can have abstract and non-abstract members
  // 1. traits do not have constructor parameters
  // 2. you can only extend one class but you can mixin multiple traits (multiple traits may be inherited by the same class)
  // 3. traits = behavior (describe what a thing does) , abstract class = "thing"
}
