package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@ToString
@Getter
public class Transaction {
    @SerializedName("Id")
    private BigInteger id;

    @SerializedName("Currency")
    private String currency;

    @SerializedName("TxId")
    private String txId;

    @SerializedName("Type")
    private String type;

    @SerializedName("Amount")
    private BigDecimal amount;

    @SerializedName("Fee")
    private BigDecimal fee;

    @SerializedName("Status")
    private String status;

    @SerializedName("Confirmations")
    private BigInteger confirmations;

    @SerializedName("TimeStamp")
    private String timeStamp;

    @SerializedName("Address")
    private String address;
}
