package biz.stevens.exchange;

import biz.stevens.datatypes.response.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static biz.stevens.exchange.TestHelper.getResourceAsString;
import static com.github.tomakehurst.wiremock.client.WireMock.*;


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
    public void setup() {
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
        addMockEndpoint("/GetCurrencies", "getCurrenciesResponse.json");

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
        addMockEndpoint("/GetTradePairs", "getTradePairsResponse.json");

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
        addMockEndpoint("/GetMarkets", "getMarketsResponse.json");

        List<Market> markets = this.cryptopia.getMarkets();
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }


    @Test
    public void testGetMarketsHours() {
        addMockEndpoint("/GetMarkets/12", "getMarketsHoursResponse.json");

        List<Market> markets = this.cryptopia.getMarkets(HOURS);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarketsBaseMarket() {
        addMockEndpoint("/GetMarkets/BTC", "getMarketsBaseMarketResponse.json");

        List<Market> markets = this.cryptopia.getMarkets(BASE_MARKET);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarketsBaseMarketHours() {
        addMockEndpoint("/GetMarkets/BTC/12", "getMarketsBaseMarketHoursResponse.json");

        List<Market> markets = this.cryptopia.getMarkets(BASE_MARKET, HOURS);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarketMarket() {
        addMockEndpoint("/GetMarket/DOT_BTC/24", "getMarketMarketResponse.json");

        Optional<Market> market = this.cryptopia.getMarket(MARKET);
        Assert.assertTrue(market.isPresent());
        assertMarket(market.get());
    }

    @Test
    public void testGetMarketMarketHours() {
        addMockEndpoint("/GetMarket/DOT_BTC/12", "getMarketMarketHoursResponse.json");

        Optional<Market> market = this.cryptopia.getMarket(MARKET, HOURS);
        Assert.assertTrue(market.isPresent());
        assertMarket(market.get());
    }

    @Test
    public void testGetMarketHistoryMarketHours() {
        addMockEndpoint("/GetMarketHistory/DOT_BTC/12", "getMarketHistoryMarketHoursResponse.json");

        List<MarketHistory> marketHistories = this.cryptopia.getMarketHistory(MARKET, HOURS);
        Assert.assertFalse(marketHistories.isEmpty());

        MarketHistory marketHistory = marketHistories.get(0);

        assertMarketHistory(marketHistory);
    }


    @Test
    public void testGetMarketHistoryMarket() {
        addMockEndpoint("/GetMarketHistory/DOT_BTC/24", "getMarketHistoryMarketResponse.json");

        List<MarketHistory> marketHistories = this.cryptopia.getMarketHistory(MARKET);
        Assert.assertFalse(marketHistories.isEmpty());

        MarketHistory marketHistory = marketHistories.get(0);

        assertMarketHistory(marketHistory);
    }

    @Test
    public void testGetMarketOrdersMarket() {
        addMockEndpoint("/GetMarketOrders/DOT_BTC/50", "getMarketOrdersMarketResponse.json");

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
        addMockEndpoint("/GetMarketOrders/DOT_BTC/10", "getMarketOrdersMarketOrderCountResponse.json");


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
        addMockEndpoint("/GetMarketOrderGroups/DOT_BTC/100", "getMarketOrderGroupsMarketResponse.json");

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
        addMockEndpoint("/GetMarketOrderGroups/DOT_BTC/10", "getMarketOrderGroupsOrderCountMarketResponse.json");

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

    private void addMockEndpoint(String endpoint, String resouceName){
        String response = getResourceAsString("public/" + resouceName);
        wireMockServer.stubFor(get(urlEqualTo(endpoint))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
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