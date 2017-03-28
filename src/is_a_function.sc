// Is this a function?
def append[A](list: List[A], item: A): List[A] = list :+ item

// Is this a function?
import scala.collection.mutable
def append[A](list: mutable.MutableList[A], item: A): mutable.MutableList[A] = list += item

// Is this a function?
def line: String = scala.io.StdIn.readLine()

// Is this a function?
def randomInt: Int = scala.util.Random.nextInt

// Is this a function?
def error(): Unit = throw new RuntimeException()