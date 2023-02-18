package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ is called a wildcard
  }

  println(x)
  println(description)

  // 1. Decompose values (specially in conjunction with case classes)
  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I can't drink in the US" // guard
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)

  /*
    1. cases are matched in order
    2. what if no cases match? => scala.MatchError
    3. type of pattern matched expression > unified type of all the types in all the cases e.g. (String and Int -> Any)
    4. PM works really well with case classes
   */

  // PM on sealed hierarchies
  sealed class Animal

  case class Dog(breed: String) extends Animal

  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Bulldog")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  // match everything (overkill)
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  /*
    Exercise:
      simple function that uses PM
        takes an expression => human readable form
        Sum(Number(2), Number(4)) => 2 + 4
        Prod(Sum(NUmber(2), NUmber(1)), NUmber(3)) = (2 + 1) * 3
   */
  trait Expression

  case class Number(n: Int) extends Expression

  case class Sum(e1: Expression, e2: Expression) extends Expression

  case class Prod(e1: Expression, e2: Expression) extends Expression

  def show(expression: Expression): String = expression match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) =>
      def maybeShowParentheses(exp: Expression): String = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }

      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
  }

  println(show(Sum(Number(2), Number(3))))
  println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))
}
