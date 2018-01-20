package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@ToString
public class Market {
    @SerializedName("TradePairId")
    private BigInteger tradePairId;

    @SerializedName("Label")
    private String label;

    @SerializedName("AskPrice")
    private BigDecimal askPrice;

    @SerializedName("BidPrice")
    private BigDecimal bidPrice;

    @SerializedName("Low")
    private BigDecimal low;

    @SerializedName("High")
    private BigDecimal high;

    @SerializedName("Volume")
    private BigDecimal volume;

    @SerializedName("LastPrice")
    private BigDecimal lastPrice;

    @SerializedName("BuyVolume")
    private BigDecimal buyVolume;

    @SerializedName("SellVolume")
    private BigDecimal sellVolume;

    @SerializedName("Change")
    private BigDecimal change;

    @SerializedName("Open")
    private BigDecimal open;

    @SerializedName("Close")
    private BigDecimal close;

    @SerializedName("BaseVolume")
    private BigDecimal baseVolume;

    @SerializedName("BuyBaseVolume")
    private BigDecimal buyBaseVolume;

    @SerializedName("SellBaseVolume")
    private BigDecimal sellBaseVolume;
}
