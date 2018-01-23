package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

@Getter
@ToString
public class SubmitTrade {
    @SerializedName("OrderId")
    private BigInteger orderId;

    @SerializedName("FilledOrders")
    private List<BigInteger> filledOrders;
}
