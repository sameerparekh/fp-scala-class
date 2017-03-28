// Rewrite this functionally
def average(list: List[Double]): Double = {
  var sum: Double = 0
  for (i <- list.indices) {
    sum += list(i)
  }
  sum / list.size
}

def averageFunctional(list: List[Double]): Double = ???