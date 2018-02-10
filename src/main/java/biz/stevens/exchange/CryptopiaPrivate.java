package biz.stevens.exchange;

import biz.stevens.datatypes.response.*;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CryptopiaPrivate {
    /**
     * @return all balances
     */
    List<Balance> getBalance();

    /**
     * @param currencyId The Cryptopia currency identifier of the balance to return e.g. '2'
     * @return a specific currency balance
     */
    List<Balance> getBalance(@NonNull Integer currencyId);

    /**
     * @param currencyName The currency symbol of the balance to return e.g. 'DOT'
     * @return a specific currency balance
     */
    List<Balance> getBalance(@NonNull String currencyName);

    /**
     * Creates or returns a deposit address for the specified currency
     *
     * @param currencyId The Cryptopia currency identifier of the address to return e.g. '2'
     * @return An optional containing a deposit Address
     */
    Optional<DepositAddress> getDepositAddress(@NonNull Integer currencyId);

    /**
     *Creates or returns a deposit address for the specified currency
     *
     * @param currencyName The currency symbol of the address to return e.g. 'DOT'
     * @return An optional containing a deposit Address
     */
    Optional<DepositAddress> getDepositAddress(@NonNull String currencyName);

    /**
     * Returns a list of open orders for all tradepairs
     * @return a list of all the open orders
     */
    List<OpenOrder> getOpenOrders();

    /**
     * Returns a list of open orders for all tradepairs or specified tradepair
     *
     * @param market The market symbol of the orders to return e.g. 'DOT/BTC'
     * @return A list with all the open orders matching the params
     */
    List<OpenOrder> getOpenOrders(@NonNull String market);

    /**
     * Returns a list of open orders for all tradepairs or specified tradepair
     *
     * @param market The market symbol of the orders to return e.g. 'DOT/BTC'
     * @param count The maximum amount of orders to return e.g. '10'
     * @return A list with all the open orders matching the params limited by the count
     */
    List<OpenOrder> getOpenOrders(@NonNull String market, @NonNull Integer count);

    /**
     * Returns a list of open orders for all tradepairs or specified tradepair
     *
     * @param tradePairId The Cryptopia tradepair identifier of the orders to return e.g. '100'
     * @return A list with all the open orders matching the params limited by the count
     */
    List<OpenOrder> getOpenOrders(@NonNull Integer tradePairId);

    /**
     * Returns a list of open orders for all tradepairs or specified tradepair
     *
     * @param tradePairId The Cryptopia tradepair identifier of the orders to return e.g. '100'
     * @param count The maximum amount of orders to return e.g. '10'
     * @return A list with all the open orders matching the params limited by the count
     */
    List<OpenOrder> getOpenOrders(@NonNull Integer tradePairId, @NonNull Integer count);

    /**
     * Returns a list of trade history for all tradepairs or specified tradepair
     *
     * @return a list of trade history for all tradepairs
     */
    List<TradeHistory> getTradeHistory();

    /**
     * Returns a list of trade history for all tradepairs or specified tradepair
     *
     * @param market The market symbol of the history to return e.g. 'DOT/BTC'
     * @return a list of trade history for a given market
     */
    List<TradeHistory> getTradeHistory(@NonNull String market);

    /**
     * Returns a list of trade history for all tradepairs or specified tradepair
     *
     * @param market The market symbol of the history to return e.g. 'DOT/BTC'
     * @param count The maximum amount of history to return e.g. '10'
     * @return a list of trade history for a given market limited by the count
     */
    List<TradeHistory> getTradeHistory(@NonNull String market, @NonNull Integer count);

    /**
     * Returns a list of trade history for all tradepairs or specified tradepair
     *
     * @param tradePairId The Cryptopia tradepair identifier of the history to return e.g. '100'
     * @return a list of trade history for a given tradepair
     */
    List<TradeHistory> getTradeHistory(@NonNull Integer tradePairId);

    /**
     * Returns a list of trade history for all tradepairs or specified tradepair
     *
     * @param tradePairId The Cryptopia tradepair identifier of the history to return e.g. '100'
     * @param count The maximum amount of history to return e.g. '10'
     * @return a list of trade history for a given tradepair limited by the count
     */
    List<TradeHistory> getTradeHistory(@NonNull Integer tradePairId, @NonNull Integer count);

    /**
     * Returns a list of transactions
     *
     * @param type The type of transactions to return e.g. 'Deposit' or 'Withdraw'
     * @return a list of transactions
     */
    List<Transaction> getTransactions(@NonNull String type);

    /**
     * Returns a list of transactions
     *
     * @param type  type The type of transactions to return e.g. 'Deposit' or 'Withdraw'
     * @param count (optional) The maximum amount of transactions to return e.g. '10' (default: 100)
     * @return a list of transactions
     */
    List<Transaction> getTransactions(@NonNull String type, @NonNull Integer count);

    /**
     * Submits a new trade order
     *
     * @param market The market symbol of the trade e.g. 'DOT/BTC'
     * @param type   the type of trade e.g. 'Buy' or 'Sell'
     * @param rate   the rate or price to pay for the coins e.g. 0.00000034
     * @param amount the amount of coins to buy e.g. 123.00000000
     * @return An optional containing a tradeResponse
     */
    Optional<SubmitTrade> submitTrade(@NonNull String market, @NonNull String type, @NonNull BigDecimal rate, @NonNull BigDecimal amount);

    /**
     * Submits a new trade order
     *
     * @param tradePairId The Cryptopia tradepair identifier of trade e.g. '100'
     * @param type        the type of trade e.g. 'Buy' or 'Sell'
     * @param rate        the rate or price to pay for the coins e.g. 0.00000034
     * @param amount      the amount of coins to buy e.g. 123.00000000
     * @return An optional containing a tradeResponse
     */
    Optional<SubmitTrade> submitTrade(@NonNull Integer tradePairId, @NonNull String type, @NonNull BigDecimal rate, @NonNull BigDecimal amount);

    /**
     * Cancels all open trades
     * @return A list with the ids of the closed trades
     */
    List<Long> cancelAllTrades();

    /**
     * Cancel trades by orderID
     *
     * @param orderId The order identifier of trade to cancel
     * @return A list with the ids of the closed trades
     */
    List<Long> cancelTradesByOrderId(@NonNull BigInteger orderId);

    /**
     * Cancel all orders with an given tradepair
     *
     * @param tradePairId The Cryptopia tradepair identifier of trades to cancel e.g. '100'
     * @return A list with the ids of the closed trades
     */
    List<Long> cancelTradesByTradePairId(@NonNull Integer tradePairId);
}
