var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {    //客户端连接
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/price', function (message) {  //订阅价格
            showPrice(JSON.parse(message.body).content);
        });
        stompClient.subscribe('/topic/margin', function (message) {  //订阅价差
            showMargin(JSON.parse(message.body).content);
        });
        stompClient.subscribe('/user/queue/assets', function (message) {    //订阅用户资产
            showAssets(JSON.parse(message.body).content);
        });
        stompClient.subscribe('/user/queue/log', function (message) {    //订阅用户日志
            showLogs(JSON.parse(message.body).content);
        });
        stompClient.subscribe('/user/queue/setting', function (message) {    //订阅用户日志
            showSettings(JSON.parse(message.body).content);
        });
    });
}

//显示价格
function showPrice(message) {
    var obj = JSON.parse(message);
    $("#okcoin-btc-buy-price").text(obj.data['P0000'].price);
    $("#okcoin-btc-sell-price").text(obj.data['P0001'].price);
    $("#okcoin-btc-last-price").text(obj.data['P0002'].price);
    var okcoinDate = new Date();
    okcoinDate.setTime(obj.data['P0001'].timestamp);
    $("#okcoin-btc-time-price").text(okcoinDate.toLocaleTimeString());

    $("#okcoin-ltc-buy-price").text(obj.data['P0010'].price);
    $("#okcoin-ltc-sell-price").text(obj.data['P0011'].price);
    $("#okcoin-ltc-last-price").text(obj.data['P0012'].price);

    $("#bitvc-btc-buy-price").text(obj.data['P1000'].price);
    $("#bitvc-btc-sell-price").text(obj.data['P1001'].price);
    $("#bitvc-btc-last-price").text(obj.data['P1002'].price);
    var bitvcDate = new Date();
    bitvcDate.setTime(obj.data['P1000'].timestamp);
    $("#bitvc-btc-time-price").text(bitvcDate.toLocaleTimeString());

    $("#bitvc-ltc-buy-price").text(obj.data['P1010'].price);
    $("#bitvc-ltc-sell-price").text(obj.data['P1011'].price);
    $("#bitvc-ltc-last-price").text(obj.data['P1012'].price);
    $("#bitvc-ltc-time-price").text(obj.data['P1010'].timestamp);

}

//显示价差
function showMargin(message) {
    var obj = JSON.parse(message);

    $("#margin-btc-okcoin-bitvc").text(obj.data['M00100'].margin);
    $("#margin-ltc-okcoin-bitvc").text(obj.data['M00101'].margin);
    $("#margin-btc-bitvc-okcoin").text(obj.data['M10000'].margin);
    $("#margin-ltc-bitvc-okcoin").text(obj.data['M10001'].margin);
    $("#time-btc-okcoin-bitvc").text(obj.data['M00100'].deltaTime);
    $("#time-ltc-okcoin-bitvc").text(obj.data['M00101'].deltaTime);
    $("#time-btc-bitvc-okcoin").text(obj.data['M10000'].deltaTime);
    $("#time-ltc-bitvc-okcoin").text(obj.data['M10001'].deltaTime);
    if (obj.data['M00100'].margin > 0) {

    }
}

//显示资产
function showAssets(message) {
    var obj = JSON.parse(message);
    //OkcoinCN
    if (obj.p00 != null) {
        var result = obj.p00;
        if (result.result.info.funds != null) {
            $("#assets-okcoin-btc-free").text(result.result.info.funds.free.btc);
            $("#assets-okcoin-ltc-free").text(result.result.info.funds.free.ltc);
            $("#assets-okcoin-cny-free").text(result.result.info.funds.free.cny);
            if (result.result.info.funds.borrow != null) {
                $("#assets-okcoin-btc-borrow").text(result.result.info.funds.borrow.btc);
                $("#assets-okcoin-ltc-borrow").text(result.result.info.funds.borrow.ltc);
                $("#assets-okcoin-cny-borrow").text(result.result.info.funds.borrow.cny);
            }
            $("#assets-okcoin-btc-freezed").text(result.result.info.funds.freezed.btc);
            $("#assets-okcoin-ltc-freezed").text(result.result.info.funds.freezed.ltc);
            $("#assets-okcoin-cny-freezed").text(result.result.info.funds.freezed.cny);
            $("#assets-okcoin-net-asset").text(result.result.info.funds.asset.net);
            $("#assets-okcoin-total-asset").text(result.result.info.funds.asset.total);
        }
    }
    //BitvcCN
    if (obj.p10 != null) {
        var result = obj.p10;
        $("#assets-bitvc-btc-free").text(result.result.available_btc);
        $("#assets-bitvc-ltc-free").text(result.result.available_ltc);
        $("#assets-bitvc-cny-free").text(result.result.available_cny);
        // $("#assets-bitvc-usd-free").text(result.result.borrow_usd);
        $("#assets-bitvc-btc-borrow").text(result.result.borrow_btc);
        $("#assets-bitvc-ltc-borrow").text(result.result.borrow_ltc);
        $("#assets-bitvc-cny-borrow").text(result.result.borrow_cny);
        $("#assets-bitvc-btc-freezed").text(result.result.frozen_btc);
        $("#assets-bitvc-ltc-freezed").text(result.result.frozen_ltc);
        $("#assets-bitvc-cny-freezed").text(result.result.frozen_cny);
        // $("#assets-bitvc-usd-freezed").text(result.result.frozen_usd);
        $("#assets-bitvc-net-asset").text(result.result.net_asset);
        $("#assets-bitvc-total-asset").text(result.result.total);
    }

    //OkcoinUN
    if (obj.p01 != null) {
        var result = obj.p01;
        if (result.result.info.funds != null) {
            $("#assets-okcoin-un-btc-free").text(result.result.info.funds.free.btc);
            $("#assets-okcoin-un-ltc-free").text(result.result.info.funds.free.ltc);
            $("#assets-okcoin-un-usd-free").text(result.result.info.funds.free.usd);
            if (result.result.info.funds.borrow != null) {
                $("#assets-okcoin-un-btc-borrow").text(result.result.info.funds.borrow.btc);
                $("#assets-okcoin-un-ltc-borrow").text(result.result.info.funds.borrow.ltc);
                $("#assets-okcoin-un-usd-borrow").text(result.result.info.funds.borrow.usd);
            }
            $("#assets-okcoin-un-btc-freezed").text(result.result.info.funds.freezed.btc);
            $("#assets-okcoin-un-ltc-freezed").text(result.result.info.funds.freezed.ltc);
            $("#assets-okcoin-un-usd-freezed").text(result.result.info.funds.freezed.usd);
            $("#assets-okcoin-un-net-asset").text(result.result.info.funds.asset.net);
            $("#assets-okcoin-un-total-asset").text(result.result.info.funds.asset.total);
        }
    }

    //OkcoinFuture
    if (obj.p02 != null) {
        var result = obj.p02;
        if (result.result.info != null) {
            $("#assets-okcoin-future-btc-accountRights").text((result.result.info.btc.account_rights).toFixed(2));
            $("#assets-okcoin-future-btc-keepDeposit").text((result.result.info.btc.keep_deposit).toFixed(2));
            $("#assets-okcoin-future-btc-riskRate").text((result.result.info.btc.risk_rate*100).toFixed(0)+'%');
            $("#assets-okcoin-future-btc-profitReal").text((result.result.info.btc.profit_real).toFixed(2));
            $("#assets-okcoin-future-btc-profitUnreal").text((result.result.info.btc.profit_unreal).toFixed(2));

            $("#assets-okcoin-future-ltc-accountRights").text((result.result.info.ltc.account_rights).toFixed(2));
            $("#assets-okcoin-future-ltc-keepDeposit").text((result.result.info.ltc.keep_deposit).toFixed(2));
            $("#assets-okcoin-future-ltc-riskRate").text((result.result.info.ltc.risk_rate*100).toFixed(0)+'%');
            $("#assets-okcoin-future-ltc-profitReal").text((result.result.info.ltc.profit_real).toFixed(2));
            $("#assets-okcoin-future-ltc-profitUnreal").text((result.result.info.ltc.profit_unreal).toFixed(2));
        }
    }
    var spotAssets = $("#assets-okcoin-net-asset").text()*1.0+$("#assets-bitvc-net-asset").text()*1.0+($("#assets-okcoin-un-net-asset").text()*6.9);
    var futureBtcAssets =($("#assets-okcoin-future-btc-accountRights").text()*1.0)*($("#okcoin-btc-last-price").text()*1.0);
    var futureLtcAssets =($("#assets-okcoin-future-ltc-accountRights").text()*1.0)*($("#okcoin-ltc-last-price").text()*1.0);
    var totalAssets =spotAssets*1.0+futureBtcAssets*1.0+futureLtcAssets*1.0;
    $("#assets-net-total").text((totalAssets).toFixed(2));
}


//显示日志
function showLogs(message) {
    var obj = JSON.parse(message);
    var thresholdList = obj.thresholdList;
    var analyseList = obj.analyseList;
    var tradeList = obj.tradeList;
    if (thresholdList != null)
        showTanalyseList(thresholdList);
    if (analyseList != null)
        showAnalyseList(analyseList);
    if (tradeList != null)
        showTradeList(tradeList);
}
//显示价差触发日志
function showTanalyseList(result) {
    var str = '<tr><th>时间</th><th>币种</th><th>做空平台</th><th>做多平台</th><th>价差</th></tr>';
    for (var i = 0; i < result.length; i++) {
        str = str + '<tr>';
        var one = result[i];
        var createTimestamp = new Date(one.createTimestamp);
        var month = createTimestamp.getMonth() + 1;
        var date = createTimestamp.getDate();
        var hour = createTimestamp.getHours();
        var minute = createTimestamp.getMinutes();
        var second = createTimestamp.getSeconds();
        var showDatetime = month + "." + date + " " + hour + ":" + minute + ":" + second;

        var context = one.context;
        var contextJson = JSON.parse(context)
        var margin = contextJson.margin;
        var buyPlatform = contextJson.buyPlatform;
        if (buyPlatform == "00") {
            buyPlatform = "Okcoin";
        } else if (buyPlatform == "10") {
            buyPlatform = "Bitvc";
        }
        var sellPlatform = contextJson.sellPlatform;
        if (sellPlatform == "00") {
            sellPlatform = "Okcoin";
        } else if (sellPlatform == "10") {
            sellPlatform = "Bitvc";
        }
        var coin = contextJson.coin;
        if (coin == '0') {
            coin = 'BTC';
        } else {
            coin = 'LTC';
        }
        str = str + '<td>';
        str = str + showDatetime + '</td><td>';
        str = str + coin + '</td><td>';
        str = str + buyPlatform + '</td><td>';
        str = str + sellPlatform + '</td><td>';
        str = str + margin + '</td>';
    }
    str = str + '</tr>';
    $("#threshold-log").html(str);
}
//显示交易分析日志
function showAnalyseList(result) {
    var str = '<tr><th>时间</th><th>日志</th></tr>';
    for (var i = 0; i < result.length; i++) {
        str = str + '<tr>';
        var one = result[i];
        var createTimestamp = one.createTimestamp;
        var context = one.context;
        str = str + '<td>';
        str = str + createTimestamp + '</td><td>';
        str = str + context + '</td>';
    }
    str = str + '</tr>';
    $("#trade-log").html(str);
}
//显示交易日志
function showTradeList(result) {
    var str = '<tr><th>时间</th><th>日志</th></tr>';
    for (var i = 0; i < result.length; i++) {
        str = str + '<tr>';
        var one = result[i];
        var createTimestamp = one.createTimestamp;
        var context = one.context;
        str = str + '<td>';
        str = str + createTimestamp + '</td><td>';
        str = str + context + '</td>';
    }
    str = str + '</tr>';
    $("#trade-success-log").html(str);
}

//显示用户的设置
function showSettings(result) {
    result = JSON.parse(result);
    if (result != null && result.marginJson != null) {
        var marginJson = result.marginJson;
        marginJson = JSON.parse(marginJson);
        if (marginJson.S00100 != null)
            $("#threshold-btc-okcoin-bitvc").text(marginJson.S00100);
        if (marginJson.S10000 != null)
            $("#threshold-btc-bitvc-okcoin").text(marginJson.S10000);
        if (marginJson.S00101 != null)
            $("#threshold-ltc-okcoin-bitvc").text(marginJson.S00101);
        if (marginJson.S10001 != null)
            $("#threshold-ltc-bitvc-okcoin").text(marginJson.S10001);
        if (result.eachAmountBtc != null)
            $("#each-amount-btc").text(result.eachAmountBtc);
        if (result.eachAmountLtc != null)
            $("#each-amount-ltc").text(result.eachAmountLtc);
    }
    if (result.autoTradeBtc == "1") {
        $("#btc-trade").removeClass('btn-danger').addClass('btn-success').val("1").text("自动交易");
    } else if (result.autoTradeBtc == "0") {
        $("#btc-trade").removeClass('btn-success').addClass('btn-danger').val("0").text("手动交易");
    }
    if (result.autoTradeLtc == "1") {
        $("#ltc-trade").removeClass('btn-danger').addClass('btn-success').val("1").text("自动交易");
    } else if (result.autoTradeLtc == "0") {
        $("#ltc-trade").removeClass('btn-success').addClass('btn-danger').val("0").text("手动交易");
    }

    if (result.autoThresholdBtc == "1") {
        $("#btc-threshold").removeClass('btn-danger').addClass('btn-success').val("1").text("自动阀值");
    } else if (result.autoThresholdBtc == "0") {
        $("#btc-threshold").removeClass('btn-success').addClass('btn-danger').val("0").text("手动阀值");
    }

    if (result.autoThresholdLtc == "1") {
        $("#ltc-threshold").removeClass('btn-danger').addClass('btn-success').val("1").text("自动阀值");
    } else if (result.autoThresholdLtc == "0") {
        $("#ltc-threshold").removeClass('btn-success').addClass('btn-danger').val("0").text("手动阀值");
    }
}

//初始化用户设置
var setting = {};

setting.init = function () {
    getUserTradeSettingInit();
}
function getUserTradeSettingInit() {
    $.ajax({
        url: '/u/getUserTradeSetting',
        data: {},
        type: 'GET',
        dataType: 'json',
        timeout: 1000,
        error: function () {
            console.log('/u/getUserTradeSetting');
        },
        success: function (result) {
            if (result != null && result.marginJson != null) {
                var marginJson = JSON.parse(result.marginJson);
                if (marginJson.S00100 != null)
                    $("#threshold-btc-okcoin-bitvc").text(marginJson.S00100);
                $("#input-threshold-btc-okcoin-bitvc").val(marginJson.S00100);
                if (marginJson.S10000 != null)
                    $("#threshold-btc-bitvc-okcoin").text(marginJson.S10000);
                $("#input-threshold-btc-bitvc-okcoin").val(marginJson.S10000);
                if (marginJson.S00101 != null)
                    $("#threshold-ltc-okcoin-bitvc").text(marginJson.S00101);
                $("#input-threshold-ltc-okcoin-bitvc").val(marginJson.S00101);
                if (marginJson.S10001 != null)
                    $("#threshold-ltc-bitvc-okcoin").text(marginJson.S10001);
                $("#input-threshold-ltc-bitvc-okcoin").val(marginJson.S10001);
                if (result.eachAmountBtc != null)
                    $("#each-amount-btc").text(result.eachAmountBtc);
                $("#input-each-amount-btc").val(result.eachAmountBtc)
                if (result.eachAmountLtc != null)
                    $("#each-amount-ltc").text(result.eachAmountLtc);
                $("#input-each-amount-ltc").val(result.eachAmountLtc)
            }
            if (result.autoTradeBtc == "1") {
                $("#btc-trade").removeClass('btn-danger').addClass('btn-success').val("1").text("自动交易");
            } else if (result.autoTradeBtc == "0") {
                $("#btc-trade").removeClass('btn-success').addClass('btn-danger').val("0").text("手动交易");
            }
            if (result.autoTradeLtc == "1") {
                $("#ltc-trade").removeClass('btn-danger').addClass('btn-success').val("1").text("自动交易");
            } else if (result.autoTradeLtc == "0") {
                $("#ltc-trade").removeClass('btn-success').addClass('btn-danger').val("0").text("手动交易");
            }

            if (result.autoThresholdBtc == "1") {
                $("#btc-threshold").removeClass('btn-danger').addClass('btn-success').val("1").text("自动阀值");
            } else if (result.autoThresholdBtc == "0") {
                $("#btc-threshold").removeClass('btn-success').addClass('btn-danger').val("0").text("手动阀值");
            }

            if (result.autoThresholdLtc == "1") {
                $("#ltc-threshold").removeClass('btn-danger').addClass('btn-success').val("1").text("自动阀值");
            } else if (result.autoThresholdLtc == "0") {
                $("#ltc-threshold").removeClass('btn-success').addClass('btn-danger').val("0").text("手动阀值");
            }
        }
    });
}

window.onload = setting.init();
window.onload = connect;

