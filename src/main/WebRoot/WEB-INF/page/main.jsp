<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>设备列表</title>
    <%--<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>--%>
    <!-- home部分通用css -->
    <link rel="stylesheet" type="text/css" href="${path}/page/css/main.css">
    <style>
        .row {
            margin: 0;
        }

        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4,
        .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10,
        .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6,
        .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12,
        .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8,
        .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3,
        .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
        }

        /*无数据页面内容*/
        #nodata {
            width: 100%;
            height: auto;
            margin: 120px 0;
            /*padding: 0 100px;*/
            /*text-align: center;*/
            /*background: #00b300;*/
            display: block;
            position: absolute;
        }

        #nodata img {
            width: 150px;
            height: auto;
            margin-left: 50%;
            -webkit-transform: translate(-50%, 0);
            -moz-transform: translate(-50%, 0);
            -ms-transform: translate(-50%, 0);
            -o-transform: translate(-50%, 0);
            transform: translate(-50%, 0);
        }

        #nodata .label-title {
            margin-top: 20px;
            font-size: 16px;
            color: rgba(9, 7, 8, 0.5);
            font-size: 17px;
            /*margin-bottom: 10px;*/
            text-align: center;
            font-weight: 500;
            line-height: 1.1;
            width: auto;
        }

        #nodata .label-title span {
            color: #2BB4EA;
            cursor: pointer;
        }

    </style>

    <script>
        function isExist(divId) {
            if ($(divId).length && $(divId).length > 0) {
                return true;
            }
            return false;
        }
        var customer = {
            "tabCustomerId": '${account.tabCustomerId}',
            "mobelPhone": '${account.mobelPhone}',
            "wxId": '${account.wxId}',
            "nickName": '${account.nickName}',
            "birthday": '${account.birthday}',
            "sex": '${account.sex}',
            "token": '${account.token}'
        };

        $(function () {
            // alert("登录手机号：[" + customer.mobelPhone + "]");
        });

    </script>

    <script>
        $(document).ready(function () {
            // webscoket
            // var ws = null;

            // 网关列表数组
            var deviceGroupArray = new Array();
            var deviceArray = new Array();
            var current_deviceGroup;
            var currentFreshDeviceSeriaNumber = "";
            var currentFreshairDeviceTabId = "";
            var code;
            var t1;  ////定时器

            $("#openAirKiss_btn").click(function () {
                window.location.href = "${path}/deviceGroup/openWifiScan?mobelPhone=" + customer.mobelPhone;
            });

            //获取空气检测设备数据
            var getFreshairData = function () {
                if ("" == $.trim(currentFreshDeviceSeriaNumber)) {
                    return;
                }
                $.ajax({
                    url: "${path}/freshair/freshairNowData/" + currentFreshDeviceSeriaNumber,
                    type: "GET",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            var freshairData = result.obj;
                            //将设备信息添加到空气检测设备列表
                            var wenduval = "";
                            var shiduval = "";
                            var pmval = "";
                            var co2val = "";
                            var vocval = "";
                            if (null == freshairData.wendu) {
                                wenduval += "温度: " + "-- ℃";
                            } else {
                                wenduval += "温度: " + freshairData.wendu + "℃";
                            }
                            $('#device_wendu_info').html(wenduval);
                            if (null == freshairData.shidu) {
                                shiduval += "湿度: " + "-- ％";
                            } else {
                                shiduval += "湿度: " + freshairData.shidu + "％";
                            }
                            $('#device_shidu_info').html(shiduval);
                            if (null == freshairData.pm) {
                                pmval += "PM2.5: " + "-- μg/m³";
                            } else {
                                pmval += "PM2.5: " + freshairData.pm + "μg/m³";
                            }
                            $('#device_pm_info').html(pmval);
                            if (null == freshairData.co2) {
                                co2val += "CO2: " + "-- ppm";
                            } else {
                                co2val += "CO2: " + freshairData.co2 + "ppm";
                            }
                            $('#device_co2_info').html(co2val);

                            if (null == freshairData.voc) {
                                vocval += "VOC: " + "-- μg/m³";
                            } else {
                                vocval += "VOC: " + freshairData.voc + "μg/m³";
                            }
                            $('#device_voc_info').html(vocval);
                        }
                    }
                });
            }

            window.onbeforeunload = function () {
                window.clearInterval(t1);//去掉定时器
            };

            $('.dropDown').mouseleave(function () {
                $('.dropDown').slideUp("slow", function () {
                    $(this).fadeOut(2000);
                });
            });

            // 分组，页面数据重新加载
            function reloadPageContent(deviceGroup) {
                if (!deviceGroup) {
                    return;
                }
                $("#page-content").css("display", "block");
                $("#notify").css("display", "block");

                if (deviceGroup.isSendMsg) {
                    $("#notifyText").html("关闭通知");
                    $("#notify").css("background", "#2BB4EA");
                } else {
                    $("#notifyText").html("开启通知");
                    $("#notify").css("background", "red");
                }

                $("#nodata").css("display", "none");
                $("#groupName").html(deviceGroup.address);
                $("#groupStatus").html("isOn");
                $("#devicelistPanel").empty();
                var deviceFreshairArray = new Array();
                //重新请求分组下所有设备
                $.ajax({
                    url: "${path}/deviceGroup/tabDeviceInGroup/" + deviceGroup.tabDeviceGroupId,
                    type: "GET",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            //获取分组信息
                            var tabDeviceGroupinfo = result.obj.tabDeviceGroupinfo;
                            //获取空气检测设备信息
                            var groupFreshairList = result.obj.groupFreshairList;
                            //如果用户有空气检测设备
                            if ((null != groupFreshairList) && (0 < groupFreshairList.length)) {
                                var freshairList = new Array();
                                for (var i in groupFreshairList) {
                                    deviceFreshairArray.push(groupFreshairList[i]);
                                    //加载第一个空气检测设备
                                }

                                currentFreshDeviceSeriaNumber = groupFreshairList[0].deviceSeriaNumber;
                                currentFreshairDeviceTabId = groupFreshairList[0].tabDeviceFreshairId;
                                getFreshairData();
                            }
                        }
                        if (!isExist("#btnadddevice")) {
                            var btnadddevice = '<div id="btnadddevice" class="list-content">' +
                                '<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">' +
                                '<div id="btn-adddevice" class="btn-adddevice">' + '' +
                                '<button >添加设备' +
                                '</button>' +
                                '</div>' +
                                '</div><!--list-content col-xs-12 -->' +
                                '</div><!--list-content -->';
                            $("#devicelistPanel").append(btnadddevice);
                            $("#btn-adddevice").click(function () {
                                addDevice(true, null);
                            });
                        }
                    },
                    error: function (msg) {
                        layer.msg(msg);
                    }
                });

            }

            // 数据请求
            function refresh() {
                //  依据客户表ID 加载客户分组
                $.ajax({
                    url: "${path}/deviceGroup/allCustomerGroups/"+customer.tabCustomerId,
                    type: "GET",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            var groupList = result.obj;
                            deviceGroupArray = new Array();
                            for (var i in groupList) {
                                var item = groupList[i];
                                var groupItem = {
                                    "tabDeviceGroupId": item.tabDeviceGroupId,
                                    "groupName": item.groupName,
                                    "isSendMsg": item.isSendMsg
                                };
                                if ($.inArray(groupItem, deviceGroupArray) == -1) {
                                    deviceGroupArray.push(groupItem);
                                    $("#leftM").prepend('<li id="tabDeviceGroupId_' + groupItem.tabDeviceGroupId + '">' +
                                        '<a href="#">' + groupItem.groupName + '</a></li>');
                                    $('#tabDeviceGroupId_' + groupItem.tabDeviceGroupId).click(function () {
                                        $("#devicelistPanel").empty();
                                        var tabDeviceGroupId = $(this).attr("id").split("_")[1];
                                        $('#leftM').slideUp("slow");
                                        for (var i in deviceGroupArray) {
                                            if (deviceGroupArray[i].tabDeviceGroupId == tabDeviceGroupId) {
                                                current_deviceGroup = deviceGroupArray[i];
                                                reloadPageContent(current_deviceGroup);
                                            }
                                        }
                                    });
                                }
                            }
                            if (deviceGroupArray.length > 0) {
                                current_deviceGroup = deviceGroupArray[0];
                                reloadPageContent(current_deviceGroup);
                            }
                            if (!isExist("#gateWayId_nomore")) {
                                $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');
                            }
                        } else {
                            layer.alert(result.errorMsg);
                            $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');
                        }
                    },
                    error: function (msg) {
                        layer.msg(msg);
                        $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');
                    }
                });

            }

            /**
             * 请求页面数据
             */
            refresh();
            //加载定时器
            getTimer();

            /**
             * 定时器方法
             */
            function getTimer() {
                t1 = window.setInterval(getFreshairData, 10000);//使用字符串执行方法
            }

            //更新网关信息，留用
            function updateGateway(deviceGroup) {
                var dialog = '<div class="box">' +
                    '<form >' +
                    '<div class="form-group">' +
                    '<label for="name">网关名称</label>' +
                    '<input type="text" class="form-control" id="update_gatewayIP" ' +
                    'placeholder="请输入网关IP地址" value="' + deviceGroup.address + '" required>' +
                    '<label for="name">网关地址</label>' +
                    '<input type="text" class="form-control" id="update_gatewayPort" ' +
                    'placeholder="请输入网关端口" value="' + deviceGroup.address + '"  required>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
                    '</form>' +
                    '</div>';


                layer.confirm(dialog, {
                    title: "更新网关配置",
                    btn: ["更新"], //按钮
//                            width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/freshair/chartDetail?service=updateGateway",
                        type: "GET",
                        data: {
//                            tabCustomerId: customer.tabCustomerId,
                            address: $("#update_gatewayPort").val(),
//                            iP: $("#update_gatewayIP").val(),
                            serialNumber: deviceGroup.tabDeviceGroupId
                        },
                        dataType: "json",
                        success: function (result) {
                            if (result.result == "success") {
                                layer.msg("更新成功");
                                refresh();
                            } else {
                                layer.alert(result.errorMsg);
                            }
                        },
                        error: function () {
                            layer.msg("程序繁忙，请稍后重试。！");

                        }
                    });
                });
            }

            $("#addGateWayBtn").click(function () {
//                addGateway();http://localhost:8080/
                window.location.href = "${path}/deviceGroup/addDevice?mobelPhone=" + customer.mobelPhone;
                <%--window.location.href = "${path}/client/device?service=addGetway&mobelPhone=" + customer.mobelPhone;--%>
            });
//              页面事件响应
            $("#personal").click(function () {
                window.location.href = "${path}/customer/personal?mobelPhone=" + customer.mobelPhone;
            });

            $('#device_shidu_item').click(function () {
                window.location.href = "${path}/freshair/chartDetail?tabDeviceFreshairId=" + currentFreshairDeviceTabId + "&code=shidu";
            });

            $('#device_wendu_item').click(function () {
                window.location.href = "${path}/freshair/chartDetail?tabDeviceFreshairId=" + currentFreshairDeviceTabId + "&code=wendu";
            });

            $('#device_pm_item').click(function () {
                window.location.href = "${path}/freshair/chartDetail?tabDeviceFreshairId=" + currentFreshairDeviceTabId + "&code=pm";
            });
            $('#device_voc_item').click(function () {
                window.location.href = "${path}/freshair/chartDetail?tabDeviceFreshairId=" + currentFreshairDeviceTabId + "&code=voc";
            });
            $('#device_co2_item').click(function () {
                window.location.href = "${path}/freshair/chartDetail?tabDeviceFreshairId=" + currentFreshairDeviceTabId + "&code=co2";
            });

            $("#shareWithSomeone").click(function () {
                var dialog = '<div class="box">' +
                    '<form >' +
                    '<div class="form-group">' +
                    '<input type="text" class="form-control" id="invate_phoneNum" placeholder="请输入邀请用户手机号"  required>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '</div>' +
                    '</form>' +
                    '</div>';
                layer.confirm(dialog, {
                    title: "邀请",
                    btn: ["邀请"], //按钮
//                            width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/deviceGroup/addGroupByInvite",
                        type: "POST",
                        data: {
                            invitederPhone: $('#invate_phoneNum').val(),
                            tabDeviceGroupId: current_deviceGroup.tabDeviceGroupId,
                            tabCustomerId: customer.tabCustomerId
                        },
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 0) {
                                layer.msg("已成功发送邀请");
                            } else {
                                layer.alert(result.errorMsg);
                            }
                        },
                        error: function () {
                            layer.msg("程序繁忙，请稍后重试。！");
                        }
                    });
                });
            });


            //关闭通知推送与开启推送通知
            $("#notify").click(function () {
                $.ajax({
                    url: "${path}/deviceGroup/switchGroupIsSendMag",
                    type: "POST",
                    data: {
                        tabDeviceGroupId: current_deviceGroup.tabDeviceGroupId,
                        tabCustomerId: customer.tabCustomerId
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            if (result.obj == 1) {
                                $("#notifyText").html("关闭通知");
                                $("#notify").css("background", "#2BB4EA");
                            } else {
                                $("#notifyText").html("开启通知");
                                $("#notify").css("background", "red");
                            }
                        } else {
                            layer.msg("操作失败！");
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");

                    }
                });

            });

            $('#ask').click(function () {
                $.ajax({
                    url: "${path}/freshair/tabDeviceFreshairsInGroup/"+current_deviceGroup.tabDeviceGroupId,
                    type: "GET",
                    data: { },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.msg("item:"+result.code);

                            var obj = result.obj;
                            var freshDeviceArr = new Array();
                            for (var i in obj) {
                                var item = obj[i];
                                var dtItem = {
                                    "deviceCategory": item.deviceCategory,
                                    "createdDate": item.createdDate,
                                    "isDeleted": item.isDeleted,
                                    "ip": item.ip,
                                    "modifiedDate": item.modifiedDate,
                                    "deviceSeriaNumber": item.deviceSeriaNumber,
                                    "tabDeviceGroupId": item.tabDeviceGroupId,
                                    "tabDeviceFreshairId": item.tabDeviceFreshairId
                                };
                                freshDeviceArr.push(dtItem);


                            }


                            var dialog = '<div class="box">' +
                                '<form >' ;
                            for (var i in freshDeviceArr) {
                                dialog += '<div class="form-group"><div class="freshDevice-item" id="freshDevice_'+freshDeviceArr[i].deviceSeriaNumber+'"  required>'+freshDeviceArr[i].deviceSeriaNumber+'</div></div>' ;
                            }
                            if (freshDeviceArr.length == 0) {
                                for (var i in freshDeviceArr) {
                                    dialog += '<div class="form-group">当前无更多设备信息</div>' ;
                                }
                            }

                            dialog += '' +
                                '<div class="form-group">' +
                                '</div>' +
                                '<div class="form-group">' +
                                '</div>' +
                                '</form>' +
                                '</div>';


                            layer.confirm(dialog, {
                                title: "选择空气检测设备查看指数",
                                btn: [], //按钮
//                  width: "100%"
                            });


                            for (var i in freshDeviceArr) {

                                $('#freshDevice_' + freshDeviceArr[i].deviceSeriaNumber).click(function () {

                                    var frshDeviceId = $(this).attr("id").split("_")[1];

                                    currentFreshDeviceSeriaNumber = frshDeviceId;
                                    getFreshairData();

                                });

                            }

                            $('#invate2').click(function () {
                                //添加默认绑定设备

                                layer.closeAll();
                            });

                            $('#invate1').click(function () {
                                layer.closeAll();
                            });



                        } else {
                            layer.msg("操作失败！");
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");

                    }
                });


            });

            function addDefaultGroup() {
                $.ajax({
                    url: "${path}/deviceGroup/addGroupByInvite",
                    type: "POST",
                    data: {
                        invitederPhone: customer.mobelPhone,
                        tabDeviceGroupId: 1,
                        tabCustomerId: 1
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.msg("关注成功");
                            //刷新页面
                            refresh();
                        } else {
                            layer.alert("关注失败");
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");
                    }
                });
                layer.closeAll();
            };
            $("#acceptShairGroup").click(function () {
                addDefaultGroup();
            });
            $("#showAllGroup").click(function () {

                window.location.href = "${path}/deviceGroup/updateService?mobelPhone=" + account.tabCustomerId;


            });

            $("#delCurrentUserGroup").click(function () {
                $.ajax({
                    url: "${path}/deviceGroup/customerRemoveGroup",
                    type: "POST",
                    data: {
                        tabCustomerId: customer.tabCustomerId,
                        tabDeviceGroupId: current_deviceGroup
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.msg("删除成功");
                            //刷新页面
                            refresh();
                        } else {
                            layer.alert("删除失败");
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");
                    }
                });
            });



        });

    </script>

</head>
<body>

<nav>
    <div class="menuleft">
        <div class="dropdownIcon">
            <span></span>
            <span></span>
            <span></span>
        </div>
    </div>
    <div id="titleV" class="titleView">
        <span>首页</span>
    </div>
    <div class="menu">
        <i class="plus"></i>
    </div>
    <ul id="rightM" class="dropDown">
        <li id="personal"><a href="#"><i class="personal"></i> 个人中心</a></li>
        <li id="addGateWayBtn"><a href="#">扫描设备</a></li>
        <li id="openAirKiss_btn"><a href="#">配置WIFI</a></li>
        <li id="shareWithSomeone"><a href="#">邀请</a></li>
        <li id="acceptShairGroup"><a href="#">添加公共设备</a></li>
        <li id="showAllGroup"><a href="#">查看设备信息</a></li>
        <li id="delCurrentUserGroup"><a href="#">删除当前分组</a></li>

    </ul>
    <ul id="leftM" class=" leftM">
        <%--<li><a href="#">Menu Item 1</a></li>--%>
        <%--<li><a href="#">Menu Item 2</a></li>--%>
        <%--<li id="noMoreData"><a href="#">没有更多数据了哦</a></li>--%>
    </ul>
</nav>

<div id="page-content" style="  display: none;">
    <!--内容区 -->
    <section id="gateWay_content" class="gateWay_content">
        <div class="top">
            <div id="topContent" class="topContent">
                <div class="row">
                    <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                        <h3 class="title"><span>分组信息</span>
                            <div id="groupName"> Qixu Lorem</div>
                        </h3>
                    </div>
                </div>
            </div>
        </div>

        <div id="ask" class="ask-content">
            <img src="${path}/page/img/icon/ask.png">
        </div>
        <div class="gateWay_detail">
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                    <div class="row content">
                        <div class="col-xs-4">
                            <div class="item square" id="device_shidu_item">
                                <img src="${path}/page/img/icon/shidu1.png">
                                <span id="device_shidu_info">空气湿度：--%</span>
                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="item square" id="device_wendu_item">
                                <img src="${path}/page/img/icon/wendu.png">
                                <span class="" id="device_wendu_info">温度：--</span>
                            </div>
                        </div>

                        <div class="col-xs-4">
                            <div class="item square" id="device_co2_item">
                                <img src="${path}/page/img/icon/CO2.png">
                                <span id="device_co2_info">co2：--%</span>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="item" id="device_pm_item">
                                <img src="${path}/page/img/icon/pm.png">
                                <span id="device_pm_info">PM2.5：--%</span>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="item" id="device_voc_item">
                                <img src="${path}/page/img/icon/voc.png">
                                <span id="device_voc_info">voc：--%</span>
                            </div>
                        </div>
                    </div><!--row content-->
                </div><!--col-xs-12-->
            </div> <!--row-->
        </div><!--gateWay_detail-->
    </section>
    <!--内容区 -->
    <section id="device-ct" class="device-content">
        <div class="row">
            <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
                <h3 class="title"><span>设备信息</span>当前网关下所有设备</h3>
            </div>
        </div>
    </section>
    <section id="device-list" class="device-list">
        <div class="row">
            <div id="devicelistPanel" class="col-xs-12 ">
                <!--col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">-->

            </div><!--col-xs-12 -->
        </div><!--row -->
    </section>
</div>


<div id="notify" class="notify">
    <span id="notifyText">关闭通知</span>
</div>

<div id="nodata">
    <img src="${path}/page/img/icon/noData.png"/>
    <div class="label-title">当前并无数据哦！<span id="addGateWay">立即添加 </span></div>
</div>
<script type="text/javascript" src="${path}/page/js/index.js" charset="utf8"></script>

</body>
</html>
