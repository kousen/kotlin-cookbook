package oop

class Task1(val name: String) {
    var priority = 3
        set(value) {
            field = value.coerceIn(1..5)
        }

    val lowPriority
        get() = priority < 3
}