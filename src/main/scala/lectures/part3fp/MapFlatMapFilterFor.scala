package lectures.part3fp

object MapFlatMapFilterFor extends App {
  val list = List(1, 2, 3) // equivalent to List.apply(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // Pair all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List("a", "b", "c", "d")
  val colors = List("black", "white")
  // iterating
  val combinations = numbers.flatMap(num => chars.map(char => {
    char + num
  }))
  println(combinations)
  val allCombinations = numbers.flatMap(num => chars.flatMap(char => colors.map(color => char + num + "-" + color)))
  println(allCombinations)

  // foreach
  list.foreach(println)

  // for-comprehension
  val forCombinations = for {
    num <- numbers if num % 2 == 0 // guarding (filter used behind the hood)
    char <- chars
    color <- colors
  } yield char + num + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
    1. Check if MyList supports for comprehensions? => MyListV5
         functions should have the following signatures:
          - map(f: A => B) => MyList[B]
          - filter(p: A => Boolean) => MyList[A]
          - flatMap(f: A => MyList[B]) => MyList[B]
    2. A small collection of at most ONE element - Maybe[+T] => class Maybe in exercises
        - map, flatMap, filter
   */
}
