
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
def error: Unit = throw new RuntimeException()

// Clarity

// Pain
val orig = Array(1, 2, 3)
val values = new Array[Int](orig.size)
for (i <- 0 until orig.size) {
  values(i) = orig(i) + 1
}

// Joy
val orig2 = List(1, 2, 3)
val values2 = orig.map(_ + 1)

// Rewrite this functionally
def average1(list: List[Double]): Double = {
  var sum: Double = 0;
  for (i <- 0 until list.size) {
    sum += list(i)
  }
  sum / list.size
}

// What’s the pure implementation of this method?
def foo[A](a: A): A = ???

// What's the pure implementation of this method?
def bar[A](xs: List[A]): Int = ???

// Expressions vs statements
// Statement
var i1 = 0

if (true) { i1 = 1 } else { i1 = 2 }

// Expression
val i2 = if (true) 1 else 2

// Var vs val
var x: Int = 3
val y: Int = 5
x = 5
// y = 6 // Not allowed

// Functions with multiple parameters
val add1 = (i: Int) => (j: Int) => i + j

add1(4)(4)

val add2 = (i: Int, j: Int) => i + j

add2(4, 4)

// Partial application
def isCorrect[A] = (allowed: Set[A]) => (s: Seq[A]) => s.forall(allowed.contains)
isCorrect("012345".toSet)("10923") // false
isCorrect("012345".toSet)("1023")  // true

val isDigits = isCorrect("0123456789".toSet)
val isAs = isCorrect(Set('A'))

isDigits("218903")  // true
isAs("218903")      // false
isDigits("AAAAAAA") // false
isAs("AAAAAAA")     // true

// Currying
val isCorrectUncurried: (Set[Int], Seq[Int]) => Boolean =
  (allowed, s) => s.forall(allowed.contains)

val curried: Set[Int] => Seq[Int] => Boolean = isCorrectUncurried.curried

val andBackAgain = Function.uncurried(curried)

// Underscores
val xs = List(1, 2, 3)

xs.map(y => y + 1)       // Fine

xs.map(_ + 1)            // Fine

xs.map(y => y)           // Fine

//xs.map(_)                // Doesn't work

xs.map(x => 1 + (x * 4)) // Fine

// xs.map(1 + (_ * 4))      // Doesn't work

// Partial Functions
val stringToInt: PartialFunction[String, Int] = {
  case s if s.forall(_.isDigit) => s.toInt
}

List("123", "abc", "4").collect(stringToInt)

// Combining two partial functions
val lengthOfString: PartialFunction[String, Int] = {
  case s => s.size
}

List("123", "abc", "4").map(stringToInt orElse lengthOfString)

// Composing functions
import scala.util.Try

val parseInt: String => Try[Int] = s => Try(s.toInt)

val incInTry: Try[Int] => Try[Int] = _.map(_ + 1)

val parseAndInc = parseInt andThen incInTry

// Call-by-value
def perform1(action: Int) = { println("Before"); action }

perform1 {
  println("Computing our integer")
  42
}

// Call-by-name
def perform2(action: => Int) = { println("Before"); action }

perform2 {
  println("Computing our integer")
  42
}

// Algebraic Data Types
sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

// Exercise
val myTree =
  Branch(Leaf(4), Branch(Leaf(4), Branch(Leaf(4), Branch(Leaf(1), Leaf(5)))))

def contents[A](tree: Tree[A]): List[A] = ???

contents(myTree)

// Compile error
//def contents2[A](tree: Tree[A]): List[A] = tree match {
//  case Leaf(value) => List(value)
//}
