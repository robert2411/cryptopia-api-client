package biz.stevens.exchange;

import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CryptopiaPrivateTest {

    private Cryptopia cryptopia;
    @BeforeClass
    public void setup() throws ConfigurationException {
        this.cryptopia = new Cryptopia();
    }


    @Test(enabled=false)

    public void testGetGetBalance() throws Exception {
        cryptopia.getGetBalance();
    }
}
