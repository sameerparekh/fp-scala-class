import scala.util.{Try, Failure, Success}
import java.lang.NumberFormatException

/**
 * Lets say we have a method multiplyTwoInts(a: Int, b: Int) and,
 * like the name implies, it takes the two numbers and multiples them
 * together, we could use it like so:
 */

val multipleTwoNumbers: (Int, Int) => Int = (x, y) => x*y

val twoTimesThree = multipleTwoNumbers(2, 3)
val sevenTimesThree = multipleTwoNumbers(7, 3)
val oneHunderesTimesThree = multipleTwoNumbers(100, 3)

/**
 * you will notice that there is a lot of duplication
 * in that code. we always type multipleTwoNumbers(<SOMETHING>, 3).
 * Scala (and other functional languages) have a few ways to cut down
 * on the duplication. The first is partially applying. Partially applying is the process
 * of specifying some of the arguments of a function, and allowing
 * the remaining arguments to be provides at a later time. Here
 * is a use of that:
*/

val timesTwo: Int => Int = multipleTwoNumbers(2, _: Int)

println(timesTwo(4))
println(timesTwo(8))

/**
 * Woah, thats neat. I took a function, and I created another function based
 * on the original function, with an argument specified. Lets break that down:
 *
 * val timesTwo: Int => Int = multipleTwoNumbers(2, _: Int)
 *
 * first we can see the type of this new function is Int => Int, meaning
 * it takes an integer and returns and integer. next we see that this fucntion
 * is equal to multipleTwoNumbers, however the first argument is set to 2,
 * while the second argument is set to be "some other int". Note that you
 * can partially apply functions with arbitrary paramater lenghts in arbirary positions
 * like so:
 */
val nonSensicalFunction: (Int, String, Try[Long]) => Try[String] = (int, string, tried) => {
  if (int > 0) {
    println(s"""
              |My PM said if the number is > 0 we should
              |print out the number to maximize DAU or whatever.
              |Here is the string: $string""".stripMargin
    )
    Failure(new RuntimeException("LOL this code is arbirary"))
  } else {
    println("Because this is sample code, I can do whatever I want")
    tried.map(_.toInt).map(_.toString)
  }
}

val sillyFunction: String => Try[String] =
  nonSensicalFunction(100, _: String, Success(Long.MaxValue))

// Notice that sillyFunction partially applies the 1st and 3rd parameters
sillyFunction("I love curry")

/**
 * There is another somewhat similar way to achieve this functionality
 * in Scala (and other languages) using higher order functions.
 * This is a little different to partially applying because
 * we can only call higher order functions from their first parameter to their last
 * in order, and it is predicated on the fact that:
 *  - Functions are first class and as such:
 *  - A function can return another function
 *
 * sounds trippy. Lets see that in action
 */

val serioiusBusinessLogic: Int => String => Try[Double] = int => {
  string => {
    val res = Try(string.toInt)
      .map(_.toDouble)
      .recover{ case _: NumberFormatException => 0.0 }
    if (res.isSuccess) {
      println(s"Just printing the string out because I'm sassy $string")
    } else {
      println(s"LOL it failed! I suck at computers: $res")
    }
    res
  }
}

val serioiusBusinessLogicWith1: String => Try[Double] = serioiusBusinessLogic(1)

val partiallyApplied = serioiusBusinessLogicWith1("great string!")

val wholeyApplied = serioiusBusinessLogic(1)("great string!")

assert(
   partiallyApplied == wholeyApplied,
  "These really should be equal"
)

/**
 * What is going on with line 93, 95, and 97? It sure sorta reminds me of partially applying,
 * but its different. And different is scary.
 *
 * Here is whats happening line by line:
 *
 * val serioiusBusinessLogicWith1: String => Try[Double] = serioiusBusinessLogic(1)
 *
 *  - This line takes our function `serioiusBusinessLogic` and calls it with the
 *    value 1
 *  - We assign the value of that to `serioiusBusinessLogicWith1` which is
 *    itself a function!
 *
 * val partiallyApplied = serioiusBusinessLogicWith1("great string!")
 *
 *  - This line calls our partially applied function with yet
 *    another argument ("great string!") which returns a Try[Double]
 *
 * val wholeyApplied = serioiusBusinessLogic(1)("great string!")
 *
 *  - This line shows us that whether we can choose to specify
 *    all arguments at the call sight if we wanted to, which is
 *    totally chill
 *  - We assert that the results really are equal. Fun!
 *
 * Your task is to create two functions that do the same thing. Both that accept a single string
 * and return a Seq[String] based on addTooAListPartiallyApplyMe and addToListHigherOrder
 * such that the Seq[String] passed in is the provided `initialList`, and the first
 * String is "muskrat". The first implementation should be done by partial appliction,
 * the other by using the provided higher order function.
 */

val initialList = Seq("dog", "cat", "bear")

val addTooAListPartiallyApplyMe: (Seq[String], String, String) => Seq[String] = (seq, str1, str2) => {
  seq :+ str1 :+ str2
}

val addToListHigherOrder: Seq[String] => String => String => Seq[String] = seq => {
  str1 => {
    str2 => {
      seq :+ str1 :+ str2
    }
  }
}

val partiallyAppliedVersion: String => Seq[String] =
  addTooAListPartiallyApplyMe(initialList, "hi", _: String)


val higherOrderVersion: String => Seq[String] = addToListHigherOrder(initialList)("")

assert(
  partiallyAppliedVersion("manbearpig") == higherOrderVersion("manbearpig"),
  "Just trying to add the manbearpig"
)

/**
 * Oh, and if you are feeling hot to trot, you can also
 * combinine these two techniques, as seen below
 *
 * Note however that this produces a Function2 (a fancy way of saying that
 * it creates a function with 2 parameters, v.s. a function which
 * returns a function) where you may instead expect this to return something that
 * looked more like:
 *
 * Int => Try[Double] => Option[String] (function1) vs (Int, Try[Double]) => Option[String] (function2)
 *
 * an insight which you may or may not find surprising,
 * confusing, interesting, or totally inconsiquential
*/
val lotsOfParameters: Int => String => Int => Try[Double] => Option[String] = {
  int => {
    long => {
      str => {
        tried =>
          None
      }
    }
  }
}

val iAmASicko = lotsOfParameters(_: Int)("lol")(7)(_: Try[Double])

/**
 * Attempt to define the .curried function of this trait. How could you maybe
 * do this in a somewhat recursive fashion by leveraging
 * Function2[A, B] (which also supports .curried)
*/
trait Curryable[A, B, C, D] {

  def apply(a: A, b: B, c: C): D

  def curried: A => B => C => D = ???

}
