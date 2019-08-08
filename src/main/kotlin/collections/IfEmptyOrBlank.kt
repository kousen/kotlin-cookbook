package collections

fun onSaleProducts_ifEmptyCollection(products: List<Product>) =
    products.filter { it.onSale }
        .map { it.name }
        .ifEmpty { listOf("none") }
        .joinToString(separator = ", ")

fun onSaleProducts_ifEmptyString(products: List<Product>) =
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
    println(onSaleProducts_ifEmptyCollection(products))

    tpsReportCoverSheet.onSale = false
    println(onSaleProducts_ifEmptyCollection(products))
    println(onSaleProducts_ifEmptyString(products))
}