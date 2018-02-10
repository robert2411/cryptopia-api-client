package biz.stevens.datatypes.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

import static biz.stevens.datatypes.FieldNames.*;

@Getter
@ToString
public class MarketHistory {
    @JsonProperty(TRADE_PAIR_ID)
    @SerializedName(TRADE_PAIR_ID)
    private BigInteger tradePairId;

    @JsonProperty(LABEL)
    @SerializedName(LABEL)
    private String label;

    @JsonProperty(TYPE)
    @SerializedName(TYPE)
    private String type;

    @JsonProperty(PRICE)
    @SerializedName(PRICE)
    private BigDecimal price;

    @JsonProperty(AMOUNT)
    @SerializedName(AMOUNT)
    private BigDecimal amount;

    @JsonProperty(TOTAL)
    @SerializedName(TOTAL)
    private BigDecimal total;

    @JsonProperty(TIMESTAMP)
    @SerializedName(TIMESTAMP)
    private Long timestamp;
}
