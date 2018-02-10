package biz.stevens.datatypes.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

import static biz.stevens.datatypes.FieldNames.*;
import static biz.stevens.datatypes.FieldNames.AMOUNT;

@ToString
@Getter
public class Transaction {
    @JsonProperty(ID)
    @SerializedName(ID)
    private BigInteger id;

    @JsonProperty(CURRENCY)
    @SerializedName(CURRENCY)
    private String currency;

    @JsonProperty(TX_ID)
    @SerializedName(TX_ID)
    private String txId;

    @JsonProperty(TYPE)
    @SerializedName(TYPE)
    private String type;

    @JsonProperty(AMOUNT)
    @SerializedName(AMOUNT)
    private BigDecimal amount;

    @JsonProperty(FEE)
    @SerializedName(FEE)
    private BigDecimal fee;

    @JsonProperty(STATUS)
    @SerializedName(STATUS)
    private String status;

    @JsonProperty(CONFIRMATIONS)
    @SerializedName(CONFIRMATIONS)
    private BigInteger confirmations;

    @JsonProperty(TIME_STAMP)
    @SerializedName(TIME_STAMP)
    private String timeStamp;

    @JsonProperty(ADDRESS)
    @SerializedName(ADDRESS)
    private String address;
}
