package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@ToString
public class OpenOrder {
    @SerializedName("OrderId")
    private BigInteger orderId;
    @SerializedName("TradePairId")
    private BigInteger tradePairId;
    @SerializedName("Type")
    private String type;
    @SerializedName("Rate")
    private BigDecimal rate;
    @SerializedName("Amount")
    private BigDecimal amount;
    @SerializedName("Total")
    private BigDecimal total;
    @SerializedName("Remaining")
    private BigDecimal remaining;
    @SerializedName("TimeStamp")
    private String timeStamp;
}
