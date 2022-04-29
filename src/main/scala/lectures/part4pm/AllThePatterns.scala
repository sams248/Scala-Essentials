package lectures.part4pm

import exercises.{ConsV5, EmptyV5, MyListV5}

object AllThePatterns extends App {

  // 1. Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The Scala!"
    case true => "The truth!"
    case AllThePatterns => "A singleton object"
  }

  // 2. Match anything
  // 2.1 wildcard
  val matchAnything: Unit = x match {
    case _ =>
  }

  // 2.2. variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3. Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) =>
    case (1, something) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple: Unit = nestedTuple match {
    case (_, (2, v)) =>
  }
  // PMs can be nested!

  // 4. Case classes - constructor pattern
  // PMs can be nested with CCs as well
  val aList: MyListV5[Int] = ConsV5(1, ConsV5(2, EmptyV5))
  val matchAList: Unit = aList match {
    case EmptyV5 =>
    case ConsV5(head, ConsV5(subHead, subTail)) =>
  }

  // 5. List patterns
  val aStandardList = List(1, 2, 3, 88)
  val standardListMatching: Unit = aStandardList match {
    case List(1, _, _, _) => // extractor - advanced topic
    case List(1, _*) => // list of arbitrary lengths - advanced
    case 1 :: List(_) => // infix pattern
    case List(1, 2, 3) :+ 88 => // infix pattern
  }

  // 6. Type specifiers
  val unknown: Any = 2
  val unknownMatch: Unit = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7. Name binding
  val nameBindingMatch: Unit = aList match {
    case nonEmptyList@ConsV5(_, _) => // name binding => use the name later(here)
    case ConsV5(1, rest@ConsV5(2, _)) => // name binding inside nested patterns
  }

  // 8. Multi-patterns
  val multiPattern: Unit = aList match {
    case EmptyV5 | ConsV5(0, _) => // compound (multi) pattern
    case _ =>
  }

  // 9. If guards
  val secondElementSpecial: Unit = aList match {
    case ConsV5(_, ConsV5(specialElement, _)) if specialElement % 2 == 0 =>
  }

  // All.

  /*
    Question
   */
  val numbers = List(1, 2, 3, 4)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int] => "a list of numbers"
    case _ => ""
  }
  println(numbersMatch) // "a list of strings" (JVM trick question!)
}
