package c_backpressure

import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

private const val MAX = 100000

fun main(vararg args: String) {

    listOf(BackpressureStrategy.BUFFER, BackpressureStrategy.DROP, BackpressureStrategy.LATEST, BackpressureStrategy.ERROR)
            .forEach(::measure)
}

private fun measure(strategy: BackpressureStrategy) {
    val testSubscriber = Observable.fromIterable(1..MAX)
            .toFlowable(strategy)
            .observeOn(Schedulers.computation())
            .test()

    testSubscriber.awaitTerminalEvent()

    println("Strategy: ${strategy.name}")
    println("Consumed: ${testSubscriber.events[0].size} items of $MAX")
    println("Consumed: ${testSubscriber.events[0].size / MAX.toFloat() * 100} %\n")
}