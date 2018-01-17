package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

@Getter
@ToString
public class MarketOrderGroup {
    @SerializedName("TradePairId")
    private BigInteger tradePairId;

    @SerializedName("Market")
    private String market;

    @SerializedName("Buy")
    List<Order> buy;

    @SerializedName("Sell")
    List<Order> sell;

}
