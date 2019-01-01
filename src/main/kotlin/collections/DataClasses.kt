package collections

import java.time.LocalDate

data class Product(val name: String, var price: Double, var onSale: Boolean = false)

data class AstroResult(
    val message: String,
    val number: Number,
    val people: List<Assignment>
)

data class Assignment(
    val craft: String,
    val name: String
)

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val published: LocalDate
)

