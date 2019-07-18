package scope

fun processString(str: String?) =
    str?.let {
        when {
            it.isEmpty() -> "Empty"
            it.isBlank() -> "Blank"
            else -> it.capitalize()
        }
    } ?: "Null"

fun main() {
    println(processString("Hello, World!"))
    println(processString(""))
    println(processString("\t"))
    println(processString(null))
}