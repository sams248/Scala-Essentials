package lectures.part3fp

object WhatsAFunction extends App {

  // use functions as first class elements
  // problem: coming from OOP
  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  println(doubler(2))

  // function types = Function1[A, B] (function1 ... function22 are by default supported by Scala)
  val stringToInToConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToInToConverter("3") + 4)

  //  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
  //    override def apply(a: Int, b: Int): Int = a + b
  //  }
  // syntactic sugar for Function2
  // Function types Function2[A, B, R] === (A, B) => R
  val adder: (Int, Int) => Int = new ((Int, Int) => Int) {
    override def apply(a: Int, b: Int): Int = a + b
  }


  // AL SCALA FUNCTIONS ARE OBJECTS
  // JVM was designed with OOP in mind

  /*
    1. define a function that takes 2 strings and concatenate them
    2. transform the MyPredicate and MyTransformer in MyList to function types
    3. define a function which takes an Int and returns another function that takes an Int and returns an Int:
      - what's the type of this function
      - how to do it
   */

  // 1
  val strConcat: (String, String) => String = new Function2[String, String, String] {
    override def apply(str1: String, str2: String): String = str1 + str2
  }
  println(strConcat("Hello", "Scala"))

  // 3
  val supperAdder: (Int => (Int => Int)) = new Function1[Int, Function1[Int, Int]] {
    override def apply(n: Int): (Int => Int) = new Function[Int, Int] {
      override def apply(m: Int): Int = n + m
    }
  }

  val adder3 = supperAdder(3)
  println(adder3(4))
  println(supperAdder(3)(4)) // curried function
}
