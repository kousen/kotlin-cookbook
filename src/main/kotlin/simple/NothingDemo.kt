package simple

import java.lang.IllegalArgumentException

fun moveTo(location: String): Nothing = when (location) {
    "New York" -> throw IllegalStateException("Too many Yankee fans")
    "Silicon Valley" -> throw IllegalArgumentException("Are you kidding? Who can afford that?")
    else -> throw Exception("whatever")
}