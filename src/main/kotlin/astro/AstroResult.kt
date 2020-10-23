package astro

import kotlinx.serialization.Serializable

@Serializable
data class AstroResult(
    val message: String,
    val number: Int,
    val people: List<Assignment>
)