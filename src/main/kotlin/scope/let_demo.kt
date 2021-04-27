package scope

import java.util.*

fun processNullableString(str: String?) =
    str?.let { s ->
        when {
            s.isEmpty() -> "Empty"
            s.isBlank() -> "Blank"
            else -> s.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault())
                else it.toString()
            }
        }
    } ?: "Null"

fun processString(str: String) = when {
    str.isEmpty() -> "Empty"
    str.isBlank() -> "Blank"
    else -> str.replaceFirstChar {
        if (it.isLowerCase())
            it.titlecase(Locale.getDefault())
        else it.toString()
    }
}

