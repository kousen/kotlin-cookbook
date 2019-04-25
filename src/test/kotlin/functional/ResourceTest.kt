package functional

import org.junit.jupiter.api.Test

internal class ResourceTest {
    @Test
    internal fun `demo the use function on Resource`() {
        Resource.use {
            it.op()
        }
    }

    @Test
    internal fun `demo the use function on SingletonResource`() {
        Resource.use {
            it.op()
        }
    }

//    @Test
//    internal fun `demo useResource`() {
//        val resource = Resource()
//        resource.useResource {
//            op()
//        }
//    }
}