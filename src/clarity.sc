// Clarity

// Pain
val orig = Array(1, 2, 3)
val values = new Array[Int](orig.length)
for (i <- orig.indices) {
  values(i) = orig(i) + 1
}

// Joy
val orig2 = List(1, 2, 3)
val values2 = orig.map(_ + 1)