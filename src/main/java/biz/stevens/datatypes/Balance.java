package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@ToString
public class Balance {
    @SerializedName("CurrencyId")
    private BigInteger currencyId;

    @SerializedName("Symbol")
    private String symbol;

    @SerializedName("Total")
    private BigDecimal total;

    @SerializedName("Available")
    private BigDecimal id;

    @SerializedName("Unconfirmed")
    private BigDecimal unconfirmed;

    @SerializedName("HeldForTrades")
    private BigDecimal heldForTrades;

    @SerializedName("PendingWithdraw")
    private BigDecimal pendingWithdraw;

    @SerializedName("Address")
    private String address;

    @SerializedName("BaseAddress")
    private String baseAddress;

    @SerializedName("Status")
    private String status;

    @SerializedName("StatusMessage")
    private String statusMessage;
}
