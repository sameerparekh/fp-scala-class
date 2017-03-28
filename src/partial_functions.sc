// Partial Functions
val stringToInt: PartialFunction[String, Int] = {
  case s if s.forall(_.isDigit) => s.toInt
}

List("123", "abc", "4").collect(stringToInt)

// Combining two partial functions
val lengthOfString: PartialFunction[String, Int] = {
  case s => s.length
}

List("123", "abc", "4").map(stringToInt orElse lengthOfString)
