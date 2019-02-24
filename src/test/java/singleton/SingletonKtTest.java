package singleton;

import org.junit.jupiter.api.Test;

public class SingletonKtTest {
    @Test
    void accessingSingletonMembers() {
        MySingleton.INSTANCE.myFunction();
        MySingleton.INSTANCE.getMyProperty();
    }
}
