import scala.collection.mutable

val immutableList = List(1, 2, 3)
val mutableList = mutable.MutableList(2, 3, 4)
val newList = immutableList :+ 5 // creates a new list
mutableList += 5 // appends to the existing list
val newMutableList = mutableList :+ 6 // creates a new list