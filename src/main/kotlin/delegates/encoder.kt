package delegates

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.logging.Logger
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Encoder(
    var content: String,
    val scheme: Charset = StandardCharsets.UTF_8
) : ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return URLDecoder.decode(content, scheme)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        content = URLEncoder.encode(value, scheme)
        Logger.getAnonymousLogger().info("Encoded $value as $content")
    }
}

fun main() {
    var encoded by Encoder("default")
    println(encoded)

    encoded = "King's Landing, Westeros"
    println(encoded)
}