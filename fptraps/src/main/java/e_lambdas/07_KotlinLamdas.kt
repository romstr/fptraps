package e_lambdas

import com.google.common.math.DoubleMath

fun main(vararg args: String) {
    (1..100).filter { checkIsInteger(it, Math::sqrt) }
            .filter { checkIsInteger(it) { a -> a / 5 } }
            .forEach(::println)
}

fun checkIsInteger(number: Int, convert: (Double) -> Double) =
        DoubleMath.isMathematicalInteger(convert(number.toDouble()))