package scripts

import java.net.InetAddress
import kotlin.system.measureTimeMillis

fun main() {
    var hostName = "localhost"
    val time = measureTimeMillis {
        hostName = InetAddress.getLocalHost().hostName
    }
    println("Accessing $hostName takes $time ms")
}