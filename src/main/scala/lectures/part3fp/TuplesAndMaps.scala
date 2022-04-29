package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  /*
   Tuples = finite ordered "lists"
   In Scala, a tuple is a value that contains a fixed number of elements, each with its own type. Tuples are immutable.
   Tuples are especially handy for returning multiple values from a method.
   */
  val aTuple1 = new Tuple2(2, "Hello Scala") // Tuple2[Int, String] = (Int, String)
  val aTuple2 = Tuple2(2, "Hello Scala")
  val aTuple3 = (2, "Hello Scala")

  println(aTuple1._1)
  println(aTuple1.copy(_2 = "Goodbye Java!"))
  println(aTuple1.swap) // ("Hello Scala", 2)

  // Map: keys -> values
  val aMap: Map[String, Int] = Map()
  val phonebook = Map(("Jim", 555), "Kate" -> 888).withDefaultValue(-1) // default value prevents NoSuchElementException
  // a -> b is syntactic sugar for (a, b)
  println(phonebook)

  // Map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Mary"))

  // Add a pairing
  val newPairing = "Mary" -> 545
  val newPhoneBook = phonebook + newPairing
  println(newPhoneBook)

  // Functional on maps: map, flatMap, filter
  // println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // change the Scala SDK to 2.12 for it to work
  // filterKeys
  print(phonebook.view.filterKeys(_.startsWith("J")).toMap)
  // mapValues
  println(phonebook.view.mapValues(number => number * 10).toMap)

  // Conversions to other collections
  println(phonebook.toList) // List((Jim,555), (Kate,888))
  println(List(("Sam", 333)).toMap) // Map(Sam -> 333)
  val names = List("James", "Brian", "Sam", "Mary", "Dan", "Susan")
  println(names.groupBy(name => name.charAt(0)))
  // HashMap(J -> List(James), M -> List(Mary), B -> List(Brian), D -> List(Dan), S -> List(Sam, Susan))

  /*
    1. What would happened if I had two original entries "Jim" -> 555 and "JIM" -> 900?
    2. Overly simplified social network based on maps
        Person: String
          - add a person to the network
          - remove
          - friend (mutual)
          - unfriend
          - number of friends of a person
          - person with most friends
          - how many people have NO friends
          - if there is a social connection between two people (direct or not)
   */

  // 1
  val phonebook2 = Map(("Jim", 555), ("JIM", 900))
  println(phonebook2)
  // println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // Map(jim -> 900)
  // Be careful with map keys! You might lose data!

  // 2
  val emptyNetwork = Map.empty[String, Set[String]]

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set.empty[String])
  }

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsOfA = network(a)
    val friendsOfB = network(b)

    network + (a -> (friendsOfA + b)) + (b -> (friendsOfB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsOfA = network(a)
    val friendsOfB = network(b)

    network + (a -> (friendsOfA - b)) + (b -> (friendsOfB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }


  val socialNetwork = add(add(emptyNetwork, "Bob"), "Mary")
  println(socialNetwork) // Map(Bob -> Set(), Mary -> Set())
  println(friend(socialNetwork, "Bob", "Mary")) // Map(Bob -> Set(Mary), Mary -> Set(Bob))
  println(unfriend(friend(socialNetwork, "Bob", "Mary"), "Bob", "Mary")) // Map(Bob -> Set(), Mary -> Set())
  println(remove(friend(socialNetwork, "Bob", "Mary"), "Bob")) // Map(Mary -> Set())

  // Let's build a small network with Jim, Bob, and Mary
  val people = add(add(add(emptyNetwork, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if (!network.contains(person)) 0
    else network(person).size
  }

  println(nFriends(testNet, "Bob")) // 2

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }

  println(mostFriends(testNet)) // Bob

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    // network.view.filterKeys(k => network(k).isEmpty).size
    network.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(testNet)) // 0

  def sociallyConnected(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    // Breadth-first search (BFS) is an algorithm for searching a tree data structure for a node that satisfies a given property.
    // It starts at the tree root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level.
    @tailrec
    // Can I find a target in discovered people, having considered people in discoveredPeople already?
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(testNet) // Map(Bob -> Set(Jim, Mary), Mary -> Set(Bob), Jim -> Set(Bob))
  println(sociallyConnected(testNet, "Mary", "Jim")) // true
  println(socialNetwork) // Map(Bob -> Set(), Mary -> Set())
  println(sociallyConnected(socialNetwork, "Mary", "Bob")) // false
}
