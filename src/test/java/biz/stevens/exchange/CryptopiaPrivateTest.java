package biz.stevens.exchange;

import biz.stevens.datatypes.response.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static biz.stevens.exchange.TestHelper.getResourceAsString;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CryptopiaPrivateTest {
    private static final String CURRENCY_NAME = "ETN";
    private static final int CURRENCY_ID = 695;
    private static final String BASE_MARKET = "ETN";
    private static final String MARKET = "ETN/USDT";
    private static final int TRADEPAIR_ID = 5760;
    private static final int COUNT = 10;
    private WireMockServer wireMockServer;

    private CryptopiaPrivate cryptopia;
    @BeforeClass
    public void setup() {
        wireMockServer = new WireMockServer(); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();
        int port = wireMockServer.port();
        String url = "http://localhost:" + Integer.toString(port) + "/";
        this.cryptopia = new CryptopiaPrivateImpl("privateKey", "publicKey", url);
    }

    @AfterClass(alwaysRun = true)
    public void cleanup() {
        wireMockServer.stop();
    }


    @Test
    public void testGetGetBalance() {
        addMockEndpoint("/GetBalance", "getBalanceResponse.json");
        List<Balance> balance = cryptopia.getBalance();
        Assert.assertFalse(balance.isEmpty());
    }

    @Test
    public void testGetGetBalanceCurrency() {
        addMockEndpoint("/GetBalance", "getBalanceResponse.json");
        List<Balance> balance = cryptopia.getBalance(CURRENCY_NAME);
        Assert.assertFalse(balance.isEmpty());
    }

    @Test
    public void testGetGetBalanceCurrencyId() {
        addMockEndpoint("/GetBalance", "getBalanceResponse.json");
        List<Balance> balance = cryptopia.getBalance(CURRENCY_ID);
        Assert.assertFalse(balance.isEmpty());
    }

    @Test
    public void testGetDepositAddressCurrency() {
        addMockEndpoint("/GetDepositAddress", "getDepositAddressResponse.json");
        Optional<DepositAddress> address = cryptopia.getDepositAddress(CURRENCY_NAME);
        Assert.assertTrue(address.isPresent());
    }

    @Test
    public void testGetDepositAddressCurrencyId() {
        addMockEndpoint("/GetDepositAddress", "getDepositAddressResponse.json");
        Optional<DepositAddress> address = cryptopia.getDepositAddress(CURRENCY_ID);
        Assert.assertTrue(address.isPresent());
    }

    @Test
    public void testGetOpenOrders() {
        addMockEndpoint("/GetOpenOrders", "getOpenOrdersResponse.json");
        List<OpenOrder> openOrders = cryptopia.getOpenOrders();
        Assert.assertFalse(openOrders.isEmpty());
    }

    @Test
    public void testGetOpenOrdersMarket() {
        addMockEndpoint("/GetOpenOrders", "getOpenOrdersResponse.json");
        List<OpenOrder> openOrders = cryptopia.getOpenOrders(MARKET);
        Assert.assertFalse(openOrders.isEmpty());
    }

    @Test
    public void testGetOpenOrdersMarketCount() {
        addMockEndpoint("/GetOpenOrders", "getOpenOrdersResponse.json");
        List<OpenOrder> openOrders = cryptopia.getOpenOrders(MARKET, COUNT);
        Assert.assertFalse(openOrders.isEmpty());
    }

    @Test
    public void testGetOpenOrdersTradePairIdCount() {
        addMockEndpoint("/GetOpenOrders", "getOpenOrdersResponse.json");
        List<OpenOrder> openOrders = cryptopia.getOpenOrders(TRADEPAIR_ID, COUNT);
        Assert.assertFalse(openOrders.isEmpty());
    }

    @Test
    public void testGetOpenOrdersTradePairId() {
        addMockEndpoint("/GetOpenOrders", "getOpenOrdersResponse.json");
        List<OpenOrder> openOrders = cryptopia.getOpenOrders(TRADEPAIR_ID);
        Assert.assertFalse(openOrders.isEmpty());
    }

    @Test
    public void testGetTradeHistory() {
        addMockEndpoint("/GetTradeHistory", "getTradeHistoryResponse.json");
        List<TradeHistory> history = cryptopia.getTradeHistory();
        Assert.assertFalse(history.isEmpty());
    }

    @Test
    public void testGetTradeHistoryMarket() {
        addMockEndpoint("/GetTradeHistory", "getTradeHistoryResponse.json");
        List<TradeHistory> history = cryptopia.getTradeHistory(MARKET);
        Assert.assertFalse(history.isEmpty());
    }

    @Test
    public void testGetGetTradeHistoryMarketCount() {
        addMockEndpoint("/GetTradeHistory", "getTradeHistoryResponse.json");
        List<TradeHistory> history = cryptopia.getTradeHistory(MARKET, COUNT);
        Assert.assertFalse(history.isEmpty());
    }

    @Test
    public void testGetTradeHistoryTradePairIdCount() {
        addMockEndpoint("/GetTradeHistory", "getTradeHistoryResponse.json");
        List<TradeHistory> history = cryptopia.getTradeHistory(TRADEPAIR_ID, COUNT);
        Assert.assertFalse(history.isEmpty());
    }

    @Test
    public void testGetTradeHistoryTradePairId() {
        //{"Success":true,"Error":null,"Data":[{"TradeId":50379428,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.08800000,"Amount":31.97137500,"Total":2.81348100,"Fee":0.00562696,"TimeStamp":"2018-02-26T01:17:27.6827298"},{"TradeId":50375372,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.08888000,"Amount":31.78319760,"Total":2.82489060,"Fee":0.00564978,"TimeStamp":"2018-02-26T00:39:18.049907"},{"TradeId":50373147,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.08800000,"Amount":31.78508288,"Total":2.79708729,"Fee":0.00559417,"TimeStamp":"2018-02-26T00:15:36.3997937"},{"TradeId":50369968,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.08888000,"Amount":31.59647172,"Total":2.80829441,"Fee":0.00561659,"TimeStamp":"2018-02-25T23:46:23.2563197"},{"TradeId":49361437,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.08800000,"Amount":31.79020612,"Total":2.79753814,"Fee":0.00559508,"TimeStamp":"2018-02-18T03:27:38.8055988"},{"TradeId":49349429,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.08888000,"Amount":0.02478421,"Total":0.00220282,"Fee":0.00000441,"TimeStamp":"2018-02-18T01:27:43.8502364"},{"TradeId":49349423,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.08888000,"Amount":12.39210269,"Total":1.10141009,"Fee":0.00220282,"TimeStamp":"2018-02-18T01:27:35.5975813"},{"TradeId":49346076,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.08800000,"Amount":12.34095445,"Total":1.08600399,"Fee":0.00217201,"TimeStamp":"2018-02-18T01:00:15.7910346"},{"TradeId":49342696,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.08730019,"Amount":19.93420935,"Total":1.74026026,"Fee":0.00348052,"TimeStamp":"2018-02-18T00:34:11.0375755"},{"TradeId":49097531,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07875000,"Amount":13.42091025,"Total":1.05689668,"Fee":0.00211379,"TimeStamp":"2018-02-16T12:33:28.3850427"},{"TradeId":48999851,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07500000,"Amount":13.41897739,"Total":1.00642330,"Fee":0.00201285,"TimeStamp":"2018-02-15T19:04:34.8659741"},{"TradeId":48998410,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07461113,"Amount":13.61220597,"Total":1.01562207,"Fee":0.00203124,"TimeStamp":"2018-02-15T18:54:40.584892"},{"TradeId":48997866,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07540000,"Amount":13.61104095,"Total":1.02627249,"Fee":0.00205254,"TimeStamp":"2018-02-15T18:49:37.3080918"},{"TradeId":48997231,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07560000,"Amount":13.72756473,"Total":1.03780389,"Fee":0.00207561,"TimeStamp":"2018-02-15T18:44:33.0519796"},{"TradeId":48996906,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07594808,"Amount":13.72737900,"Total":1.04256808,"Fee":0.00208514,"TimeStamp":"2018-02-15T18:40:46.749131"},{"TradeId":48993647,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07599996,"Amount":13.74594903,"Total":1.04469158,"Fee":0.00208938,"TimeStamp":"2018-02-15T18:19:35.4647521"},{"TradeId":48993006,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07580002,"Amount":13.74633873,"Total":1.04197275,"Fee":0.00208395,"TimeStamp":"2018-02-15T18:14:34.8652017"},{"TradeId":48992422,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07580000,"Amount":13.70744248,"Total":1.03902414,"Fee":0.00207805,"TimeStamp":"2018-02-15T18:09:38.8305244"},{"TradeId":48991733,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07528000,"Amount":13.70584221,"Total":1.03177580,"Fee":0.00206355,"TimeStamp":"2018-02-15T18:04:34.8855769"},{"TradeId":48990817,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07465000,"Amount":13.86584496,"Total":1.03508533,"Fee":0.00207017,"TimeStamp":"2018-02-15T17:59:35.3623883"},{"TradeId":48990126,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07525100,"Amount":13.47928369,"Total":1.01432958,"Fee":0.00202866,"TimeStamp":"2018-02-15T17:54:35.9134223"},{"TradeId":48990125,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07469000,"Amount":0.38574725,"Total":0.02881146,"Fee":0.00005762,"TimeStamp":"2018-02-15T17:54:35.9134223"},{"TradeId":48989717,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07515100,"Amount":13.94641314,"Total":1.04808689,"Fee":0.00209617,"TimeStamp":"2018-02-15T17:49:31.6790319"},{"TradeId":48989290,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07500000,"Amount":13.94380026,"Total":1.04578502,"Fee":0.00209157,"TimeStamp":"2018-02-15T17:44:36.6692913"},{"TradeId":48988408,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07455660,"Amount":14.20510014,"Total":1.05908397,"Fee":0.00211817,"TimeStamp":"2018-02-15T17:39:35.0165095"},{"TradeId":48987792,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07455000,"Amount":14.20538451,"Total":1.05901142,"Fee":0.00211802,"TimeStamp":"2018-02-15T17:35:11.1200059"},{"TradeId":48986987,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07500000,"Amount":14.17697622,"Total":1.06327322,"Fee":0.00212655,"TimeStamp":"2018-02-15T17:29:33.1738651"},{"TradeId":48986418,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07455000,"Amount":14.17695935,"Total":1.05689232,"Fee":0.00211378,"TimeStamp":"2018-02-15T17:25:08.7506064"},{"TradeId":48985803,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07484000,"Amount":14.17861764,"Total":1.06112774,"Fee":0.00212226,"TimeStamp":"2018-02-15T17:19:34.4897858"},{"TradeId":48985329,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07455000,"Amount":14.17795799,"Total":1.05696677,"Fee":0.00211393,"TimeStamp":"2018-02-15T17:15:05.4683809"},{"TradeId":48984946,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07443501,"Amount":0.14997217,"Total":0.01116318,"Fee":0.00002233,"TimeStamp":"2018-02-15T17:11:48.1795766"},{"TradeId":48984653,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07450000,"Amount":14.09395973,"Total":1.05000000,"Fee":0.00210000,"TimeStamp":"2018-02-15T17:09:31.5262499"},{"TradeId":48984272,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07455000,"Amount":14.24383980,"Total":1.06187826,"Fee":0.00212376,"TimeStamp":"2018-02-15T17:05:51.414538"},{"TradeId":48983233,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07480000,"Amount":14.25306168,"Total":1.06612901,"Fee":0.00213226,"TimeStamp":"2018-02-15T16:59:37.2783972"},{"TradeId":48982510,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07455000,"Amount":14.25249549,"Total":1.06252354,"Fee":0.00212505,"TimeStamp":"2018-02-15T16:54:41.791466"},{"TradeId":48981933,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07443501,"Amount":14.30908083,"Total":1.06509657,"Fee":0.00213019,"TimeStamp":"2018-02-15T16:50:11.5576284"},{"TradeId":48978884,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07443501,"Amount":14.30829139,"Total":1.06503781,"Fee":0.00213008,"TimeStamp":"2018-02-15T16:24:33.2625954"},{"TradeId":48978564,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07443501,"Amount":14.38719579,"Total":1.07091106,"Fee":0.00214182,"TimeStamp":"2018-02-15T16:21:26.225376"},{"TradeId":48977991,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07455000,"Amount":14.38640113,"Total":1.07250620,"Fee":0.00214501,"TimeStamp":"2018-02-15T16:15:32.6868405"},{"TradeId":48977313,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07443501,"Amount":14.46591663,"Total":1.07677065,"Fee":0.00215354,"TimeStamp":"2018-02-15T16:10:05.6352555"},{"TradeId":48966709,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07443450,"Amount":14.46532918,"Total":1.07671954,"Fee":0.00215344,"TimeStamp":"2018-02-15T14:39:01.984284"},{"TradeId":48966051,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07443501,"Amount":14.52411873,"Total":1.08110292,"Fee":0.00216221,"TimeStamp":"2018-02-15T14:32:35.2790719"},{"TradeId":48959884,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07355250,"Amount":14.52525604,"Total":1.06836889,"Fee":0.00213674,"TimeStamp":"2018-02-15T13:40:23.1322455"},{"TradeId":48959293,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07443501,"Amount":14.41147257,"Total":1.07271810,"Fee":0.00214544,"TimeStamp":"2018-02-15T13:34:44.9063125"},{"TradeId":48959186,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07355250,"Amount":14.41259504,"Total":1.06008240,"Fee":0.00212016,"TimeStamp":"2018-02-15T13:33:54.2448426"},{"TradeId":48955796,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07443501,"Amount":14.30037032,"Total":1.06444821,"Fee":0.00212890,"TimeStamp":"2018-02-15T13:01:50.0102782"},{"TradeId":48945865,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07314300,"Amount":14.29618039,"Total":1.04566552,"Fee":0.00209133,"TimeStamp":"2018-02-15T11:26:17.2358135"},{"TradeId":48789497,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07134934,"Amount":14.71513923,"Total":1.04991547,"Fee":0.00209983,"TimeStamp":"2018-02-14T14:34:31.9678776"},{"TradeId":48789006,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07057167,"Amount":14.71450798,"Total":1.03842740,"Fee":0.00207685,"TimeStamp":"2018-02-14T14:31:31.3490132"},{"TradeId":48787786,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07054936,"Amount":14.77764684,"Total":1.04255353,"Fee":0.00208511,"TimeStamp":"2018-02-14T14:24:37.4354237"},{"TradeId":48787699,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07057167,"Amount":14.77724951,"Total":1.04285518,"Fee":0.00208571,"TimeStamp":"2018-02-14T14:23:49.6814558"},{"TradeId":48766821,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07066506,"Amount":14.81693301,"Total":1.04703946,"Fee":0.00209408,"TimeStamp":"2018-02-14T12:19:32.4393211"},{"TradeId":48736579,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07035010,"Amount":14.81560723,"Total":1.04227945,"Fee":0.00208456,"TimeStamp":"2018-02-14T08:29:27.6363326"},{"TradeId":48735553,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07000001,"Amount":14.94830304,"Total":1.04638136,"Fee":0.00209276,"TimeStamp":"2018-02-14T08:19:32.2739604"},{"TradeId":48735450,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07035010,"Amount":14.94772664,"Total":1.05157406,"Fee":0.00210315,"TimeStamp":"2018-02-14T08:18:25.4283383"},{"TradeId":48733414,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07012000,"Amount":15.00531516,"Total":1.05217270,"Fee":0.00210435,"TimeStamp":"2018-02-14T07:59:37.070097"},{"TradeId":48732876,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07011222,"Amount":15.00472157,"Total":1.05201434,"Fee":0.00210403,"TimeStamp":"2018-02-14T07:54:33.4011328"},{"TradeId":48732206,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07000000,"Amount":15.06409245,"Total":1.05448647,"Fee":0.00210897,"TimeStamp":"2018-02-14T07:49:32.4776198"},{"TradeId":48731678,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06999999,"Amount":15.06282681,"Total":1.05439773,"Fee":0.00210880,"TimeStamp":"2018-02-14T07:44:33.3090397"},{"TradeId":48731131,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06980000,"Amount":15.18938091,"Total":1.06021879,"Fee":0.00212044,"TimeStamp":"2018-02-14T07:39:32.6902947"},{"TradeId":48730592,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07011222,"Amount":15.18910194,"Total":1.06494166,"Fee":0.00212988,"TimeStamp":"2018-02-14T07:34:32.4734083"},{"TradeId":48730109,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07005000,"Amount":15.21694746,"Total":1.06594717,"Fee":0.00213189,"TimeStamp":"2018-02-14T07:29:32.0244356"},{"TradeId":48729612,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06990000,"Amount":15.21501499,"Total":1.06352955,"Fee":0.00212706,"TimeStamp":"2018-02-14T07:24:31.9538378"},{"TradeId":48729090,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06950011,"Amount":1.01984406,"Total":0.07087927,"Fee":0.00014176,"TimeStamp":"2018-02-14T07:19:31.9468865"},{"TradeId":48729089,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06950011,"Amount":14.38846644,"Total":1.00000000,"Fee":0.00200000,"TimeStamp":"2018-02-14T07:19:31.9468865"},{"TradeId":48728635,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07011222,"Amount":15.40615718,"Total":1.08015988,"Fee":0.00216032,"TimeStamp":"2018-02-14T07:14:31.9129051"},{"TradeId":48728114,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06950006,"Amount":15.62145156,"Total":1.08569182,"Fee":0.00217138,"TimeStamp":"2018-02-14T07:09:32.2136529"},{"TradeId":48727626,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07020000,"Amount":15.62083135,"Total":1.09658236,"Fee":0.00219316,"TimeStamp":"2018-02-14T07:04:32.0531809"},{"TradeId":48727115,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07030000,"Amount":15.68290284,"Total":1.10250807,"Fee":0.00220502,"TimeStamp":"2018-02-14T06:59:32.0657042"},{"TradeId":48726540,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07030000,"Amount":15.68166666,"Total":1.10242117,"Fee":0.00220484,"TimeStamp":"2018-02-14T06:54:32.5690028"},{"TradeId":48726025,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07007550,"Amount":15.80520546,"Total":1.10755768,"Fee":0.00221512,"TimeStamp":"2018-02-14T06:49:32.155468"},{"TradeId":48725983,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07035010,"Amount":15.80560716,"Total":1.11192604,"Fee":0.00222385,"TimeStamp":"2018-02-14T06:49:01.7178845"},{"TradeId":48723801,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07007549,"Amount":1.37562142,"Total":0.09639735,"Fee":0.00019279,"TimeStamp":"2018-02-14T06:24:36.5120703"},{"TradeId":48723800,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07049848,"Amount":14.38991771,"Total":1.01446733,"Fee":0.00202893,"TimeStamp":"2018-02-14T06:24:36.5120703"},{"TradeId":48723239,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07000000,"Amount":15.76478713,"Total":1.10353510,"Fee":0.00220707,"TimeStamp":"2018-02-14T06:19:31.9489343"},{"TradeId":48722734,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07000000,"Amount":15.83996634,"Total":1.10879764,"Fee":0.00221760,"TimeStamp":"2018-02-14T06:14:33.4407162"},{"TradeId":48722133,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07005712,"Amount":15.83946834,"Total":1.10966753,"Fee":0.00221934,"TimeStamp":"2018-02-14T06:09:33.3044252"},{"TradeId":48721674,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.07005713,"Amount":15.88935051,"Total":1.11316229,"Fee":0.00222632,"TimeStamp":"2018-02-14T06:04:31.8457938"},{"TradeId":48721043,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07000000,"Amount":15.88758916,"Total":1.11213124,"Fee":0.00222426,"TimeStamp":"2018-02-14T05:59:33.7037352"},{"TradeId":48720409,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06950005,"Amount":16.06370040,"Total":1.11642798,"Fee":0.00223286,"TimeStamp":"2018-02-14T05:54:32.5103447"},{"TradeId":48719795,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.07000000,"Amount":16.06180639,"Total":1.12432645,"Fee":0.00224865,"TimeStamp":"2018-02-14T05:49:32.5115148"},{"TradeId":48719222,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06930036,"Amount":16.25117967,"Total":1.12621260,"Fee":0.00225243,"TimeStamp":"2018-02-14T05:44:33.1719404"},{"TradeId":48718670,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06985000,"Amount":16.24926300,"Total":1.13501102,"Fee":0.00227002,"TimeStamp":"2018-02-14T05:39:33.5836867"},{"TradeId":48718203,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06930011,"Amount":16.44091119,"Total":1.13935695,"Fee":0.00227871,"TimeStamp":"2018-02-14T05:34:33.5062872"},{"TradeId":48717773,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06985000,"Amount":16.43861517,"Total":1.14823727,"Fee":0.00229647,"TimeStamp":"2018-02-14T05:29:33.9117773"},{"TradeId":48717309,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06930010,"Amount":16.66814292,"Total":1.15510397,"Fee":0.00231021,"TimeStamp":"2018-02-14T05:24:35.1188702"},{"TradeId":48716797,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06999996,"Amount":16.66664975,"Total":1.16666482,"Fee":0.00233333,"TimeStamp":"2018-02-14T05:19:32.425369"},{"TradeId":48716401,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06930000,"Amount":16.81597368,"Total":1.16534698,"Fee":0.00233069,"TimeStamp":"2018-02-14T05:14:36.6893178"},{"TradeId":48715937,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06965300,"Amount":14.71567410,"Total":1.02499085,"Fee":0.00204998,"TimeStamp":"2018-02-14T05:09:32.2217202"},{"TradeId":48715936,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06965288,"Amount":2.09879432,"Total":0.14618707,"Fee":0.00029237,"TimeStamp":"2018-02-14T05:09:32.2217202"},{"TradeId":48715610,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06930000,"Amount":16.96507362,"Total":1.17567960,"Fee":0.00235136,"TimeStamp":"2018-02-14T05:04:32.4697849"},{"TradeId":48715214,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06965288,"Amount":16.96440781,"Total":1.18161986,"Fee":0.00236324,"TimeStamp":"2018-02-14T04:59:32.2648578"},{"TradeId":48714651,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06929999,"Amount":17.03088783,"Total":1.18024036,"Fee":0.00236048,"TimeStamp":"2018-02-14T04:54:33.6727259"},{"TradeId":48714209,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06929999,"Amount":17.02900610,"Total":1.18010995,"Fee":0.00236022,"TimeStamp":"2018-02-14T04:49:33.0111604"},{"TradeId":48713702,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06910100,"Amount":17.21714346,"Total":1.18972183,"Fee":0.00237944,"TimeStamp":"2018-02-14T04:44:31.9356379"},{"TradeId":48713004,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06960000,"Amount":17.21573516,"Total":1.19821517,"Fee":0.00239643,"TimeStamp":"2018-02-14T04:39:32.3999526"},{"TradeId":48712414,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06910000,"Amount":17.35670970,"Total":1.19934864,"Fee":0.00239870,"TimeStamp":"2018-02-14T04:34:40.9905738"},{"TradeId":48711816,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06939997,"Amount":17.35454040,"Total":1.20440458,"Fee":0.00240881,"TimeStamp":"2018-02-14T04:29:32.0700934"},{"TradeId":48711402,"TradePairId":5760,"Market":"ETN/USDT","Type":"Sell","Rate":0.06900000,"Amount":17.57142090,"Total":1.21242804,"Fee":0.00242486,"TimeStamp":"2018-02-14T04:24:32.3498498"},{"TradeId":48711002,"TradePairId":5760,"Market":"ETN/USDT","Type":"Buy","Rate":0.06959969,"Amount":17.56867983,"Total":1.22277467,"Fee":0.00244555,"TimeStamp":"2018-02-14T04:19:33.8187313"}]}
        addMockEndpoint("/GetTradeHistory", "getTradeHistoryResponse.json");
        List<TradeHistory> history = cryptopia.getTradeHistory(TRADEPAIR_ID);
        Assert.assertFalse(history.isEmpty());
    }

    @Test
    public void testGetTransactionHistoryMarketCount() {
        addMockEndpoint("/GetTransactions", "getTransactionsResponse.json");
        List<Transaction> transactions = cryptopia.getTransactions("Deposit", COUNT);
        Assert.assertFalse(transactions.isEmpty());

    }

    @Test
    public void testGetTransactionHistoryMarket() {
        addMockEndpoint("/GetTransactions", "getTransactionsResponse.json");
        List<Transaction> transactions = cryptopia.getTransactions("Withdraw");
        Assert.assertFalse(transactions.isEmpty());
    }

    /*
     * the consciences of a wrong call can be big here thats whey the params are listed here
     * @param tradePairId The Cryptopia tradepair identifier of trade e.g. '100'
     * @param type the type of trade e.g. 'Buy' or 'Sell'
     * @param rate the rate or price to pay for the coins e.g. 0.00000034
     * @param amount the amount of coins to buy e.g. 123.00000000
     * @return an tradeResponse
     */
    @Test
    public void testSubmitTradeTradePairId() {
        addMockEndpoint("/SubmitTrade", "submitTradeResponse.json");
        BigDecimal rate = BigDecimal.valueOf(10000.0);
        BigDecimal amount = BigDecimal.valueOf(1);

        Optional<SubmitTrade> trade = cryptopia.submitTrade(MARKET, "Sell", rate, amount);
        Assert.assertTrue(trade.isPresent());
    }

    /*
     * the consciences of a wrong call can be big here thats whey the params are listed here
     * @param market The market symbol of the trade e.g. 'DOT/BTC'
     * @param type the type of trade e.g. 'Buy' or 'Sell'
     * @param rate the rate or price to pay for the coins e.g. 0.00000034
     * @param amount the amount of coins to buy e.g. 123.00000000
     * @return an tradeResponse
     */
    //@Test(enabled = false)
    @Test
    public void testSubmitTradeMarket() {
        addMockEndpoint("/SubmitTrade", "submitTradeResponse.json");
        BigDecimal rate = BigDecimal.valueOf(1000.0);
        BigDecimal amount = BigDecimal.valueOf(1);
        Optional<SubmitTrade> trade = cryptopia.submitTrade(TRADEPAIR_ID, "Sell", rate, amount);
        Assert.assertTrue(trade.isPresent());
    }


    @Test
    public void testCancelAllTrades() {
        addMockEndpoint("/CancelTrade", "cancelTradeResponse.json");
        List<Long> cancel = cryptopia.cancelAllTrades();
        Assert.assertFalse(cancel.isEmpty());
    }


    @Test
    public void testcancelTradeByOrderId() {
        addMockEndpoint("/CancelTrade", "cancelTradeResponse.json");
        /* this value should be changed before the test to a valid tradeOrderId */
        BigInteger orderId = BigInteger.valueOf(285045097);
        List<Long> cancel = cryptopia.cancelTradesByOrderId(orderId);
        Assert.assertFalse(cancel.isEmpty());
    }

    @Test
    public void testCancelTradeByTradePairId() {
        addMockEndpoint("/CancelTrade", "cancelTradeResponse.json");
        List<Long> cancel = cryptopia.cancelTradesByTradePairId(TRADEPAIR_ID);
        Assert.assertFalse(cancel.isEmpty());
    }

    private void addMockEndpoint(String endpoint, String resouceName){
        String response = getResourceAsString("private/" + resouceName);
        wireMockServer.stubFor(post(urlEqualTo(endpoint))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }




}
