package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class MarketOrders {
    @SerializedName("Buy")
    List<Order> buy;
    @SerializedName("Sell")
    List<Order> sell;

}

