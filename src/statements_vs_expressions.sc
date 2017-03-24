import java.time.{DayOfWeek, LocalDateTime}

case class UserName(
  var firstName: String,
  var lastName: String
)

/**
 * Statements are side-effecting, expressions return
 * things. Here are two examples of a statement vs. an
 * expression doing the same thing
 */
var newUser: UserName = null

val includeLastName = true

if (includeLastName) {
  newUser = UserName(
    firstName = "boaty",
    lastName = "boatface"
  )
} else {
  newUser = UserName(
    firstName = "boaty",
    lastName = ""
  )
}

val newUser2 = if(includeLastName) {
  UserName(
    firstName = "boaty",
    lastName = "boatface"
  )
} else {
  UserName(
    firstName = "boaty",
    lastName = ""
  )
}

println(
  s"""
    |Side effecting user: $newUser
    |Expression user: $newUser2
  """.stripMargin
)


/**
 * Your turn. Below is a function that will tell you
 * if today is Tuesday. Based on this function, write:
 *  - A statement that adds '2' to the end of the 'statementNumbers'
 *    sequence if today is tuesday
 *  - An expression that returns a sequence containing 1 if it is
 *    not Tuesday, or a sequence containing 1, 2 if today is tuesday
 *
 *
 * Use the test cases at the bottom to validate your work
 */
def isTuesday: Boolean = {
  LocalDateTime.now().getDayOfWeek == DayOfWeek.TUESDAY
}

var statementNumbers: Seq[Int] = Seq(1)

// Implement with an expression
val expressionList: Seq[Int] = ???

assert(
  statementNumbers.forall(expressionList.contains),
  "The lists should have the same elements!"
)

assert(
  if (isTuesday) {
    2 == statementNumbers.size
    2 == expressionList.size
  } else {
    1 == statementNumbers.size
    1 == expressionList.size
  },
  "On Tuesdays, for important business reasons, we set should have 2 elements in the seq"
)