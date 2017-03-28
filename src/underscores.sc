// Underscores
val xs = List(1, 2, 3)

xs.map(y => y + 1)       // Fine

xs.map(_ + 1)            // Fine

xs.map(y => y)           // Fine

//xs.map(_)                // Doesn't work

xs.map(x => 1 + (x * 4)) // Fine

// xs.map(1 + (_ * 4))      // Doesn't work