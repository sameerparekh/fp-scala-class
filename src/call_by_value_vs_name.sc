// Call-by-value
def perform1(action: Int) = { println("Before"); action }

perform1 {
  println("Computing our integer")
  42
}

// Call-by-name
def perform2(action: => Int) = { println("Before"); action }

perform2 {
  println("Computing our integer")
  42
}


