@file:Suppress("FunctionName")

package operators

import kotlin.math.pow

infix fun Int.`**`(x: Int) = toDouble().pow(x).toInt()
infix fun Long.`**`(x: Int) = toDouble().pow(x).toLong()
infix fun Float.`**`(x: Int) = pow(x)
infix fun Double.`**`(x: Int) = pow(x)

fun Int.pow(x: Int) = `**`(x)
fun Long.pow(x: Int) = `**`(x)