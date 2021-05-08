package collections

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class IfEmptyOrBlankKtTest {
    private val overthruster = Product("Oscillation Overthruster", 1_000_000.0)
    private val fluxcapacitor = Product("Flux Capacitor", 299_999.95, onSale = true)
    private val tpsReportCoverSheet = Product("TPS Report Cover Sheet", 0.25)

    @Test
    fun productsOnSale() {
        val products = listOf(overthruster, fluxcapacitor, tpsReportCoverSheet)

        assertAll( "On sale products",
            { assertEquals("Flux Capacitor", onSaleProductsIfEmptyCollection(products)) },
            { assertEquals("Flux Capacitor", onSaleProductsIfEmptyString(products)) })
    }

    @Test
    fun productsNotOnSale() {
        val products = listOf(overthruster, tpsReportCoverSheet)

        assertAll( "No products on sale",
            { assertEquals("none", onSaleProductsIfEmptyCollection(products)) },
            { assertEquals("none", onSaleProductsIfEmptyString(products)) })
    }
}