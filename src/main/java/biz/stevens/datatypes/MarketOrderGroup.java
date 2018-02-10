package biz.stevens.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

import static biz.stevens.datatypes.FieldNames.*;

@Getter
@ToString
public class MarketOrderGroup {
    @JsonProperty(TRADE_PAIR_ID)
    @SerializedName(TRADE_PAIR_ID)
    private BigInteger tradePairId;

    @JsonProperty(MARKET)
    @SerializedName(MARKET)
    private String market;

    @JsonProperty(BUY)
    @SerializedName(BUY)
    List<Order> buy;

    @JsonProperty(SELL)
    @SerializedName(SELL)
    List<Order> sell;

}
