package biz.stevens.exchange;

import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CryptopiaPrivateTest {

    private CryptopiaImpl cryptopia;
    @BeforeClass
    public void setup() throws ConfigurationException {
        this.cryptopia = new CryptopiaImpl();
    }


    @Test(enabled=false)
    //@Test
    public void testGetGetBalance() {
        cryptopia.getBalance().stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetGetBalanceCurrency() {
        cryptopia.getBalance("1337").stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetGetBalanceCurrencyId() {
        cryptopia.getBalance(331).stream().forEach(System.out::println);
    }
}
