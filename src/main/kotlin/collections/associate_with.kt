package collections

import java.util.*

// From tweet on @Kotlin feed
// Also, https://kotlinlang.org/docs/reference/whatsnew13.html#associatewith

@Suppress("ReplaceAssociateFunction")
fun <T> repeatAndCapitalizeUsingAssociate(keys: Iterable<T>) =
    keys.associate { it to it.toString().repeat(5)
        .replaceFirstChar { letter ->
            if (letter.isLowerCase()) letter.titlecase(Locale.getDefault()) else it.toString()
        }
    }

fun <T> repeatAndCapitalizeUsingAssociateWith(keys: Iterable<T>) =
        keys.associateWith { it.toString().repeat(5).capitalize() }

fun main(args: Array<String>) {
    val keys = 'a'..'f'
    val map = repeatAndCapitalizeUsingAssociate(keys)
    println(map)

    val map1 = repeatAndCapitalizeUsingAssociateWith(keys)
    println(map1)
}
