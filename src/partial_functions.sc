/**
 * Functions take some number of "things" and return another type
 * of "thing". Examples include:
 *
 *   - A function to udpate a user that might look like:
 *     (Future[User], UpdateRequst) => Future[User]
 *
 *   - A function to multiply two numbers that might look like:
 *     (Int, Int) => Int
 *
 *   - A function to remove bad words from a string:
 *     String => String
 *
 *   - A function to detect if a user is infact Kevin Spacey
 *     User => Boolean
 *
 * and importantly, functions must handle the entire domain of their
 * input types. This is a very silly way to say something quite simple:
 *
 *   for any valid input type, a function must produce an output
 *
 * This means:
 *  - If I pass any user object into my function that detects if a user is
 *     in fact acclaimed actor Kevin Spacey, is must return a Boolean,
 *     always, every day, every time, forever
 *
 *  - If I pass any two numbers into my function that multiplies two numbers, it must
 *     return another number, always, every day, every time, forever
 *
 * But that can get a little bit annoying. For example, lets say
 * that our User trait (we will assume it is a trait) has two concrete
 * implementations:
 */
trait User
case class FamousPerson(
  firstName: String,
  lastName: String,
  isHouseOfCardsCastMember: Boolean = false
) extends User
class GlueLickingIdiot(nameOrWhatever: String) extends User

/**
 * Now, it is more than clear to anyone that Kevin Spacey is not only a talented
 * actor, but a rather famous person. It would also be a bit of a pain to have
 * to write a function to detect whether or not someone really was Kevin Spacey
 * that looked at all Users, because it is quite obvious that Kevin Spacey
 * is not a memebr of the GlueLickingIdiot class. To solve that deliema,
 * we can use a PartialFunction[User, Boolean]
 */
val isKevinSpacey: PartialFunction[User, Boolean] = {
  case f: FamousPerson =>
    f.firstName == "Kevin" &&
    f.lastName == "Spacey" &&
    f.isHouseOfCardsCastMember
}

/**
 * A partial function, unlike a regular function, does not have to
 * handle all possible inputs that could be passed to it. notice
 * that isKevinSpacey partial function has a case statement that
 * only handles FamousPerson and completely ignores GlueLickingIdiot
 *
 * A significantly more practical usage of this is the .collect{} method
 * that is available on Scala collections. It accepts a partial function
 * of whatever is in the collection, and returns a list of whatever is
 * returned. The result of which is you can compress a .filter().map() into
 * something more degnified and elegant. Compare:
 */
val maybeNumbers = Seq(Some(1), Some(34), None, Some(-87), None)

val meh: Seq[Int] = maybeNumbers.filter(_.isDefined).map(_.get)
val elegant: Seq[Int] = maybeNumbers.collect{ case Some(i) => i }

/**
 * This is truely a contrived example, but when
 * the filtering logic gets nasty, this is a life saver. For those of
 * you who work with compiled thrift objects, you can use a similar
 * destructuring approach to pull out deeply nested things
 *
 * also note that we can avoid calling .get() in the second case,
 * which is a smell in Scala code.
 * Write a partial function that operates on Try[String] and that returns
 * the string in the successful case, ignoring the failed case
 */
val myParial: PartialFunction[Try[String], String] = ???
