package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@ToString
public class MarketHistory {
    @SerializedName("TradePairId")
    private BigInteger tradePairId;

    @SerializedName("Label")
    private String label;

    @SerializedName("Type")
    private String type;

    @SerializedName("Price")
    private BigDecimal price;

    @SerializedName("Amount")
    private BigDecimal amount;

    @SerializedName("Total")
    private BigDecimal total;

    @SerializedName("Timestamp")
    private Long timestamp;
}
