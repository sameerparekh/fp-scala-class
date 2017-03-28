// Algebraic Data Types
sealed trait Tree[+A]
case class Leaf[A](element: A) extends Tree[A]
case class Node[A](
  element: A,
  children: Set[Tree[A]]
) extends Tree[A]

// Exercise
val myTree =
  Node[Int](1, Set(Leaf[Int](2), Node[Int](4, Set(Leaf[Int](3), Leaf[Int](5)))))


// Pre-order DFS traversal
def contents[A](tree: Tree[A]): List[A] = ???


contents(myTree)


// Try this
//def badContents[A](tree: Tree[A]): List[A] = tree match {
//  case Leaf(value) => List(value)
//}