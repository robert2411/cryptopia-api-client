package biz.stevens.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

import static biz.stevens.datatypes.FieldNames.*;
import static biz.stevens.datatypes.FieldNames.AMOUNT;

@Getter
@ToString
public class TradeHistory {

    @JsonProperty(TRADE_ID)
    @SerializedName(TRADE_ID)
    private BigInteger tradeId;

    @JsonProperty(TRADE_PAIR_ID)
    @SerializedName(TRADE_PAIR_ID)
    private BigInteger tradePairId;

    @JsonProperty(MARKET)
    @SerializedName(MARKET)
    private String market;

    @JsonProperty(TYPE)
    @SerializedName(TYPE)
    private String type;

    @JsonProperty(RATE)
    @SerializedName(RATE)
    private BigDecimal rate;

    @JsonProperty(AMOUNT)
    @SerializedName(AMOUNT)
    private BigDecimal amount;

    @JsonProperty(TOTAL)
    @SerializedName(TOTAL)
    private BigDecimal total;

    @JsonProperty(FEE)
    @SerializedName(FEE)
    private BigDecimal fee;

    @JsonProperty(TIME_STAMP)
    @SerializedName(TIME_STAMP)
    private String timeStamp;
}