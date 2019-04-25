package functional

class Resource private constructor() {
    companion object {
        fun use(f: (Resource) -> Unit) {
            val resource = Resource()
            try {
                f(resource)
            } finally {
                resource.close()
            }
        }
    }

    fun op1() = println("op1 called...")
    fun op2() = println("op2 called...")

    private fun close() = println("closing resource")
}
