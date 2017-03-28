/**
 * Immutable data is great and all, but it does make
 * classes harder to modify. Here is an example:
 */

class WindCar(
  val maxAirSpeedInMetersPerSecond: Int,
  val color: String,
  val isExpensive: Boolean
)

val aCheapWindCar = new WindCar(
  50,
  "taupe",
  false
)

val aGreenCheapWindCar = new WindCar(
  aCheapWindCar.maxAirSpeedInMetersPerSecond,
  "green",
  aCheapWindCar.isExpensive
)

/**
 * Yuck, look at all that typing. All those keystrokes cost me a lot
 * of time and now my feature is late to ship because of it. How could we
 * rewrite this logic using a case class and the .copy() method?
 */

case class BetterWindCar(
  maxAirSpeedInMetersPerSecond: Int,
  color: String,
  isExpensive: Boolean
)

val aCheapBetterWindCar: BetterWindCar = BetterWindCar(
  50,
  "taupe",
  isExpensive = false
)

/**
 * Create a new version of aCheapBetterWindCar but make it blue
 * using .copy() method. Use the test below to help.
 */
val aBlueCheapBetterWindCar: BetterWindCar = ???

assert(
  "blue" == aBlueCheapBetterWindCar.color,
  s"Hey, I said I wanted it blue not ${aBlueCheapBetterWindCar.color}."
)
