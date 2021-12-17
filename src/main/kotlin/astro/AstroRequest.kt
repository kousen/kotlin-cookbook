package astro

import com.google.gson.Gson
import serialization.AstroResult
import java.net.URL

class AstroRequest {
    companion object {
        private const val ASTRO_URL = "http://api.open-notify.org/astros.json"
    }

    operator fun invoke(): AstroResult =
        Gson().fromJson(URL(ASTRO_URL).readText(), AstroResult::class.java)
}
