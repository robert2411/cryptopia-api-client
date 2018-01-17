package biz.stevens.exchange;

import biz.stevens.datatypes.*;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

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

    private Cryptopia cryptopia;
    @BeforeClass
    public void setup() throws ConfigurationException {
        this.cryptopia = new Cryptopia();
    }

    @Test
    public void testGetCurrencies() throws Exception {
        List<Currency> currencies = this.cryptopia.getCurrencies();

        Assert.assertFalse(currencies.isEmpty());

        Currency currency = currencies.get(0);
            Assert.assertNotNull(currency.getId());
            Assert.assertNotNull(currency.getName());
            Assert.assertNotNull(currency.getSymbol());
            Assert.assertNotNull(currency.getAlgorithm());
            Assert.assertNotNull(currency.getWithdrawFee());
            Assert.assertNotNull(currency.getMinWithdraw());
            Assert.assertNotNull(currency.getMinBaseTrade());
            Assert.assertNotNull(currency.getIsTipEnabled());
            Assert.assertNotNull(currency.getMinTip());
            Assert.assertNotNull(currency.getDepositConfirmations());
            Assert.assertNotNull(currency.getStatus());
            Assert.assertNotNull(currency.getListingStatus());

    }

    @Test
    public void testGetTradePairs() throws Exception {
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
    public void testGetMarkets() throws Exception {
        List<Market> markets = this.cryptopia.getMarkets();
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }


    @Test
    public void testGetMarkets1() throws Exception {
        List<Market> markets = this.cryptopia.getMarkets(HOURS);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarkets2() throws Exception {
        List<Market> markets = this.cryptopia.getMarkets(BASE_MARKET);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarkets3() throws Exception {
        List<Market> markets = this.cryptopia.getMarkets(BASE_MARKET,HOURS);
        Assert.assertFalse(markets.isEmpty());
        Market market = markets.get(0);
        assertMarket(market);
    }

    @Test
    public void testGetMarket() throws Exception {
        Optional<Market> market = this.cryptopia.getMarket(MARKET);
        Assert.assertTrue(market.isPresent());
        assertMarket(market.get());
    }

    @Test
    public void testGetMarket1() throws Exception {
        Optional<Market> market = this.cryptopia.getMarket(MARKET, HOURS);
        Assert.assertTrue(market.isPresent());
        assertMarket(market.get());
    }

    @Test
    public void testGetMarketHistory() throws Exception {
        List<MarketHistory> marketHistories = this.cryptopia.getMarketHistory(MARKET, HOURS);
        Assert.assertFalse(marketHistories.isEmpty());

        MarketHistory marketHistory = marketHistories.get(0);

        assertMarketHistory(marketHistory);
    }



    @Test
    public void testGetMarketHistory1() throws Exception {
        List<MarketHistory> marketHistories = this.cryptopia.getMarketHistory(MARKET);
        Assert.assertFalse(marketHistories.isEmpty());

        MarketHistory marketHistory = marketHistories.get(0);

        assertMarketHistory(marketHistory);
    }

    @Test
    public void testGetMarketOrders() throws Exception {
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
    public void testGetMarketOrders1() throws Exception {
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
    public void testGetMarketOrderGroups() throws Exception {
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
    public void testGetMarketOrderGroups1() throws Exception {
        List<MarketOrderGroup> marketOrderGroups = this.cryptopia.getMarketOrderGroups(MARKET, ORDER_COUNT);
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