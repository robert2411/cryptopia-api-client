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
public class Cryptopia {
    private final String privateKey;
    private final String publicKey;
    private final String privateApiBaseUrl;
    private final String publicApiBaseUrl;

    public Cryptopia() throws ConfigurationException {
        Configuration config = new PropertiesConfiguration("cryptopia.properties");
        privateKey = config.getString("privateKey");
        publicKey = config.getString("publicKey");
        privateApiBaseUrl = config.getString("privateApiBaseUrl");
        publicApiBaseUrl = config.getString("publicApiBaseUrl");
    }
    public Cryptopia(@NonNull final String privateKey, @NonNull final String publicKey, @NonNull final String privateApiBaseUrl, @NonNull final String publicApiBaseUrl){
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.privateApiBaseUrl = privateApiBaseUrl;
        this.publicApiBaseUrl = publicApiBaseUrl;
    }

    public List<Currency> getCurrencies(){
        Optional<String> json = publicCall("GetCurrencies");

        if(json.isPresent()){
            return from(json.get()).getList("Data", Currency.class);
        }
        return Collections.emptyList();
    }

    public List<TradePair> getTradePairs(){
        Optional<String> json = publicCall("GetTradePairs");

        if(json.isPresent()){
            return from(json.get()).getList("Data", TradePair.class);
        }
        return Collections.emptyList();
    }

    public List<Market> getMarkets(final String baseMarket, final int hours){
        String endpoint = "GetMarkets";
        if (baseMarket != null){
            endpoint += "/"+ baseMarket;
        }
        if (hours != -1){
            endpoint += "/"+ hours;
        }

        Optional<String> json = publicCall(endpoint);

        if(json.isPresent()){
            return from(json.get()).getList("Data", Market.class);
        }
        return Collections.emptyList();

    }

    public List<Market> getMarkets(final String baseMarket){
        return getMarkets(baseMarket, -1);

    }

    public List<Market> getMarkets(final int hours){
        return getMarkets(null, hours);

    }

    public List<Market> getMarkets(){
        return getMarkets(null, -1);
    }

    public Optional<Market> getMarket(@NonNull final String marketName, final int hours){
        String endpoint = "GetMarket/" + marketName + "/" + Integer.toString(hours);

        Optional<String> json = publicCall(endpoint);
        return json.map(s -> from(s).getObject("Data", Market.class));

    }

    public Optional<Market> getMarket(@NonNull final String marketName){
        return getMarket(marketName, 24);

    }

    public List<MarketHistory> getMarketHistory(@NonNull final String marketName){
        return getMarketHistory(marketName, 24);
    }
    public List<MarketHistory> getMarketHistory(@NonNull final String marketName, final int hours){
        String endpoint = "GetMarketHistory/" + marketName + "/" + Integer.toString(hours);

        Optional<String> json = publicCall(endpoint);

        if(json.isPresent()){
            return from(json.get()).getList("Data", MarketHistory.class);
        }
        return Collections.emptyList();

    }


    public Optional<MarketOrders> getMarketOrders(@NonNull final String marketName){
        return getMarketOrders(marketName, 50);
    }

    public Optional<MarketOrders> getMarketOrders(@NonNull final String marketName, final int orderCount){
        String endpoint = "GetMarketOrders/" + marketName + "/" + Integer.toString(orderCount);

        Optional<String> json = publicCall(endpoint);

        return json.map(s -> from(s).getObject("Data", MarketOrders.class));

    }

    public List<MarketOrderGroup> getMarketOrderGroups(@NonNull final String marketName){
        return getMarketOrderGroups(marketName, 100);
    }

    public List<MarketOrderGroup> getMarketOrderGroups(@NonNull final String marketName, final int orderCount){
        String endpoint = "GetMarketOrderGroups/" + marketName + "/" + Integer.toString(orderCount);

        Optional<String> json = publicCall(endpoint);

        if(json.isPresent()){
            return from(json.get()).getList("Data", MarketOrderGroup.class);
        }
        return Collections.emptyList();
    }


    public void getGetBalance(){
        String endpoint = "GetBalance";
        Optional<String> json = privateCall(endpoint, "{}");
        //{"Currency":"DOT"}
        json.ifPresent(System.out::println);
    }


    private Optional<String> publicCall(final String endpoint){
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
        }catch (Exception e){
            log.error("Something went wrong while making publicCall: [{}] Exception [{}]", endpoint, e);
            return Optional.empty();
        }
    }

    //Request data in json body
    private Optional<String> privateCall(@NonNull final String endpoint,@NonNull final String jSonPostParam){
        try {
            //https://www.cryptopia.co.nz/Forum/Thread/262
            String nonce = getNonce();
            String reqSignature = getReqSignature(nonce, endpoint, jSonPostParam);
            String AUTH = getAUTH(nonce, reqSignature);

            Response response = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", AUTH)
                    .body(jSonPostParam)
                    .when()
                    .post(this.publicApiBaseUrl + endpoint);
            response.then().
                    statusCode(200);//.
                   // body("Success", equalTo(true));
            //TODO do something with the error field; ﻿{"Success":false,"Error":"Invalid authorization header."}
            log.info("[PREF][PRIVATE] calling [{}] took [{}mS]", endpoint, response.time());
            return Optional.ofNullable(response.asString());
        }catch (Exception e){
            System.out.println("Something went wrong while making publicCall: ["+endpoint+"] Exception ["+e+"]");
            log.error("Something went wrong while making publicCall: [{}] Exception [{}]", endpoint, e);
            return Optional.empty();
        }
    }

    private static String getNonce(){
        return Long.toString(System.currentTimeMillis());
    }

    @SneakyThrows
    private String getReqSignature(final String nonce, final String endpoint, final String jSonPostParam){
        return this.publicKey
                        + "POST"
                        + URLEncoder.encode(this.privateApiBaseUrl + endpoint, StandardCharsets.UTF_8.toString()).toLowerCase()
                        + nonce
                        + getMD5_B64(jSonPostParam);


    }

    private String getAUTH(final String nonce, final String reqSignature){
        return  "amx "
                + this.publicKey
                +":"
                + sha256_B64(reqSignature)
                +":"
                + nonce;
    }

    @SneakyThrows
    private String getMD5_B64(@NonNull final String postParameter) {
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest(postParameter.getBytes("UTF-8")));
    }

    @SneakyThrows
    private String sha256_B64(@NonNull final String msg){
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(Base64.getDecoder().decode(this.privateKey), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(msg.getBytes("UTF-8")));
    }






}
