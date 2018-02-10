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
public class OpenOrder {
    @JsonProperty(ORDER_ID)
    @SerializedName(ORDER_ID)
    private BigInteger orderId;

    @JsonProperty(TRADE_PAIR_ID)
    @SerializedName(TRADE_PAIR_ID)
    private BigInteger tradePairId;

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

    @JsonProperty(REMAINING)
    @SerializedName(REMAINING)
    private BigDecimal remaining;

    @JsonProperty(TIME_STAMP)
    @SerializedName(TIME_STAMP)
    private String timeStamp;
}
