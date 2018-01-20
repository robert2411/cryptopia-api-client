package biz.stevens.datatypes;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DepositAddress {
    @SerializedName("Currency")
    private String currency;

    @SerializedName("Address")
    private String address;

    @SerializedName("BaseAddress")
    private String baseAddress;
}
