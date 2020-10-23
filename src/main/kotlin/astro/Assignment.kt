package astro

import kotlinx.serialization.Serializable

@Serializable
data class Assignment(
    val name: String,
    val craft: String
)
