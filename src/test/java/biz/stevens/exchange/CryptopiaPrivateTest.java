package biz.stevens.exchange;

import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CryptopiaPrivateTest {
    private static final String CURRENCY_NAME = "ETN";
    private static final int CURRENCY_ID = 695;
    private static final String BASE_MARKET = "ETN";
    private static final String MARKET = "ETN/USDT";
    private static final int TRADEPAIR_ID = 5760;
    private static final int COUNT = 10;

    private CryptopiaImpl cryptopia;
    @BeforeClass
    public void setup() throws ConfigurationException {
        this.cryptopia = new CryptopiaImpl();
    }


    @Test(enabled = false)
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

    @Test(enabled = false)
    //@Test
    public void testGetOpenOrders() {
        cryptopia.getOpenOrders().stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetOpenOrdersMarket() {
        cryptopia.getOpenOrders(MARKET).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetOpenOrdersMarketCount() {
        cryptopia.getOpenOrders(MARKET, COUNT).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetOpenOrdersTradePairIdCount() {
        cryptopia.getOpenOrders(TRADEPAIR_ID, COUNT).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetOpenOrdersTradePairId() {
        cryptopia.getOpenOrders(TRADEPAIR_ID).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetTradeHistory() {
        cryptopia.getTradeHistory().stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetTradeHistoryMarket() {
        cryptopia.getTradeHistory(MARKET).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetGetTradeHistoryMarketCount() {
        cryptopia.getTradeHistory(MARKET, COUNT).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetTradeHistoryTradePairIdCount() {
        cryptopia.getTradeHistory(TRADEPAIR_ID, COUNT).stream().forEach(System.out::println);
    }

    @Test(enabled = false)
    //@Test
    public void testGetTradeHistoryTradePairId() {
        cryptopia.getTradeHistory(TRADEPAIR_ID).stream().forEach(System.out::println);
    }

    //@Test(enabled = false)
    @Test
    public void testGetTransactionHistoryMarketCount() {
        cryptopia.getTransactions("Deposit", COUNT).stream().forEach(System.out::println);
    }

    //@Test(enabled = false)
    @Test
    public void testGetTransactionHistoryMarket() {
        cryptopia.getTransactions("Withdraw").stream().forEach(System.out::println);
    }




}
