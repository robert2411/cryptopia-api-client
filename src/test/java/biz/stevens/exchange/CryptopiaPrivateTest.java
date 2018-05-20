package biz.stevens.exchange;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static biz.stevens.exchange.TestHelper.getResourceAsString;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CryptopiaPrivateTest {
    private static final String CURRENCY_NAME = "ETN";
    private static final int CURRENCY_ID = 695;
    private static final String BASE_MARKET = "ETN";
    private static final String MARKET = "ETN/USDT";
    private static final int TRADEPAIR_ID = 5760;
    private static final int COUNT = 10;
    private WireMockServer wireMockServer;

    private CryptopiaPrivate cryptopia;
    @BeforeClass
    public void setup() throws ConfigurationException {
        wireMockServer = new WireMockServer(); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        int port = wireMockServer.port();
        String url = "http://localhost:" + Integer.toString(port) + "/";
        this.cryptopia = new CryptopiaPrivateImpl("privateKey", "publicKey", url);
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        wireMockServer.stop();
    }


    @Test
    public void testGetGetBalance() {
        addMockEndpoint("/GetBalance", "placeholder.json");
        cryptopia.getBalance().stream().forEach(System.out::println);
    }

    @Test
    public void testGetGetBalanceCurrency() {
        addMockEndpoint("/GetBalance", "placeholder.json");
        cryptopia.getBalance(CURRENCY_NAME).forEach(System.out::println);
    }

    @Test
    public void testGetGetBalanceCurrencyId() {
        addMockEndpoint("/GetBalance", "placeholder.json");
        cryptopia.getBalance(CURRENCY_ID).forEach(System.out::println);
    }

    @Test
    public void testGetDepositAddressCurrency() {
        addMockEndpoint("/GetDepositAddress", "placeholder.json");
        cryptopia.getDepositAddress(CURRENCY_NAME).ifPresent(System.out::println);
    }

    @Test
    public void testGetDepositAddressCurrencyId() {
        addMockEndpoint("/GetDepositAddress", "placeholder.json");
        cryptopia.getDepositAddress(CURRENCY_ID).ifPresent(System.out::println);
    }

    @Test
    public void testGetOpenOrders() {
        addMockEndpoint("/GetOpenOrders", "placeholder.json");
        cryptopia.getOpenOrders().forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersMarket() {
        addMockEndpoint("/GetOpenOrders", "placeholder.json");
        cryptopia.getOpenOrders(MARKET).forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersMarketCount() {
        addMockEndpoint("/GetOpenOrders", "placeholder.json");
        cryptopia.getOpenOrders(MARKET, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersTradePairIdCount() {
        addMockEndpoint("/GetOpenOrders", "placeholder.json");
        cryptopia.getOpenOrders(TRADEPAIR_ID, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetOpenOrdersTradePairId() {
        addMockEndpoint("/GetOpenOrders", "placeholder.json");
        cryptopia.getOpenOrders(TRADEPAIR_ID).forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistory() {
        addMockEndpoint("/GetTradeHistory", "placeholder.json");
        cryptopia.getTradeHistory().forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistoryMarket() {
        addMockEndpoint("/GetTradeHistory", "placeholder.json");
        cryptopia.getTradeHistory(MARKET).forEach(System.out::println);
    }

    @Test
    public void testGetGetTradeHistoryMarketCount() {
        addMockEndpoint("/GetTradeHistory", "placeholder.json");
        cryptopia.getTradeHistory(MARKET, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistoryTradePairIdCount() {
        addMockEndpoint("/GetTradeHistory", "placeholder.json");
        cryptopia.getTradeHistory(TRADEPAIR_ID, COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetTradeHistoryTradePairId() {
        addMockEndpoint("/GetTradeHistory", "placeholder.json");
        cryptopia.getTradeHistory(TRADEPAIR_ID).forEach(System.out::println);
    }

    @Test
    public void testGetTransactionHistoryMarketCount() {
        addMockEndpoint("/GetTransactions", "placeholder.json");
        cryptopia.getTransactions("Deposit", COUNT).forEach(System.out::println);
    }

    @Test
    public void testGetTransactionHistoryMarket() {
        addMockEndpoint("/GetTransactions", "placeholder.json");
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
        addMockEndpoint("/SubmitTrade", "placeholder.json");
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
        addMockEndpoint("/SubmitTrade", "placeholder.json");
        BigDecimal rate = BigDecimal.valueOf(1000.0);
        BigDecimal amount = BigDecimal.valueOf(1);
        cryptopia.submitTrade(TRADEPAIR_ID, "Sell", rate, amount).ifPresent(System.out::println);
    }


    @Test
    public void testCancelAllTrades() {
        addMockEndpoint("/CancelTrade", "placeholder.json");
        cryptopia.cancelAllTrades().forEach(System.out::println);
    }


    @Test
    public void testcancelTradeByOrderId() {
        addMockEndpoint("/CancelTrade", "placeholder.json");
        /* this value should be changed before the test to a valid tradeOrderId */
        BigInteger orderId = BigInteger.valueOf(285045097);
        cryptopia.cancelTradesByOrderId(orderId).forEach(System.out::println);
    }

    @Test
    public void testCancelTradeByTradePairId() {
        addMockEndpoint("/CancelTrade", "placeholder.json");
        cryptopia.cancelTradesByTradePairId(TRADEPAIR_ID).forEach(System.out::println);
    }

    private void addMockEndpoint(String endpoint, String resouceName){
        String response = getResourceAsString("private/" + resouceName);
        wireMockServer.stubFor(post(urlEqualTo(endpoint))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }




}
