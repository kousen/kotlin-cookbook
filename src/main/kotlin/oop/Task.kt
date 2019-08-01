package oop

class Task(val name: String, _priority: Int = DEFAULT_PRIORITY) {

    companion object {
        const val MIN_PRIORITY = 1
        const val MAX_PRIORITY = 5
        const val DEFAULT_PRIORITY = 3
    }

    var priority = validPriority(_priority)
        set(value) {
            field = validPriority(value)
        }

    private fun validPriority(p: Int) =
        p.coerceIn(MIN_PRIORITY, MAX_PRIORITY)


//    private fun validPriority(p: Int) = when {
//        p < MIN_PRIORITY -> MIN_PRIORITY
//        p > MAX_PRIORITY -> MAX_PRIORITY
//        else -> p
//    }
}