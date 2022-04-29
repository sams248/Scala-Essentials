package lectures

// example of a package object
// there can only be one per package
// object name will have the same name as the package
// file name will be package.scala (naming convention)
package object part2oop {

  // define methods and constants here and use them by simple name anywhere in the package

  def sayHello():Unit = println("Hello, Scala!")
  val SPEED_OF_LIGHT = 299792458
}
