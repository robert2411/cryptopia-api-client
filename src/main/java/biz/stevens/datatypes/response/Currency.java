package biz.stevens.datatypes.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

import static biz.stevens.datatypes.FieldNames.*;

@Getter
@ToString
public class Currency {
    @JsonProperty(ID)
    @SerializedName(ID)
    private Integer id;

    @JsonProperty(NAME)
    @SerializedName(NAME)
    private String name;

    @JsonProperty(SYMBOL)
    @SerializedName(SYMBOL)
    private String symbol;

    @JsonProperty(ALGORITHM)
    @SerializedName(ALGORITHM)
    private String algorithm;

    @JsonProperty(WITHDRAW_FEE)
    @SerializedName(WITHDRAW_FEE)
    private BigDecimal withdrawFee;

    @JsonProperty(MIN_WITHDRAW)
    @SerializedName(MIN_WITHDRAW)
    private BigDecimal minWithdraw;

    @JsonProperty(MAX_WITHDRAW)
    @SerializedName(MAX_WITHDRAW)
    private BigDecimal maxWithdraw;

    @JsonProperty(MIN_BASE_TRADE)
    @SerializedName(MIN_BASE_TRADE)
    private BigDecimal minBaseTrade;

    @JsonProperty(IS_TIP_ENABLED)
    @SerializedName(IS_TIP_ENABLED)
    private Boolean isTipEnabled;

    @JsonProperty(MIN_TIP)
    @SerializedName(MIN_TIP)
    private BigDecimal minTip;

    @JsonProperty(DEPOSIT_CONFIRMATIONS)
    @SerializedName(DEPOSIT_CONFIRMATIONS)
    private Integer depositConfirmations;

    @JsonProperty(STATUS)
    @SerializedName(STATUS)
    private String status;

    @JsonProperty(STATUS_MESSAGE)
    @SerializedName(STATUS_MESSAGE)
    private String statusMessage;

    @JsonProperty(LISTING_STATUS)
    @SerializedName(LISTING_STATUS)
    private String listingStatus;


}
