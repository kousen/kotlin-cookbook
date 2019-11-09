package misc

fun main() {
    println("The current Kotlin version is ${KotlinVersion.CURRENT}")

    println("${1 shl 16}, ${3 shl 8}, ${50}")
    println((1 shl 16) + (3 shl 8) + 50)
}