package biz.stevens.exchange;

import biz.stevens.datatypes.*;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CryptopiaPublic {

    /**
     * Returns all currency data
     *
     * @return A list with al the currencies
     */
    List<Currency> getCurrencies();

    /**
     * Returns all trade pair data
     *
     * @return A list with all the tradePairs
     */
    List<TradePair> getTradePairs();

    /**
     * Returns all market data
     *
     * @param baseMarket the basemarket e.g. BTC
     * @param hours The amount of hours for witch you want your data (default 24)
     * @return A list with all the marketData
     */
    List<Market> getMarkets(String baseMarket, int hours);

    /**
     * Returns all market data
     * @param baseMarket the basemarket e.g. BTC
     * @return A list with all the marketData
     */
    List<Market> getMarkets(String baseMarket);

    /**
     * Returns all market data
     *
     * @param hours The amount of hours for witch you want your data (default 24)
     * @return A list with all the marketData
     */
    List<Market> getMarkets(int hours);

    /**
     * Returns all market data
     *
     * @return A list with all the marketData
     */
    List<Market> getMarkets();

    /**
     * Returns market data for the specified tradePair
     *
     * @param marketName the marketName in the format "DOT_BTC"
     * @param hours The amount of hours for witch you want your data (default 24)
     * @return An optional containing the market data
     */
    Optional<Market> getMarket(@NonNull String marketName, int hours);

    /**
     *Returns market data for the specified tradePair
     *
     * @param tradePairId the tradepaireId e.g. 100
     * @return An optional containing the market data
     */
    Optional<Market> getMarket(@NonNull Integer tradePairId);

    /**
     * Returns market data for the specified market
     *
     * @param tradePairId the tradepaireId e.g. 100
     * @param hours The amount of hours for witch you want your data (default 24)
     * @return An optional containing the market data
     */
    Optional<Market> getMarket(@NonNull Integer tradePairId, int hours);

    /**
     * Returns market data for the specified market
     *
     * @param marketName the marketName in the format "DOT_BTC"
     * @return An optional containing the market data
     */
    Optional<Market> getMarket(@NonNull String marketName);

    /**
     * Returns the market history data for the specified trade pair.
     *
     * @param marketName the marketName in the format "DOT_BTC"
     * @return A list with the marketHistory
     */
    List<MarketHistory> getMarketHistory(@NonNull String marketName);

    /**
     * Returns the market history data for the specified trade pair.
     *
     * @param marketName the marketName in the format "DOT_BTC"
     * @param hours The amount of hours for witch you want your data (default 24)
     * @return A list with the marketHistory
     */
    List<MarketHistory> getMarketHistory(@NonNull String marketName, int hours);

    /**
     * Returns the market history data for the specified trade pair.
     *
     * @param tradePairId the tradepaireId e.g. 100
     * @return A list with the marketHistory
     */
    List<MarketHistory> getMarketHistory(@NonNull Integer tradePairId);

    /**
     * Returns the market history data for the specified trade pair.
     *
     * @param tradePairId the tradepaireId e.g. 100
     * @param hours The amount of hours for witch you want your data (default 24)
     * @return A list with the marketHistory
     */
    List<MarketHistory> getMarketHistory(@NonNull Integer tradePairId, int hours);

    /**
     * Returns the open buy and sell orders for the specified trade pair.
     *
     * @param marketName the marketName in the format "DOT_BTC"
     * @return an optional with all the marketOrders
     */
    Optional<MarketOrders> getMarketOrders(@NonNull String marketName);

    /**
     * Returns the open buy and sell orders for the specified trade pair.
     *
     * @param marketName the marketName in the format "DOT_BTC"
     * @param orderCount the amount of orders (default 100)
     * @return an optional with all the marketOrders
     */
    Optional<MarketOrders> getMarketOrders(@NonNull String marketName, int orderCount);

    /**
     * Returns the open buy and sell orders for the specified trade pair.
     *
     * @param tradePairId the tradepaireId e.g. 100
     * @return an optional with all the marketOrders
     */
    Optional<MarketOrders> getMarketOrders(@NonNull Integer tradePairId);

    /**
     * Returns the open buy and sell orders for the specified trade pair.
     *
     * @param tradePairId the tradepaireId e.g. 100
     * @param orderCount the amount of orders (default 100)
     * @return an optional with all the marketOrders
     */
    Optional<MarketOrders> getMarketOrders(@NonNull Integer tradePairId, int orderCount);

    /**
     * Returns the open buy and sell orders for the specified markets.
     * @param marketName the marketName in the format "DOT_BTC"
     * @param marketNames optional extra markets, see marketName
     * @return a list with orders for a specific market
     */
    List<MarketOrderGroup> getMarketOrderGroups(@NonNull String marketName, @NonNull  String... marketNames);

    /**
     * Returns the open buy and sell orders for the specified markets.
     * @param orderCount the amount of orders in the list
     * @param marketName the marketName in the format "DOT_BTC"
     * @param marketNames optional extra markets, see marketName
     * @return
     */
    List<MarketOrderGroup> getMarketOrderGroups(int orderCount, @NonNull String marketName, @NonNull  String... marketNames);
}
