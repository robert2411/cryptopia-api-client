package biz.stevens.datatypes;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@ToString
public class TradeHistory {
    @SerializedName("TradeId")
    private BigInteger tradeId;
    @SerializedName("TradePairId")
    private BigInteger tradePairId;
    @SerializedName("Market")
    private String market;
    @SerializedName("Type")
    private String type;
    @SerializedName("Rate")
    private BigDecimal rate;
    @SerializedName("Amount")
    private BigDecimal amount;
    @SerializedName("Total")
    private BigDecimal total;
    @SerializedName("Fee")
    private BigDecimal fee;
    @SerializedName("TimeStamp")
    private String timeStamp;
}