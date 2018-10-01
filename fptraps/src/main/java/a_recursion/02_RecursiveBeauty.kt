package a_recursion

fun main(vararg args: String) {
    (2..10).forEach { println("fibonacci($it): ${fibonacci(it)}") }
    (1..5).forEach { println("factorial($it): ${factorial(it)}") }
}

fun fibonacci(n: Int): Int {
    return when (n) {
        0, 1 -> n
        else -> fibonacci(n - 1) + fibonacci(n - 2)
    }
}

fun factorial(n: Int): Int {
    return when (n) {
        1 -> 1
        else -> n * factorial(n - 1)
    }
}
