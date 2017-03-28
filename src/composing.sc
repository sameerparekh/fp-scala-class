// Composing functions
import scala.util.Try

val parseInt: String => Try[Int] = s => Try(s.toInt)

val incInTry: Try[Int] => Try[Int] = _.map(_ + 1)

val parseAndInc = parseInt andThen incInTry
