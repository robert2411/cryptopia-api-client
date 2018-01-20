package biz.stevens.exchange;

import biz.stevens.datatypes.*;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface Cryptopia {
    List<Currency> getCurrencies();

    List<TradePair> getTradePairs();

    List<Market> getMarkets(String baseMarket, int hours);

    List<Market> getMarkets(String baseMarket);

    List<Market> getMarkets(int hours);

    List<Market> getMarkets();

    Optional<Market> getMarket(@NonNull String marketName, int hours);

    Optional<Market> getMarket(@NonNull String marketName);

    List<MarketHistory> getMarketHistory(@NonNull String marketName);

    List<MarketHistory> getMarketHistory(@NonNull String marketName, int hours);

    Optional<MarketOrders> getMarketOrders(@NonNull String marketName);

    Optional<MarketOrders> getMarketOrders(@NonNull String marketName, int orderCount);

    List<MarketOrderGroup> getMarketOrderGroups(@NonNull String marketName);

    List<MarketOrderGroup> getMarketOrderGroups(@NonNull String marketName, int orderCount);

    List<Balance> getBalance();

    List<Balance> getBalance(@NonNull Integer currencyId);

    List<Balance> getBalance(@NonNull String currencyName);
}
