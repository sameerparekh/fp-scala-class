/**
 * Immutable data is great and all, but it does make
 * classes harder to modify. Here is an example:
 */

class FlyingMachine(
  val maxAirSpeedInMetersPerSecond: Int,
  val color: String,
  val isExpensive: Boolean
)

val aCheapFlyingMachine = new FlyingMachine(
  50,
  "taupe",
  false
)

val aGreenCheapFlyingMachine = new FlyingMachine(
  aCheapFlyingMachine.maxAirSpeedInMetersPerSecond,
  "green",
  aCheapFlyingMachine.isExpensive
)

/**
 * Yuck, look at all that typing. All those keystrokes cost me a lot
 * of time and now my feature is late to ship because of it. How could we
 * rewrite this logic using a case class and the .copy() method?
 */

case class BetterFlyingMachine(
  maxAirSpeedInMetersPerSecond: Int,
  color: String,
  isExpensive: Boolean
)

val aCheapBetterFlyingMachine: BetterFlyingMachine = BetterFlyingMachine(
  50,
  "taupe",
  isExpensive = false
)

/**
 * Create a new version of aCheapBetterFlyingMachine but make it blue
 * using .copy() method. Use the test below to help.
 */
val aBlueCheapBetterFlyingMachine: BetterFlyingMachine = aCheapBetterFlyingMachine.copy(color = "blue")

assert(
  "blue" == aBlueCheapBetterFlyingMachine.color,
  s"Hey, I said I wanted it blue not ${aBlueCheapBetterFlyingMachine.color}."
)
