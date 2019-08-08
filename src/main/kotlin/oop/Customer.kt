package oop

class Customer(val name: String) {

// Version 1:
//    private var _messages: List<String>? = null
//
//    val messages: List<String>
//        get() {
//            if (_messages == null) {
//                _messages = loadMessages()
//            }
//            return _messages!!
//        }

    // Version 2:
    val messages: List<String> by lazy { loadMessages() }

    private fun loadMessages(): MutableList<String> =
        mutableListOf(
            "Initial contact",
            "Convinced them to use Kotlin",
            "Sold training class. Sweet."
        ).also { println("Loaded messages") }
}