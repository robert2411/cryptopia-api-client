package biz.stevens.datatypes.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;

import static biz.stevens.datatypes.FieldNames.*;
import static biz.stevens.datatypes.FieldNames.CURRENCY;

@ToString
@Getter
public class TradePair {
    @JsonProperty(ID)
    @SerializedName(ID)
    private BigInteger id;

    @JsonProperty(LABEL)
    @SerializedName(LABEL)
    private String label;

    @JsonProperty(CURRENCY)
    @SerializedName(CURRENCY)
    private String currency;

    @JsonProperty(SYMBOL)
    @SerializedName(SYMBOL)
    private String symbol;

    @JsonProperty(BASE_CURRENCY)
    @SerializedName(BASE_CURRENCY)
    private String baseCurrency;

    @JsonProperty(BASE_SYMBOL)
    @SerializedName(BASE_SYMBOL)
    private String baseSymbol;

    @JsonProperty(STATUS)
    @SerializedName(STATUS)
    private String status;

    @JsonProperty(STATUS_MESSAGE)
    @SerializedName(STATUS_MESSAGE)
    private String statusMessage;

    @JsonProperty(TRADE_FEE)
    @SerializedName(TRADE_FEE)
    private String tradeFee;

    @JsonProperty(MINIMUM_TRADE)
    @SerializedName(MINIMUM_TRADE)
    private String minimumTrade;

    @JsonProperty(MAXIMUM_TRADE)
    @SerializedName(MAXIMUM_TRADE)
    private String maximumTrade;

    @JsonProperty(MINIMUM_BASE_TRADE)
    @SerializedName(MINIMUM_BASE_TRADE)
    private String minimumBaseTrade;

    @JsonProperty(MAXIMUM_BASE_TRADE)
    @SerializedName(MAXIMUM_BASE_TRADE)
    private String maximumBaseTrade;

    @JsonProperty(MINIMUM_PRICE)
    @SerializedName(MINIMUM_PRICE)
    private String minimumPrice;

    @JsonProperty(MAXIMUM_PRICE)
    @SerializedName(MAXIMUM_PRICE)
    private String maximumPrice;

}
