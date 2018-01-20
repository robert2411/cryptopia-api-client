package biz.stevens.exchange;

import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CryptopiaPrivateTest {
    private static final String CURRENCY_NAME = "1337";
    private static final int CURRENCY_ID = 331;
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
        cryptopia.getBalance(CURRENCY_NAME).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetGetBalanceCurrencyId() {
        cryptopia.getBalance(CURRENCY_ID).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetDepositAddressCurrency() {
        cryptopia.getDepositAddress(CURRENCY_NAME).ifPresent(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetDepositAddressCurrencyId() {
        cryptopia.getDepositAddress(CURRENCY_ID).ifPresent(System.out::println);
    }




}
