package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigInteger;

@ToString
@Getter
public class TradePair {
    @SerializedName("Id")
    private BigInteger id;

    @SerializedName("Label")
    private String label;

    @SerializedName("Currency")
    private String currency;

    @SerializedName("Symbol")
    private String symbol;

    @SerializedName("BaseCurrency")
    private String baseCurrency;

    @SerializedName("BaseSymbol")
    private String baseSymbol;

    @SerializedName("Status")
    private String status;

    @SerializedName("StatusMessage")
    private String statusMessage;

    @SerializedName("TradeFee")
    private String tradeFee;

    @SerializedName("MinimumTrade")
    private String minimumTrade;

    @SerializedName("MaximumTrade")
    private String maximumTrade;

    @SerializedName("MinimumBaseTrade")
    private String minimumBaseTrade;

    @SerializedName("MaximumBaseTrade")
    private String maximumBaseTrade;

    @SerializedName("MinimumPrice")
    private String minimumPrice;

    @SerializedName("MaximumPrice")
    private String maximumPrice;

}
