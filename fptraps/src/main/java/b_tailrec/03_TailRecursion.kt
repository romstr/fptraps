package b_tailrec

fun main(vararg args: String) {
    (1..6).forEach {
        println("$it:")
        println("\tfact1: ${fact1(it)}")
        println("\tfact2: ${fact2(it)}")
        println("\tfact3: ${fact3(it)}")
    }
}

fun fact1(n: Int): Int {
    return when (n) {
        1 -> 1
        else -> n * fact1(n - 1)
    }
}

tailrec fun fact2(n: Int, acc: Int = 1): Int {
    return when (n) {
        1 -> acc
        else -> fact2(n - 1, acc * n)
    }
}

fun fact3(n: Int, acc: Int = 1): Int {
    return when (n) {
        1 -> acc
        else -> fact3(n - 1, acc * n)
    }
}