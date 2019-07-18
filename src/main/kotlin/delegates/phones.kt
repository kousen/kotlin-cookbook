package delegates

interface Dialable {
    fun dial(number: String): String
}

class Phone : Dialable {
    override fun dial(number: String) =
        "Dialing $number..."
}

interface Snappable {
    fun takePicture(): String
}

class Camera : Snappable {
    override fun takePicture() =
        "Taking picture..."
}

class SmartPhone(
    private val phone: Dialable = Phone(),
    private val camera: Snappable = Camera()) :
    Dialable by phone, Snappable by camera

fun main() {
    val myPhone = SmartPhone()
    println(myPhone.dial("555-1234"))
    println(myPhone.takePicture())
}

