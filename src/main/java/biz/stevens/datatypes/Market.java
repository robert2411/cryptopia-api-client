package biz.stevens.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

import static biz.stevens.datatypes.FieldNames.*;

@Getter
@ToString
public class Market {
    @JsonProperty(TRADE_PAIR_ID)
    @SerializedName(TRADE_PAIR_ID)
    private BigInteger tradePairId;

    @JsonProperty(LABEL)
    @SerializedName(LABEL)
    private String label;

    @JsonProperty(ASK_PRICE)
    @SerializedName(ASK_PRICE)
    private BigDecimal askPrice;

    @JsonProperty(BID_PRICE)
    @SerializedName(BID_PRICE)
    private BigDecimal bidPrice;

    @JsonProperty(LOW)
    @SerializedName(LOW)
    private BigDecimal low;

    @JsonProperty(HIGH)
    @SerializedName(HIGH)
    private BigDecimal high;

    @JsonProperty(VOLUME)
    @SerializedName(VOLUME)
    private BigDecimal volume;

    @JsonProperty(LAST_PRICE)
    @SerializedName(LAST_PRICE)
    private BigDecimal lastPrice;

    @JsonProperty(BUY_VOLUME)
    @SerializedName(BUY_VOLUME)
    private BigDecimal buyVolume;

    @JsonProperty(SELL_VOLUME)
    @SerializedName(SELL_VOLUME)
    private BigDecimal sellVolume;

    @JsonProperty(CHANGE)
    @SerializedName(CHANGE)
    private BigDecimal change;

    @JsonProperty(OPEN)
    @SerializedName(OPEN)
    private BigDecimal open;

    @JsonProperty(CLOSE)
    @SerializedName(CLOSE)
    private BigDecimal close;

    @JsonProperty(BASE_VOLUME)
    @SerializedName(BASE_VOLUME)
    private BigDecimal baseVolume;

    @JsonProperty(BUY_BASE_VOLUME)
    @SerializedName(BUY_BASE_VOLUME)
    private BigDecimal buyBaseVolume;

    @JsonProperty(SELL_BASE_VOLUME)
    @SerializedName(SELL_BASE_VOLUME)
    private BigDecimal sellBaseVolume;
}
