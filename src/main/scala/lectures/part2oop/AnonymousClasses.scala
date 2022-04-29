package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat(): Unit
  }

  // Anonymous class (on the spot implementation)
  val funnyAnimal: Animal = new Animal {
    override def eat(): Unit = println("haha")
  }

  /*
    Behind the scenes, this is what the compiler does:

      class AnonymousClasses$$anon$1 extends Animal {
        override def eat(): Unit = println("haha")
      }
      val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass) // class lectures.part2oop.AnonymousClasses$$anon$1

  // Anonymous classes work for both abstract and non-abstract data types
  class Person(name: String) {
    def sayHi(): Unit = println(s"Hi, my name is $name, how can I help you?")
  }

  val jim = new Person("Jim") {
    override def sayHi(): Unit = println(s"Hi, my name is Jim, how can I assist you?")
  }

  println(jim.getClass)

}
