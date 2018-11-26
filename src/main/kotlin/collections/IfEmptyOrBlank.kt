package collections

data class Product(val name: String, var price: Double, var onSale: Boolean = false)

fun onSaleProducts_ifEmptyCollection(products: List<Product>) =
    products.filter { it.onSale }
        .map { it.name }
        .ifEmpty { listOf("none") }
        .joinToString(separator = ", ")

fun onSaleProducts_ifEmptyString(products: List<Product>) =
        products.filter { it.onSale }
            .map { it.name }
            .joinToString(separator = ", ")
            .ifEmpty { "none" }

fun main(args: Array<String>) {
    val widget = Product("Oscillation Overthruster", 10.0)
    val tpsReportCoverSheet = Product("TPS Report Cover Sheet",
        0.25, true)
    val handPhaser = Product("Flux Capacitor", 29.95)
    val products = listOf(widget, tpsReportCoverSheet, handPhaser)
    println(onSaleProducts_ifEmptyCollection(products))

    tpsReportCoverSheet.onSale = false
    println(onSaleProducts_ifEmptyCollection(products))
    println(onSaleProducts_ifEmptyString(products))
}