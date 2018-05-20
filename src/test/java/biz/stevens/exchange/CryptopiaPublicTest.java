package biz.stevens.exchange;

import biz.stevens.datatypes.response.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static biz.stevens.exchange.TestHelper.getResourceAsString;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * It seems that the status message is often NULL so that field is not tested!!
 * The unit tests are not guaranteed to pass even when there is no mistake in the code
 * The assumption was made that the  api response is correct and the first row is a row with all field set
 */
public class CryptopiaPublicTest {
    private static final String BASE_MARKET = "BTC";
    private static final String MARKET = "DOT_BTC";
    private static final int HOURS = 12;
    private static final int ORDER_COUNT = 10;
    private WireMockServer wireMockServer;

    private CryptopiaPublicImpl cryptopia;

    @BeforeClass
    public void setup() throws ConfigurationException {
        wireMockServer = new WireMockServer(); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        int port = wireMockServer.port();
        String url = "http://localhost:" + Integer.toString(port) + "/";
        this.cryptopia = new CryptopiaPublicImpl(url);
//        this.cryptopia = new CryptopiaPublicImpl();
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        wireMockServer.stop();
    }

    @Test
    public void testGetCurrencies() {
        String response = getResourceAsString("getCurrenciesResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetCurrencies"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        List<Currency> currencies = this.cryptopia.getCurrencies();

        Assert.assertFalse(currencies.isEmpty());

        Currency currency = currencies.get(0);
        Assert.assertEquals(currency.getId(), Integer.valueOf(762));
        Assert.assertEquals(currency.getName(), "$PAC");
        Assert.assertEquals(currency.getSymbol(), "$PAC");
        Assert.assertEquals(currency.getAlgorithm(), "X11");
        Assert.assertEquals(currency.getWithdrawFee(), BigDecimal.valueOf(0.00002000));
        Assert.assertEquals(currency.getMinWithdraw(), BigDecimal.valueOf(0.00010000));
        Assert.assertEquals(currency.getMaxWithdraw(), BigDecimal.valueOf(20000000.00000000));
        Assert.assertEquals(currency.getMinBaseTrade(), BigDecimal.valueOf(0.00002000));
        Assert.assertEquals(currency.getIsTipEnabled(), Boolean.FALSE);
        Assert.assertEquals(currency.getMinTip(), BigDecimal.valueOf(0.00000000));
        Assert.assertEquals(currency.getDepositConfirmations(), Integer.valueOf(20));
        Assert.assertEquals(currency.getStatus(), "OK");
        Assert.assertNull(currency.getStatusMessage());
        Assert.assertEquals(currency.getListingStatus(), "Active");

    }

    @Test
    public void testGetTradePairs() {
        String response = getResourceAsString("getTradePairsResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetTradePairs"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        List<TradePair> tradePairs = this.cryptopia.getTradePairs();
        Assert.assertFalse(tradePairs.isEmpty());
        TradePair tradePair = tradePairs.get(0);
        Assert.assertNotNull(tradePair.getId());
        Assert.assertNotNull(tradePair.getLabel());
        Assert.assertNotNull(tradePair.getCurrency());
        Assert.assertNotNull(tradePair.getSymbol());
        Assert.assertNotNull(tradePair.getBaseCurrency());
        Assert.assertNotNull(tradePair.getBaseSymbol());
        Assert.assertNotNull(tradePair.getStatus());
        Assert.assertNotNull(tradePair.getTradeFee());
        Assert.assertNotNull(tradePair.getMinimumTrade());
        Assert.assertNotNull(tradePair.getMaximumTrade());
        Assert.assertNotNull(tradePair.getMinimumBaseTrade());
        Assert.assertNotNull(tradePair.getMaximumBaseTrade());
        Assert.assertNotNull(tradePair.getMinimumPrice());
        Assert.assertNotNull(tradePair.getMaximumPrice());

    }

    @Test
    public void testGetMarkets() {
        String response = getResourceAsString("getMarketsResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarkets"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        List<Market> markets = this.cryptopia.getMarkets();
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }


    @Test
    public void testGetMarketsHours() {
        String response = getResourceAsString("getMarketsHoursResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarkets/12"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        List<Market> markets = this.cryptopia.getMarkets(HOURS);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarketsBaseMarket() {
        String response = getResourceAsString("getMarketsBaseMarketResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarkets/BTC"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        List<Market> markets = this.cryptopia.getMarkets(BASE_MARKET);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarketsBaseMarketHours() {
        String response = getResourceAsString("getMarketsBaseMarketHoursResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarkets/BTC/12"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        List<Market> markets = this.cryptopia.getMarkets(BASE_MARKET, HOURS);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarketMarket() {
        String response = getResourceAsString("getMarketHistoryMarketResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarket/DOT_BTC/24"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        Optional<Market> market = this.cryptopia.getMarket(MARKET);
        Assert.assertTrue(market.isPresent());
        assertMarket(market.get());
    }

    @Test
    public void testGetMarketMarketHours() {
        String response = getResourceAsString("getMarketMarketHoursResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarket/DOT_BTC/12"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
        Optional<Market> market = this.cryptopia.getMarket(MARKET, HOURS);
        Assert.assertTrue(market.isPresent());
        assertMarket(market.get());
    }

    @Test
    public void testGetMarketHistoryMarketHours() {
        String response = getResourceAsString("getMarketHistoryMarketHoursResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarketHistory/DOT_BTC/12"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        List<MarketHistory> marketHistories = this.cryptopia.getMarketHistory(MARKET, HOURS);
        Assert.assertFalse(marketHistories.isEmpty());

        MarketHistory marketHistory = marketHistories.get(0);

        assertMarketHistory(marketHistory);
    }


    @Test
    public void testGetMarketHistoryMarket() {
        String response = getResourceAsString("getMarketHistoryMarketResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarketHistory/DOT_BTC/24"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        List<MarketHistory> marketHistories = this.cryptopia.getMarketHistory(MARKET);
        Assert.assertFalse(marketHistories.isEmpty());

        MarketHistory marketHistory = marketHistories.get(0);

        assertMarketHistory(marketHistory);
    }

    @Test
    public void testGetMarketOrdersMarket() {
        String response = getResourceAsString("getMarketOrdersMarketResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarketOrders/DOT_BTC/50"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        Optional<MarketOrders> marketOrders = this.cryptopia.getMarketOrders(MARKET);
        Assert.assertTrue(marketOrders.isPresent());

        List<Order> buyOrderList = marketOrders.get().getBuy();
        List<Order> sellOrderList = marketOrders.get().getBuy();

        Assert.assertFalse(buyOrderList.isEmpty());
        Assert.assertFalse(sellOrderList.isEmpty());

        assertOrder(buyOrderList.get(0));
        assertOrder(sellOrderList.get(0));
    }


    @Test
    public void testGetMarketOrdersMarketOrderCount() {
        String response = getResourceAsString("getMarketOrdersMarketOrderCountResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarketOrders/DOT_BTC/10"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        Optional<MarketOrders> marketOrders = this.cryptopia.getMarketOrders(MARKET, ORDER_COUNT);
        Assert.assertTrue(marketOrders.isPresent());

        Assert.assertNotNull(marketOrders.get().getBuy());
        Assert.assertNotNull(marketOrders.get().getSell());

        List<Order> buyOrderList = marketOrders.get().getBuy();
        List<Order> sellOrderList = marketOrders.get().getBuy();

        Assert.assertFalse(buyOrderList.isEmpty());
        Assert.assertFalse(sellOrderList.isEmpty());

        assertOrder(buyOrderList.get(0));
        assertOrder(sellOrderList.get(0));
    }

    @Test
    public void testGetMarketOrderGroupsMarket() {
        String response = getResourceAsString("getMarketOrderGroupsMarketResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarketOrderGroups/DOT_BTC/100"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        List<MarketOrderGroup> marketOrderGroups = this.cryptopia.getMarketOrderGroups(MARKET);
        Assert.assertFalse(marketOrderGroups.isEmpty());

        MarketOrderGroup marketOrderGroup = marketOrderGroups.get(0);

        Assert.assertNotNull(marketOrderGroup.getTradePairId());
        Assert.assertNotNull(marketOrderGroup.getMarket());
        Assert.assertNotNull(marketOrderGroup.getBuy());
        Assert.assertNotNull(marketOrderGroup.getSell());

        List<Order> buyOrderList = marketOrderGroup.getBuy();
        List<Order> sellOrderList = marketOrderGroup.getBuy();

        Assert.assertFalse(buyOrderList.isEmpty());
        Assert.assertFalse(sellOrderList.isEmpty());

        assertOrder(buyOrderList.get(0));
        assertOrder(sellOrderList.get(0));
    }

    @Test
    public void testGetMarketOrderGroupsOrderCountMarket() {
        String response = getResourceAsString("getMarketOrderGroupsOrderCountMarketResponse.json");
        wireMockServer.stubFor(get(urlEqualTo("/GetMarketOrderGroups/DOT_BTC/10"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));

        List<MarketOrderGroup> marketOrderGroups = this.cryptopia.getMarketOrderGroups(ORDER_COUNT, MARKET);
        Assert.assertFalse(marketOrderGroups.isEmpty());

        MarketOrderGroup marketOrderGroup = marketOrderGroups.get(0);

        Assert.assertNotNull(marketOrderGroup.getTradePairId());
        Assert.assertNotNull(marketOrderGroup.getMarket());
        Assert.assertNotNull(marketOrderGroup.getBuy());
        Assert.assertNotNull(marketOrderGroup.getSell());

        List<Order> buyOrderList = marketOrderGroup.getBuy();
        List<Order> sellOrderList = marketOrderGroup.getBuy();

        Assert.assertFalse(buyOrderList.isEmpty());
        Assert.assertFalse(sellOrderList.isEmpty());

        assertOrder(buyOrderList.get(0));
        assertOrder(sellOrderList.get(0));
    }

    private void assertMarket(Market market) {
        Assert.assertNotNull(market.getTradePairId());
        Assert.assertNotNull(market.getLabel());
        Assert.assertNotNull(market.getAskPrice());
        Assert.assertNotNull(market.getBidPrice());
        Assert.assertNotNull(market.getLow());
        Assert.assertNotNull(market.getHigh());
        Assert.assertNotNull(market.getVolume());
        Assert.assertNotNull(market.getLastPrice());
        Assert.assertNotNull(market.getBuyVolume());
        Assert.assertNotNull(market.getSellVolume());
        Assert.assertNotNull(market.getChange());
        Assert.assertNotNull(market.getOpen());
        Assert.assertNotNull(market.getClose());
        Assert.assertNotNull(market.getBaseVolume());
        Assert.assertNotNull(market.getBuyBaseVolume());
        Assert.assertNotNull(market.getSellBaseVolume());
    }

    private void assertMarketHistory(MarketHistory marketHistory) {
        Assert.assertNotNull(marketHistory.getTradePairId());
        Assert.assertNotNull(marketHistory.getLabel());
        Assert.assertNotNull(marketHistory.getType());
        Assert.assertNotNull(marketHistory.getPrice());
        Assert.assertNotNull(marketHistory.getAmount());
        Assert.assertNotNull(marketHistory.getTotal());
        Assert.assertNotNull(marketHistory.getTimestamp());
    }

    private void assertOrder(Order order) {
        Assert.assertNotNull(order.getTradePairId());
        Assert.assertNotNull(order.getLabel());
        Assert.assertNotNull(order.getPrice());
        Assert.assertNotNull(order.getVolume());
        Assert.assertNotNull(order.getTotal());
    }


}