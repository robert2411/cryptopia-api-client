package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@ToString
public class Currency {
    @SerializedName("Id")
    private BigInteger id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Symbol")
    private String symbol;

    @SerializedName("Algorithm")
    private String algorithm;

    @SerializedName("WithdrawFee")
    private BigDecimal withdrawFee;

    @SerializedName("MinWithdraw")
    private BigDecimal minWithdraw;

    @SerializedName("MinBaseTrade")
    private BigDecimal minBaseTrade;

    @SerializedName("IsTipEnabled")
    private Boolean isTipEnabled;

    @SerializedName("MinTip")
    private BigDecimal minTip;

    @SerializedName("DepositConfirmations")
    private BigInteger depositConfirmations;

    @SerializedName("Status")
    private String status;

    @SerializedName("StatusMessage")
    private String statusMessage;

    @SerializedName("ListingStatus")
    private String listingStatus;


}
