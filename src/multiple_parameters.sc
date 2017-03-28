// Functions with multiple parameters
val add1 = (i: Int) => (j: Int) => i + j

add1(4)(4)

val add2 = (i: Int, j: Int) => i + j

add2(4, 4)
