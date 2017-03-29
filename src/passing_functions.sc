/**
 * In Scala, functions are 'first class' meaning we
 * can pass them around like we would pass around other things.
 * For those of you coming from Javaland, you may be familiar
 * with Runnable, which is similar. Runnable allows
 * you to wrap up some functionality and pass it around
 * to be run later. Here is a simple example of passing a function
 */

val printANumber: Int => Unit = x => println(x)

Seq(1, 2, 3, 4) foreach printANumber

/**
 * Whats happening here? We are passing a function named `printANumber`
 * into a function called `foreach` which is called on a Seq. The
 * signature off foreach is:
 *
 *  def foreach[U](f: A => U): Unit
 *
 *  Breaking that down, foreach takes a function named `f` which takes
 *  an A (defined in the Seq) and returns a U (defined by the function)
 *
 *  In our case, the A is Int, the U is Unit
 *
 *  Implement `newFunction` to convert a number into a string. Look
 *  at what gets printed out, was it what you expected? Notice how
 *  we are passing the function newFunction into a function called `map`.
 *  `map` takes every element in the sequence of `oneHundredEvenNumbers`
 *  and passes it into the function we pass into it. Notice that
 *  `newFunction` is just a function, we didn't have to create a new instance
 *  of a class to hold it, functions are free to roam in Scala. They can
 *  be created, passed around, and returned without need of a wrapping object
 *  such as Runnable
 */

val newFunction: Int => String = ???

val oneHundredEvenNumbers: Seq[Int] = Stream.from(1)
  .filter(x => 0 == x % 2 )
  .take(100)

val asOneLongString = oneHundredEvenNumbers map newFunction mkString "\n"

println(s"Here are all the values as one long string:\n$asOneLongString")

/**
 *
 * Another idiomatic Scalaism is to extend functions
 * them to create logic classes of your own. Lets say we have to write
 * some logic to render templates based on a configuration. If we be in Javaland
 * we might write something like this:
 */

//class JavaRenderer {
//
//  public String render(
//    RenderConfig config,
//    Template template,
//    Map<String, String> values
//  ) {
//    // Stuff goes here
//  }
//
//}

// but in Scalaland, we might actually want do write something like this:

//class Renderer extends (RenderConfig, Template, Map[String, String]) => String {
//
// def apply(
//   config: RenderConfig,
//   template: Template,
//   values: Map[String, String]
// ): String = {
//   // Stuff goes here
// }
//
//}

/**
 * and actually have our class extend a Function3, instead of having
 * to define a custom method name
 * "But what exactly does this get us?" I hear you ask. Well, let me tell you:
 *
 *  - you can easily compose functions together using .andThen()
 *  - you get .curried for free, in case you want to eaily build multiple
 *    types of formatters
 *  - no having to come up with redundant class/function names (i.e. a Render class with
 *    single .render() method)
 *  - since its not just a plain Function3[(RenderConfig, Template, Map[String, String]), String]
 *    you can inject it easily without using @Named() for all you Guicers out
 *    there
 */
