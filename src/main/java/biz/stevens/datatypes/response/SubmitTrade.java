package biz.stevens.datatypes.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

import static biz.stevens.datatypes.FieldNames.FILLED_ORDERS;
import static biz.stevens.datatypes.FieldNames.ORDER_ID;

@Getter
@ToString
public class SubmitTrade {
    @JsonProperty(ORDER_ID)
    @SerializedName(ORDER_ID)
    private BigInteger orderId;

    @JsonProperty(FILLED_ORDERS)
    @SerializedName(FILLED_ORDERS)
    private List<BigInteger> filledOrders;
}
