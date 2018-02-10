package biz.stevens.exchange;

import biz.stevens.datatypes.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonParserType;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class CryptopiaPublicImpl implements CryptopiaPublic {

    private final String publicApiBaseUrl;

    public CryptopiaPublicImpl() throws ConfigurationException {
        Configuration config = new PropertiesConfiguration("cryptopia.properties");
        this.publicApiBaseUrl = config.getString("publicApiBaseUrl", "https://www.cryptopia.co.nz/api/");
    }

    /**
     * @param publicApiBaseUrl The public api url normally this should be "https://www.cryptopia.co.nz/api/"
     */
    public CryptopiaPublicImpl(@NonNull final String publicApiBaseUrl) {
        this.publicApiBaseUrl = publicApiBaseUrl;
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
    public Optional<Market> getMarket(Integer tradePairId) {
        return getMarket(tradePairId.toString());
    }

    @Override
    public Optional<Market> getMarket(Integer tradePairId, int hours) {
        return getMarket(tradePairId.toString(), hours);
    }

    @Override
    public Optional<Market> getMarket(@NonNull final String marketName) {
        return getMarket(marketName, 24);

    }

    @Override
    public Optional<Market> getMarket(@NonNull final String marketName, final int hours) {
        String endpoint = "GetMarket/" + marketName + "/" + Integer.toString(hours);

        Optional<String> json = publicCall(endpoint);
        return json.map(s -> from(s).getObject("Data", Market.class));

    }

    @Override
    public List<MarketHistory> getMarketHistory(Integer tradePairId) {
        return getMarketHistory(tradePairId.toString());
    }

    @Override
    public List<MarketHistory> getMarketHistory(Integer tradePairId, int hours) {
        return getMarketHistory(tradePairId.toString(), hours);
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
    public Optional<MarketOrders> getMarketOrders(Integer tradePairId) {
        return getMarketOrders(tradePairId.toString());
    }

    @Override
    public Optional<MarketOrders> getMarketOrders(Integer tradePairId, int orderCount) {
        return getMarketOrders(tradePairId.toString(), orderCount);
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
    public List<MarketOrderGroup> getMarketOrderGroups(@NonNull final String marketName, @NonNull  String... marketNames) {

        return getMarketOrderGroups(100, marketName, marketNames);
    }

    @Override
    public List<MarketOrderGroup> getMarketOrderGroups(final int orderCount, @NonNull final String marketName, @NonNull  String... marketNames) {
        String tempMarketName = marketName;
        for (String market: marketNames){
            tempMarketName += "-" + market;
        }
        String endpoint = "GetMarketOrderGroups/" + tempMarketName + "/" + Integer.toString(orderCount);

        Optional<String> json = publicCall(endpoint);

        if (json.isPresent()) {
            return from(json.get()).getList("Data", MarketOrderGroup.class);
        }
        return Collections.emptyList();
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

}
