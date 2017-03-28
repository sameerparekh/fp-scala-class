import scala.util.Try

def parseInt(s: String) = Try(s.toInt)

def addStrings(s1: String, s2: String): Try[Int] =
  for {
    x <- parseInt("123")
    y <- parseInt("456")
  } yield x + y