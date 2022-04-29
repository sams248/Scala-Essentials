package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // Try handles Exceptions similar to how Option handles null

  // Create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super Failure"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No String for you today!")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // Syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // Utilities
  println(potentialFailure.isSuccess) // false
  println(potentialFailure.isFailure) // true

  // orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry) // Success(A valid result)

  // If you design the API, if you know that your code might throw and exception, wrap your computation in a Try
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("A valid result!")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2)) // Success(6)
  println(aSuccess.flatMap(x => Success(x * 10))) // Success(30)
  println(aSuccess.filter(_ > 10)) // Failure(java.util.NoSuchElementException: Predicate does not hold for 3)
  // Filter can turn Success into Failure

  /*
    Exercise
   */
  val host = "localhost"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      // Simulate a flaky connection
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted!")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Port taken...")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] = {
      Try(getConnection(host, port))
    }
  }

  // If you get the html page from the connection, print it to the console, i.e. call renderHTML
  // 1. define safe APIs -> getSafe & getSafeConnection
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML = possibleConnection.flatMap(connection => connection.getSafe("/whatever"))
  possibleHTML.foreach(renderHTML)

  // Shorthand version
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/whatever"))
    .foreach(renderHTML)

  // for-comprehensions
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/whatever")
  } renderHTML(html)

/*
    imperative language equivalent (nested Trys)
    try {
      val connection = HttpService.getConnection(host, port)
      try {
        val page = connection.get("/whatever")
        renderHTML(page)
      } catch (some other exception) {

      } catch (some exception) {

      }
    }
 */
}
