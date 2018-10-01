package a_recursion

val audience = listOf("John", "Jill", "Janis")

fun main(vararg args: String) {
    println("Loop:\n")
    loopPrint(audience)

    println("\nRecursion:\n")
    recursivePrint(audience)
}

fun loopPrint(audience: List<String>) {
    for (listener in audience) {
        println(listener)
    }
}

fun recursivePrint(audience: List<String>) {
    if (audience.isNotEmpty()) {
        println(head(audience))
        recursivePrint(tail(audience))
    }
}

fun <T> head(list: List<T>) = list.first()

fun <T> tail(list: List<T>) = list.drop(1)