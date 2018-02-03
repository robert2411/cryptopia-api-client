package biz.stevens.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

import static biz.stevens.datatypes.FieldNames.*;

@Getter
@ToString
public class Balance {
    @JsonProperty(CURRENCY_ID)
    @SerializedName(CURRENCY_ID)
    private BigInteger currencyId;

    @JsonProperty(SYMBOL)
    @SerializedName(SYMBOL)
    private String symbol;

    @JsonProperty(TOTAL)
    @SerializedName(TOTAL)
    private BigDecimal total;

    @JsonProperty(AVAILABLE)
    @SerializedName(AVAILABLE)
    private BigDecimal available;

    @JsonProperty(UNCONFIRMED)
    @SerializedName(UNCONFIRMED)
    private BigDecimal unconfirmed;

    @JsonProperty(HELD_FOR_TRADES)
    @SerializedName(HELD_FOR_TRADES)
    private BigDecimal heldForTrades;

    @JsonProperty(PENDING_WITHDRAW)
    @SerializedName(PENDING_WITHDRAW)
    private BigDecimal pendingWithdraw;

    @JsonProperty(ADDRESS)
    @SerializedName(ADDRESS)
    private String address;

    @JsonProperty(BASE_ADDRESS)
    @SerializedName(BASE_ADDRESS)
    private String baseAddress;

    @JsonProperty(STATUS)
    @SerializedName(STATUS)
    private String status;

    @JsonProperty(STATUS_MESSAGE)
    @SerializedName(STATUS_MESSAGE)
    private String statusMessage;
}
