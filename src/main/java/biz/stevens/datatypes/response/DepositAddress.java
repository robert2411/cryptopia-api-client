package biz.stevens.datatypes.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import static biz.stevens.datatypes.FieldNames.*;

@Getter
@ToString
public class DepositAddress {
    @JsonProperty(CURRENCY)
    @SerializedName(CURRENCY)
    private String currency;

    @JsonProperty(ADDRESS)
    @SerializedName(ADDRESS)
    private String address;

    @JsonProperty(BASE_ADDRESS)
    @SerializedName(BASE_ADDRESS)
    private String baseAddress;
}
