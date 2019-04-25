package functional

class Resource private constructor() {
    companion object {
        @JvmStatic
        fun use(f: (Resource) -> Unit) {
            val resource = Resource()
            try {
                resource.open()
                f(resource)
            } finally {
                resource.close()
            }
        }
    }

    fun open() = println("opening resource")
    fun op() = println("op called...")
    fun close() = println("closing resource")
}

object SingletonResource {
    fun use(f: (SingletonResource) -> Unit) {
        try {
            open()
            f(this)
        } finally {
            close()
        }
    }

    fun open() = println("opening resource")

    fun op() = println("op called...")

    fun close() = println("closing resource")
}

//fun Resource.Companion.useResource(f: Resource.(Resource) -> Unit) {
//    try {
//        open()
//        f(this)
//    } finally {
//        close()
//    }
//}