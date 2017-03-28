val inc1 = (i: Int) => i + 1

val inc2: Int => Int = i => i + 1

val inc3: Int => Int = _ + 1

val inc4: Function[Int, Int] = _ + 1

val inc5 = new Function[Int, Int] {
  def apply(i: Int): Int = i + 1
}