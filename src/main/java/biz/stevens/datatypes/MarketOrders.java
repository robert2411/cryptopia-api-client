package biz.stevens.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static biz.stevens.datatypes.FieldNames.BUY;
import static biz.stevens.datatypes.FieldNames.SELL;

@Getter
@ToString
public class MarketOrders {
    @JsonProperty(BUY)
    @SerializedName(BUY)
    List<Order> buy;

    @JsonProperty(SELL)
    @SerializedName(SELL)
    List<Order> sell;

}

