package biz.stevens.exchange;

import biz.stevens.datatypes.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

@Slf4j
public class CryptopiaPrivateImpl implements CryptopiaPrivate {
    private final String privateKey;
    private final String publicKey;
    private final String privateApiBaseUrl;

    public CryptopiaPrivateImpl() throws ConfigurationException {
        Configuration config = new PropertiesConfiguration("cryptopia.properties");
        privateKey = config.getString("privateKey");
        publicKey = config.getString("publicKey");
        privateApiBaseUrl = config.getString("privateApiBaseUrl");
    }

    public CryptopiaPrivateImpl(@NonNull final String privateKey, @NonNull final String publicKey, @NonNull final String privateApiBaseUrl, @NonNull final String publicApiBaseUrl) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.privateApiBaseUrl = privateApiBaseUrl;
    }



    @Override
    public List<Balance> getBalance() {
        return getBalanceHelper("{}");
    }

    /**
     * This call is not properly tested (only visually)
     */
    @Override
    public List<Balance> getBalance(@NonNull final Integer currencyId) {
        return getBalanceHelper("{\"CurrencyId\":" + currencyId + "}");
    }

    /**
     * This call is not properly tested (only visually)
     */
    @Override
    public List<Balance> getBalance(@NonNull final String currencyName) {
        return getBalanceHelper("{\"Currency\":\"" + currencyName + "\"}");
    }

    private List<Balance> getBalanceHelper(@NonNull final String jsonPostParam) {
        String endpoint = "GetBalance";
        Optional<String> json = privateCall(endpoint, jsonPostParam);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", Balance.class);
        }
        return Collections.emptyList();
    }

    /**
     * This call is not properly tested (only visually)
     */
    @Override
    public Optional<DepositAddress> getDepositAddress(@NonNull final Integer currencyId) {
        return getDepositAddressHelper("{\"CurrencyId\":" + currencyId + "}");
    }

    /**
     * This call is not properly tested (only visually)
     */
    @Override
    public Optional<DepositAddress> getDepositAddress(@NonNull final String currencyName) {
        return getDepositAddressHelper("{\"Currency\":\"" + currencyName + "\"}");
    }

    private Optional<DepositAddress> getDepositAddressHelper(@NonNull final String jsonPostParam) {
        String endpoint = "GetDepositAddress";
        Optional<String> json = privateCall(endpoint, jsonPostParam);

        return json.map(s -> from(s).getObject("Data", DepositAddress.class));
    }


    @Override
    public List<OpenOrder> getOpenOrders() {
        return getOpenOrdersHelper("{}");
    }

    @Override
    public List<OpenOrder> getOpenOrders(@NonNull final String market) {
        return getOpenOrdersHelper("{\"Market\":\"" + market + "\"}");
    }

    @Override
    public List<OpenOrder> getOpenOrders(@NonNull final String market, @NonNull final Integer count) {
        return getOpenOrdersHelper("{\"Market\":\"" + market + "\", \"Count\":" + count + "}");
    }

    @Override
    public List<OpenOrder> getOpenOrders(@NonNull final Integer tradePairId) {
        return getOpenOrdersHelper("{\"TradePairId\":" + tradePairId + "}");
    }

    @Override
    public List<OpenOrder> getOpenOrders(@NonNull final Integer tradePairId, @NonNull final Integer count) {
        return getOpenOrdersHelper("{\"TradePairId\":" + tradePairId + ", \"Count\":" + count + "}");
    }

    private List<OpenOrder> getOpenOrdersHelper(@NonNull final String jsonPostParam) {
        String endpoint = "GetOpenOrders";
        Optional<String> json = privateCall(endpoint, jsonPostParam);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", OpenOrder.class);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TradeHistory> getTradeHistory() {
        return getTradeHistoryHelper("{}");
    }

    @Override
    public List<TradeHistory> getTradeHistory(@NonNull final String market) {
        return getTradeHistoryHelper("{\"Market\":\"" + market + "\"}");
    }

    @Override
    public List<TradeHistory> getTradeHistory(@NonNull final String market, @NonNull final Integer count) {
        return getTradeHistoryHelper("{\"Market\":\"" + market + "\", \"Count\":" + count + "}");
    }

    @Override
    public List<TradeHistory> getTradeHistory(@NonNull final Integer tradePairId) {
        return getTradeHistoryHelper("{\"TradePairId\":" + tradePairId + "}");
    }

    @Override
    public List<TradeHistory> getTradeHistory(@NonNull final Integer tradePairId, @NonNull final Integer count) {
        return getTradeHistoryHelper("{\"TradePairId\":" + tradePairId + ", \"Count\":" + count + "}");
    }

    private List<TradeHistory> getTradeHistoryHelper(@NonNull final String jsonPostParam) {
        String endpoint = "GetTradeHistory";
        Optional<String> json = privateCall(endpoint, jsonPostParam);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", TradeHistory.class);
        }
        return Collections.emptyList();
    }

    /**
     * Returns a list of transactions
     *
     * @param type The type of transactions to return e.g. 'Deposit' or 'Withdraw'
     * @return a list of transactions
     */
    @Override
    public List<Transaction> getTransactions(@NonNull final String type) {
        return getTransactionsHelper("{\"Type\":\"" + type + "\"}");
    }

    /**
     * Returns a list of transactions
     *
     * @param type  type The type of transactions to return e.g. 'Deposit' or 'Withdraw'
     * @param count (optional) The maximum amount of transactions to return e.g. '10' (default: 100)
     * @return a list of transactions
     */
    @Override
    public List<Transaction> getTransactions(@NonNull final String type, @NonNull final Integer count) {
        return getTransactionsHelper("{\"Type\":\"" + type + "\", \"Count\":" + count + "}");
    }

    private List<Transaction> getTransactionsHelper(@NonNull final String jsonPostParam) {
        String endpoint = "GetTransactions";
        Optional<String> json = privateCall(endpoint, jsonPostParam);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", Transaction.class);
        }
        return Collections.emptyList();
    }

    private static String getNonce() {
        return Long.toString(System.currentTimeMillis());
    }

    @SneakyThrows
    private String getReqSignature(final String nonce, final String endpoint, final String jSonPostParam) {
        return this.publicKey
                + "POST"
                + URLEncoder.encode(this.privateApiBaseUrl + endpoint, StandardCharsets.UTF_8.toString()).toLowerCase()
                + nonce
                + getMD5_B64(jSonPostParam);


    }

    private String getAUTH(final String nonce, final String reqSignature) {
        return "amx "
                + this.publicKey
                + ":"
                + sha256_B64(reqSignature)
                + ":"
                + nonce;
    }

    @SneakyThrows
    private String getMD5_B64(@NonNull final String postParameter) {
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(postParameter.getBytes("UTF-8")));
    }

    @SneakyThrows
    private String sha256_B64(@NonNull final String msg) {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(Base64.getDecoder().decode(this.privateKey), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(msg.getBytes("UTF-8")));
    }

    //Request data in json body
    private Optional<String> privateCall(@NonNull final String endpoint, @NonNull final String jSonPostParam) {
        try {
            //https://www.cryptopia.co.nz/Forum/Thread/262
            final String nonce = getNonce();
            final String reqSignature = getReqSignature(nonce, endpoint, jSonPostParam);
            final String AUTH = getAUTH(nonce, reqSignature);

            Response response = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", AUTH)
                    .body(jSonPostParam)
                    .when()
                    .post(this.privateApiBaseUrl + endpoint);
            response.then()
                    .statusCode(200);
            //.body("Success", equalTo(true));
            //TODO do something with the error field; ﻿{"Success":false,"Error":"Invalid authorization header."}
            log.info("[PREF][PRIVATE] calling [{}] took [{}mS]", endpoint, response.time());
            System.out.println(response.asString());
            return Optional.ofNullable(response.asString());
        } catch (Exception e) {
            log.error("Something went wrong while making publicCall: [{}] Exception [{}]", endpoint, e);
            return Optional.empty();
        }
    }


}
