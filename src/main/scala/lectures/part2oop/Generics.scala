package lectures.part2oop

object Generics extends App {
  // a generic class with type A parameter, traits can also be type parameterized
  class MyList[+A] { // with the plus sign, MyList is covariant in the type A
    // def add(element: A): MyList[A] = ??? wouldn't work
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // if Cat extends Animal, does a list of Cat extend a list of Animal
  // there are 3 possible answers to this question:

  // 1. Yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A] //+ shows this is a covariant list

  val animal: Animal = new Cat
  // similarly
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ?? is this OK? hard question! => answer: we return a list of animals

  // 2. No = INVARIANCE
  class InvariantList[A]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal] // new InvariantList[Cat] gives error

  // 3. Hell, no! CONTRAVARIANT
  class ContravariantList[-A]

  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]


  // bounded types
  class Cage[A <: Animal](animal: A) // accepts sub-types of Animal

  val cage = new Cage(new Dog)

  class Car

  // val newCage = new Cage(new Car) // won't run

  // expand MyList (in exercises) to be generic => MyListV1

}
