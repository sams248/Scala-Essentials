package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // Package is a bunch of definitions grouped under the same name

  // package members are accessible by their simple name (if in the same package)
  val writer = new Writer("John", "Snow", 2022)

  // import the package (if in another package)
  val princess = new Princess
  // If you want to avoid the import use fully qualified name: val princess2 = new playground.Cinderella

  // packages are in hierarchy: package -> sub-package
  // matching folder structure

  // package objects (very rarely used in practice)
  sayHello()
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1.use fully qualified name
  // val sqlDte = new java.sql.Date(2022, 2,1)
  val date = new Date
  // 2. use aliasing
  val sqlDate = new SqlDate(2022, 2, 1)

  /*
     Default imports:
       java.lang - String, Object, Exception
       scala - Int, Nothing, FUnction
       scala.Predef - println, ???
   */

}
