package coroutines

import kotlinx.coroutines.flow.flow
import java.math.BigInteger

// Suggested by Tim Yates on Twitter, 16 Dec 2019
fun fibflow() = flow {
    var p = Pair(BigInteger.ZERO, BigInteger.ONE)
    emit(p.first)
    while (true) {
        emit(p.second)
        p = Pair(p.second, p.first + p.second)
    }
}