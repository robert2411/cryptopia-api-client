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
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class CryptopiaImpl implements Cryptopia {
    private final String privateKey;
    private final String publicKey;
    private final String privateApiBaseUrl;
    private final String publicApiBaseUrl;

    public CryptopiaImpl() throws ConfigurationException {
        Configuration config = new PropertiesConfiguration("cryptopia.properties");
        privateKey = config.getString("privateKey");
        publicKey = config.getString("publicKey");
        privateApiBaseUrl = config.getString("privateApiBaseUrl");
        publicApiBaseUrl = config.getString("publicApiBaseUrl");
    }

    public CryptopiaImpl(@NonNull final String privateKey, @NonNull final String publicKey, @NonNull final String privateApiBaseUrl, @NonNull final String publicApiBaseUrl) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.privateApiBaseUrl = privateApiBaseUrl;
        this.publicApiBaseUrl = publicApiBaseUrl;
    }

    private static String getNonce() {
        return Long.toString(System.currentTimeMillis());
    }

    @Override
    public List<Currency> getCurrencies() {
        Optional<String> json = publicCall("GetCurrencies");

        if (json.isPresent()) {
            return from(json.get()).getList("Data", Currency.class);
        }
        return Collections.emptyList();
    }

    @Override
    public List<TradePair> getTradePairs() {
        Optional<String> json = publicCall("GetTradePairs");

        if (json.isPresent()) {
            return from(json.get()).getList("Data", TradePair.class);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Market> getMarkets(final String baseMarket, final int hours) {
        String endpoint = "GetMarkets";
        if (baseMarket != null) {
            endpoint += "/" + baseMarket;
        }
        if (hours != -1) {
            endpoint += "/" + hours;
        }

        Optional<String> json = publicCall(endpoint);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", Market.class);
        }
        return Collections.emptyList();

    }

    @Override
    public List<Market> getMarkets(final String baseMarket) {
        return getMarkets(baseMarket, -1);

    }

    @Override
    public List<Market> getMarkets(final int hours) {
        return getMarkets(null, hours);

    }

    @Override
    public List<Market> getMarkets() {
        return getMarkets(null, -1);
    }

    @Override
    public Optional<Market> getMarket(@NonNull final String marketName, final int hours) {
        String endpoint = "GetMarket/" + marketName + "/" + Integer.toString(hours);

        Optional<String> json = publicCall(endpoint);
        return json.map(s -> from(s).getObject("Data", Market.class));

    }

    @Override
    public Optional<Market> getMarket(@NonNull final String marketName) {
        return getMarket(marketName, 24);

    }

    @Override
    public List<MarketHistory> getMarketHistory(@NonNull final String marketName) {
        return getMarketHistory(marketName, 24);
    }

    @Override
    public List<MarketHistory> getMarketHistory(@NonNull final String marketName, final int hours) {
        String endpoint = "GetMarketHistory/" + marketName + "/" + Integer.toString(hours);

        Optional<String> json = publicCall(endpoint);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", MarketHistory.class);
        }
        return Collections.emptyList();

    }

    @Override
    public Optional<MarketOrders> getMarketOrders(@NonNull final String marketName) {
        return getMarketOrders(marketName, 50);
    }

    @Override
    public Optional<MarketOrders> getMarketOrders(@NonNull final String marketName, final int orderCount) {
        String endpoint = "GetMarketOrders/" + marketName + "/" + Integer.toString(orderCount);

        Optional<String> json = publicCall(endpoint);

        return json.map(s -> from(s).getObject("Data", MarketOrders.class));

    }

    @Override
    public List<MarketOrderGroup> getMarketOrderGroups(@NonNull final String marketName) {
        return getMarketOrderGroups(marketName, 100);
    }

    @Override
    public List<MarketOrderGroup> getMarketOrderGroups(@NonNull final String marketName, final int orderCount) {
        String endpoint = "GetMarketOrderGroups/" + marketName + "/" + Integer.toString(orderCount);

        Optional<String> json = publicCall(endpoint);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", MarketOrderGroup.class);
        }
        return Collections.emptyList();
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

    private Optional<String> publicCall(final String endpoint) {
        try {
            Response response = given().
                    contentType(ContentType.JSON).
                    when().
                    get(this.publicApiBaseUrl + endpoint);
            response.then().
                    statusCode(200).
                    body("Success", equalTo(true));
            //TODO do something with the error field
            log.info("[PREF][PUBLIC] calling [{}] took [{}mS]", endpoint, response.time());
            return Optional.ofNullable(response.asString());
        } catch (Exception e) {
            log.error("Something went wrong while making publicCall: [{}] Exception [{}]", endpoint, e);
            return Optional.empty();
        }
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
                    .post(this.publicApiBaseUrl + endpoint);
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


}
