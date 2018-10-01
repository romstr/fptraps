package b_tailrec

import java.math.BigInteger

fun main(vararg args: String) {
    listOf(5, 10, 100, 1000, 10000)
            .forEach {
                println("tailrec factorial($it): ${tailFactorial(it.toBigInteger())}")
                println("factorial($it): ${factorial(it.toBigInteger())}")
            }
}

fun factorial(n: BigInteger): BigInteger {
    return when (n) {
        BigInteger.ONE -> BigInteger.ONE
        else -> n.multiply(factorial(n.minus(BigInteger.ONE)))
    }
}

tailrec fun tailFactorial(n: BigInteger, acc: BigInteger = BigInteger.ONE): BigInteger {
    return when (n) {
        BigInteger.ONE -> acc
        else -> tailFactorial(n.minus(BigInteger.ONE), acc.multiply(n))
    }
}

fun Int.toBigInteger() = BigInteger(this.toString())