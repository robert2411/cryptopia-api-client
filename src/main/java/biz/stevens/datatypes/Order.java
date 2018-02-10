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
public class Order {
    @JsonProperty(TRADE_PAIR_ID)
    @SerializedName(TRADE_PAIR_ID)
    private BigInteger tradePairId;

    @JsonProperty(LABEL)
    @SerializedName(LABEL)
    private String label;

    @JsonProperty(PRICE)
    @SerializedName(PRICE)
    private BigDecimal price;

    @JsonProperty(VOLUME)
    @SerializedName(VOLUME)
    private BigDecimal volume;

    @JsonProperty(TOTAL)
    @SerializedName(TOTAL)
    private BigDecimal total;
}
