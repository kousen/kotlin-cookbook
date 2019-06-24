package javaintegration

import java.io.IOException

@Throws(IOException::class)
fun houstonWeHaveAProblem() {
    throw IOException("Nope")
}