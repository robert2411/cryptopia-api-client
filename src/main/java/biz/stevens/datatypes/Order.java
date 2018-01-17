package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@ToString
public class Order {
    @SerializedName("TradePairId")
    private BigInteger tradePairId;
    @SerializedName("Label")
    private String label;
    @SerializedName("Price")
    private BigDecimal price;
    @SerializedName("Volume")
    private BigDecimal volume;
    @SerializedName("Total")
    private BigDecimal total;
}
