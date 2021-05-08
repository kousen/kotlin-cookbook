package collections

fun onSaleProductsIfEmptyCollection(products: List<Product>) =
    products.filter { it.onSale }
        .map { it.name }
        .ifEmpty { listOf("none") }
        .joinToString(separator = ", ")

fun onSaleProductsIfEmptyString(products: List<Product>) =
    products.filter { it.onSale }
        .joinToString(separator = ", ") { it.name }
        .ifEmpty { "none" }

fun main() {
    val widget = Product("Oscillation Overthruster", 10.0)
    val tpsReportCoverSheet = Product(
        "TPS Report Cover Sheet",
        0.25, true
    )
    val fluxCapacitor = Product("Flux Capacitor", 29.95)
    val products = listOf(widget, tpsReportCoverSheet, fluxCapacitor)
    println(onSaleProductsIfEmptyCollection(products))

    tpsReportCoverSheet.onSale = false
    println(onSaleProductsIfEmptyCollection(products))
    println(onSaleProductsIfEmptyString(products))
}