package biz.stevens.exchange;

import biz.stevens.datatypes.*;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

public interface CryptopiaPrivate {
    List<Balance> getBalance();

    List<Balance> getBalance(@NonNull Integer currencyId);

    List<Balance> getBalance(@NonNull String currencyName);

    Optional<DepositAddress> getDepositAddress(@NonNull Integer currencyId);

    Optional<DepositAddress> getDepositAddress(@NonNull String currencyName);

    List<OpenOrder> getOpenOrders();

    List<OpenOrder> getOpenOrders(@NonNull String market);

    List<OpenOrder> getOpenOrders(@NonNull String market, @NonNull Integer count);

    List<OpenOrder> getOpenOrders(@NonNull Integer tradePairId);

    List<OpenOrder> getOpenOrders(@NonNull Integer tradePairId, @NonNull Integer count);

    List<TradeHistory> getTradeHistory();

    List<TradeHistory> getTradeHistory(@NonNull String market);

    List<TradeHistory> getTradeHistory(@NonNull String market, @NonNull Integer count);

    List<TradeHistory> getTradeHistory(@NonNull Integer tradePairId);

    List<TradeHistory> getTradeHistory(@NonNull Integer tradePairId, @NonNull Integer count);

    List<Transaction> getTransactions(@NonNull String type);

    List<Transaction> getTransactions(@NonNull String type, @NonNull Integer count);
}