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
