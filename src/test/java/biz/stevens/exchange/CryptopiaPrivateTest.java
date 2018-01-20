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

    public void testGetGetBalance() {
        cryptopia.getGetBalance();
    }
}
