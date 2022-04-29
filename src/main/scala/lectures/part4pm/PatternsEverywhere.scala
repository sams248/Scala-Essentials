package lectures.part4pm

object PatternsEverywhere extends App {

  // Big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually matches!
  /*
    try {
      // code
     } catch (e) {
        case e: RuntimeException => "runtime"
        case npe: NullPointerException => "npe"
        case _ => "something else"
       }
    }
   */

  // Big idea # 2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?
  } yield 10 * x

  // Generators are also based on pattern matching
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators, ....

  // Big idea # 3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)
  // multiple value definition based on pattern matching

  val head :: tail = list
  println(head) // 1
  println(tail) // List(2, 3, 4)

  // Big idea #4
  // partial functions are based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1 => "the one"
    case _ => "something else"
  } // partial function literal

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }
  println(mappedList) // List(the one, 2 is even, something else, 4 is even)
  println(mappedList2) // List(the one, 2 is even, something else, 4 is even)
}
