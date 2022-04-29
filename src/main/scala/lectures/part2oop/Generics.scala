package lectures.part2oop

object Generics extends App {

  // A generic class with type A parameter (traits can also be type-parameterized)
  class MyList[+A] { // with the plus sign, MyList is covariant in the type A
    // def add(element: A): MyList[A] = ??? wouldn't work
    def add[B >: A](element: B): MyList[B] = ???
    /*
     In the example of cate and doges:
      A = Cat
      B = Animal
     */
  }

  // This shows how generic class MyList is reusable:
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // A generic class with multiple parameters
  class MyMap[Key, Value]

  val stringToIntMap = new MyMap[String, Int]
  val intToIntMap = new MyMap[Int, Int]

  // Companion object (note that objects can not be type-parametrized)
  object MyList {
    // Generic (type-parametrized) method
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers: MyList[Int] = MyList.empty[Int]

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  //////////////////////////////////////////////////////////
  // Question: if Cat extends Animal, does a list of Cat extend a list of Animal?
  // There are 3 possible answers to this question:
  // 1. Yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A] // + shows this is a covariant list

  val animal: Animal = new Cat
  // similarly
  val animalList: CovariantList[Animal] = new CovariantList[Cat]

  // animalList.add(new Dog) ?? is this OK? hard question! => answer: we return a list of animals
  //////////////////////////////////////////////////////////
  // 2. No = INVARIANCE
  class InvariantList[A]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // new InvariantList[Cat] gives error

  //////////////////////////////////////////////////////////
  // 3. Hell, no! CONTRAVARIANT
  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]

  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Bounded types
  // Allow you to use generic classes only for certain types that are either a sub-class or a super-class of a type.
  class Cage[A <: Animal](animal: A) // Class Cage only accepts type parameter A that is sub-type of Animal
  // On the other hand >: is lower-bounding

  val cage = new Cage(new Dog)

  class Car

  // val newCage = new Cage(new Car) // won't run

  // As an exercise, expand MyList (in exercises) to be generic => MyListV1

}
