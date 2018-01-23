package biz.stevens.exchange;

import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

@Test(enabled = false)
public class CryptopiaPrivateTest {
    private static final String CURRENCY_NAME = "ETN";
    private static final int CURRENCY_ID = 695;
    private static final String BASE_MARKET = "ETN";
    private static final String MARKET = "ETN/USDT";
    private static final int TRADEPAIR_ID = 5760;
    private static final int COUNT = 10;

    private CryptopiaPrivate cryptopia;
    @BeforeClass
    public void setup() throws ConfigurationException {
        this.cryptopia = new CryptopiaPrivateImpl();
    }


    @Test
    public void testGetGetBalance() {
        cryptopia.getBalance().stream().forEach(System.out::println);
    }

    @Test
    public void testGetGetBalanceCurrency() {
        cryptopia.getBalance(CURRENCY_NAME).forEach(System.out::println);
    }

    @Test
    public void testGetGetBalanceCurrencyId() {
        cryptopia.getBalance(CURRENCY_ID).forEach(System.out::println);
    }

    @Test
    public void testGetDepositAddressCurrency() {
        cryptopia.getDepositAddress(CURRENCY_NAME).ifPresent(System.out::println);
    }

    @Test
    public void testGetDepositAddressCurrencyId() {
        cryptopia.getDepositAddress(CURRENCY_ID).ifPresent(System.out::println);
    }

    @Test
    public void testGetOpenOrders() {
        cryptopia.getOpenOrders().forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersMarket() {
        cryptopia.getOpenOrders(MARKET).forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersMarketCount() {
        cryptopia.getOpenOrders(MARKET, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersTradePairIdCount() {
        cryptopia.getOpenOrders(TRADEPAIR_ID, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersTradePairId() {
        cryptopia.getOpenOrders(TRADEPAIR_ID).forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistory() {
        cryptopia.getTradeHistory().forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistoryMarket() {
        cryptopia.getTradeHistory(MARKET).forEach(System.out::println);
    }

    @Test
    public void testGetGetTradeHistoryMarketCount() {
        cryptopia.getTradeHistory(MARKET, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistoryTradePairIdCount() {
        cryptopia.getTradeHistory(TRADEPAIR_ID, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistoryTradePairId() {
        cryptopia.getTradeHistory(TRADEPAIR_ID).forEach(System.out::println);
    }

    @Test
    public void testGetTransactionHistoryMarketCount() {
        cryptopia.getTransactions("Deposit", COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetTransactionHistoryMarket() {
        cryptopia.getTransactions("Withdraw").forEach(System.out::println);
    }

    /*
     * the consciences of a wrong call can be big here thats whey the params are listed here
     * @param tradePairId The Cryptopia tradepair identifier of trade e.g. '100'
     * @param type the type of trade e.g. 'Buy' or 'Sell'
     * @param rate the rate or price to pay for the coins e.g. 0.00000034
     * @param amount the amount of coins to buy e.g. 123.00000000
     * @return an tradeResponse
     */
    @Test
    public void testSubmitTradeTradePairId() {
        BigDecimal rate = BigDecimal.valueOf(1000.0);
        BigDecimal amount = BigDecimal.valueOf(1);

        cryptopia.submitTrade(MARKET, "Sell", rate, amount).ifPresent(System.out::println);
    }

    /*
     * the consciences of a wrong call can be big here thats whey the params are listed here
     * @param market The market symbol of the trade e.g. 'DOT/BTC'
     * @param type the type of trade e.g. 'Buy' or 'Sell'
     * @param rate the rate or price to pay for the coins e.g. 0.00000034
     * @param amount the amount of coins to buy e.g. 123.00000000
     * @return an tradeResponse
     */
    //@Test(enabled = false)
    @Test
    public void testSubmitTradeMarket() {
        BigDecimal rate = BigDecimal.valueOf(1000.0);
        BigDecimal amount = BigDecimal.valueOf(1);
        cryptopia.submitTrade(TRADEPAIR_ID, "Sell", rate, amount).ifPresent(System.out::println);
    }


    @Test
    public void testCancelAllTrades() {
        cryptopia.cancelAllTrades().forEach(System.out::println);
    }


    @Test
    public void testcancelTradeByOrderId() {
        /* this value should be changed before the test to a valid tradeOrderId */
        BigInteger orderId = BigInteger.valueOf(285045097);
        cryptopia.cancelTradesByOrderId(orderId).forEach(System.out::println);
    }

    @Test
    public void testCancelTradeByTradePairId() {
        cryptopia.cancelTradesByTradePairId(TRADEPAIR_ID).forEach(System.out::println);
    }




}
