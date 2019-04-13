package functional

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class AlgorithmBenchmarks {
    @Benchmark
    fun factorial1000() = factorial(1000)
}