// Currying
val isCorrectUncurried: (Set[Int], Seq[Int]) => Boolean =
  (allowed, s) => s.forall(allowed.contains)

val curried: Set[Int] => Seq[Int] => Boolean = isCorrectUncurried.curried

val andBackAgain = Function.uncurried(curried)