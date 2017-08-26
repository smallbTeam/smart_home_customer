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
            "tabCustomerId": '${customer.tabCustomerId}',
            "mobelPhone": '${customer.mobelPhone}',
            "wxId": '${customer.wxId}',
            "nickName": '${customer.nickName}',
            "birthday": '${customer.birthday}',
            "sex": '${customer.sex}',
            "token": '${customer.token}'
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
            var deviceId;
            var code;
            var t1;  ////定时器


            $("#openAirKiss_btn").click(function () {
                window.location.href = "${path}/client/home?service=openWifiScan&mobelPhone=" + customer.mobelPhone;
            });

//            webscoket
//            function WebSocketTest() {
//                if ('WebSocket' in window) {
//                    ws = new WebSocket('ws://s-357114.gotocdn.com/smart_home/webSocketServer');
//                   //ws = new WebSocket('ws://127.0.0.1:9080/smarthome/webSocketServer');
//                    //ws = new WebSocket('ws://localhost:8080/smarthome/webSocketServer');
//                }
//                else if ('MozWebSocket' in window) {
//                    ws = new MozWebSocket("ws://s-357114.gotocdn.com/smart_home/webSocketServer");
//                }
//                else {
//                    ws = new SockJS("http://s-357114.gotocdn.com/smart_home/sockjs/webSocketServer");
//                }
//                // 打开一个 web socket
//                ws.onopen = function () {
//                    // Web Socket 已连接上，使用 send() 方法发送数据
//                    ws.send(current_deviceGroup.id);
//                };
//
//                ws.onmessage = function (evt) {
//                    var msg = evt.data;
////                    alert("msg:" + msg);
//                    var jsonmsg = JSON.parse(msg);
//                    $("#device_pm_info").html('PM2.5:'+jsonmsg.pm+'μg/m³');
//                    $("#device_shidu_info").html('湿度:'+jsonmsg.shidu+'%');
//                    $("#device_wendu_info").html('温度:'+jsonmsg.wendu+'℃');
//                    $("#device_voc_info").html('VOC:'+jsonmsg.voc+'g/L');
//                    $("#device_co2_info").html('CO2:'+jsonmsg.co2+'ppm');
//
//                };
//
//                ws.onclose = function () {
//
//                };
//            }

            window.onbeforeunload = function () {
                // ws.close();
                window.clearInterval(t1);//去掉定时器
            };

            $('.dropDown').mouseleave(function () {
                $('.dropDown').slideUp("slow", function () {
                    $(this).fadeOut(2000);
                });
            });

            // 网关切换，页面数据重新加载
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
                deviceArray = new Array();
                //重新请求分组下所有设备
                $.ajax({
                    url: "${path}/deviceGroup/"+deviceGroup.tabDeviceGroupId,
                    type: "POST",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            //获取分组信息
                            var tabDeviceGroupinfo = result.obj.tabDeviceGroupinfo;
                            //获取空气检测设备信息
                            var groupFreshairList = result.obj.groupFreshairList
                            //如果用户有空气检测设备
                            if ((null != groupFreshairList) && (0 < groupFreshairList.length)) {
                                var freshairList = new Array();
                                for (var i in groupFreshairList) {
//                                    list_map.push({tabDeviceFreshairId:groupFreshairList[i].tabDeviceFreshairId,
//                                        deviceSeriaNumber:groupFreshairList[i].deviceSeriaNumber,
//                                        deviceCategory:groupFreshairList[i].deviceCategory,
//                                        ip:groupFreshairList[i].ip,
//                                        name:groupFreshairList[i].name,
//                                        state:groupFreshairList[i].state
//                                    });
                                    list_map.push(groupFreshairList[i]);

                                    //加载第一个空气检测设备
                                    getFreshairInfo(deviceSeriaNumber,){

                                    }

                                    var freshair = groupFreshairList[i];

                                    //将设备信息添加到空气检测设备列表

                                    var wenduval = "";
                                    var shiduval = "";
                                    var pm = "";
                                    var co2 = "";
                                    var voc = "";
                                    deviceId = itemDevice.deviceId;




                                    /*$.ajax({
                                     url: "
                                    ${path}/client/device?service=getDeviceByDeviceId",
                                     type: "GET",
                                     data: {
                                     deviceId: itemDevice.deviceId
                                     },
                                     dataType: "json",
                                     success: function (result) {
                                     //console.log("设备网管数据：" + JSON.stringify(result));
                                     for (var j in result.operationResult.deviceDataList) {
                                     var deviceData = result.operationResult.deviceDataList[j];
                                     if (deviceData.categoryParameterId == 1) {
                                     if (deviceData.value == "") {
                                     wenduval += deviceData.name + ": " + deviceData.unit;
                                     } else {
                                     wenduval += deviceData.name + ": " + deviceData.value + deviceData.unit;
                                     }
                                     $('#device_wendu_info').html(wenduval);
                                     } else if (deviceData.categoryParameterId == 2) {
                                     if (deviceData.value == "") {
                                     shiduval += deviceData.name + ": " + deviceData.unit;
                                     } else {
                                     shiduval += deviceData.name + ": " + deviceData.value + deviceData.unit;
                                     }
                                     $('#device_shidu_info').html(shiduval);
                                     } else if (deviceData.categoryParameterId == 3) {
                                     if (deviceData.value == "") {
                                     pm += deviceData.name + ": " + deviceData.unit;
                                     } else {
                                     pm += deviceData.name + ": " + deviceData.value + deviceData.unit;
                                     }
                                     $('#device_pm_info').html(pm);
                                     } else if (deviceData.categoryParameterId == 4) {
                                     if (deviceData.value == "") {
                                     voc += deviceData.name + ": " + deviceData.unit;
                                     7
                                     } else {
                                     voc += deviceData.name + ": " + deviceData.value + deviceData.unit;
                                     }
                                     $('#device_voc_info').html(voc);
                                     } else if (deviceData.categoryParameterId == 5) {
                                     if (deviceData.value == "") {
                                     co2 += deviceData.name + ": " + deviceData.unit;
                                     } else {
                                     co2 += deviceData.name + ": " + deviceData.value + deviceData.unit;
                                     }
                                     $('#device_co2_info').html(co2);
                                     }
                                     }
                                     }
                                     });*/

                                } else {
                                    var deviceItem = {
                                        "deviceTypeAttention": itemDevice.deviceTypeAttention,
                                        "DeviceData": itemDevice.DeviceData,
                                        "deviceTypeId": itemDevice.deviceTypeId,
                                        "DeviceNo": itemDevice.DeviceNo,
                                        "gatewayIP": itemDevice.gatewayIP,
                                        "deviceTypeName": itemDevice.deviceTypeName,
                                        "gatewayGatewayPort": itemDevice.gatewayGatewayPort,
                                        "id": itemDevice.id,
                                        "deviceGetwayId": itemDevice.deviceGetwayId,
                                        "deviceName": itemDevice.deviceName,
                                        "deviceTypeModel": itemDevice.deviceTypeModel,
                                        "deviceTypeDescribtion": itemDevice.deviceTypeDescribtion,
                                        "deviceState": itemDevice.deviceState
                                    };

                                    if ($.inArray(deviceItem, deviceArray) == -1) {

                                        deviceArray.push(deviceItem);
                                        //向设备列表区域添加每条设备信息
                                        var html = '<div id="list-content_' + deviceItem.id + '" class="list-content">' +
                                            '<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">' +
                                            '<div class="list-item">' +
                                            '<div class="list-item-content">' +
                                            '<div class="leftContent">' +
                                            '<div id="deviceMenu_' + deviceItem.id + '" class="device-menu">' +
                                            '<img src="${path}/page/img/icon/ison.png"/>' +
                                            '</div>' +
                                            ' <span id="deviceStatus_' + deviceItem.id + '" class="subtitle">状态：开启中</span>' +
                                            '</div>' +
                                            '<div class="rightContent">' +
                                            '<div class="topLabel">' +
                                            '<div class="title">海尔变频空调</div>' +
                                            '<span class="subline">型号：x30698</span>' +
                                            '<span class="subline">设备编号：0102030</span>' +
                                            '<span class="contentline">设备状态：良好</span>' +
                                            '<br/>' +
                                            '<span class="contentline">设备类型：空调</span>' +
                                            '<br/>' +
                                            '<span class="contentline">设备类型：空调</span>' +
                                            '</div>' +
                                            '<div class="bottomLabel ">' +
                                            '<div class="bottomLabel-item pull-left"><img ' +
                                            'src="${path}/page/img/icon/blue.png"/><span>正常</span></div>' +
                                            '<div class="bottomLabel-item pull-left"><img ' +
                                            'src="${path}/page/img/icon/blue.png"/><span>18c</span></div>' +
                                            '<div class="bottomLabel-item pull-left"><img ' +
                                            'src="${path}/page/img/icon/blue.png"/><span>100</span></div>' +
                                            '</div>' +
                                            '<div class="list-item-hover">' +
                                            '<div id="delete_' + deviceItem.id + '" class="item-icon ">' +
                                            '<img src="${path}/page/img/icon/delete.png" alt="img30"/>' +
                                            '<span>删除</span>' +
                                            '</div>' +
                                            '<div id="edit_' + deviceItem.id + '" class="item-icon ">' +
                                            '<img src="${path}/page/img/icon/edit.png" alt="img30"/>' +
                                            '<span>编辑</span>' +
                                            '</div>' +
                                            '<div id="detail_' + deviceItem.id + '" class="item-icon ">' +
                                            '<img src="${path}/page/img/icon/detail.png" alt="img30"/>' +
                                            '<span>详情</span>' +
                                            '</div>' +
                                            '  </div>' +
                                            ' </div>' +
                                            ' </div>' +
                                            '</div>' +
                                            '</div><!--list-content col-xs-12 -->' +
                                            '</div><!--list-content -->';

                                        $("#devicelistPanel").append(html);
                                        $('#delete_' + deviceItem.id).click(function () {
                                            var id = $(this).attr("id").split("_")[1];
                                            $.ajax({
                                                url: "${path}/client/device?service=delDeviceById",
                                                data: {
                                                    deviceId: id
                                                },
                                                success: function (msg) {
                                                    if (msg.result == "success") {
                                                        $("#list-content_" + id).remove();

                                                    } else {
                                                        layer.msg("删除失败");
                                                    }
                                                },
                                                error: function () {
                                                    layer.msg("删除失败");
                                                }
                                            });
                                        });
                                        $('#edit_' + deviceItem.id).click(function () {
                                            var id = $(this).attr("id").split("_")[1];
                                            var index = -1;
                                            for (var i in deviceArray) {
                                                if (id == deviceArray[i].id) {
                                                    index = i;
                                                }
                                            }
                                            if (index == -1) return;
                                            addDevice(false, deviceArray[index]);
                                        });

                                        $('#detail_' + deviceItem.id).click(function () {
                                            var id = $(this).attr("id").split("_")[1];
                                            window.location.href = "${path}/client/device?service=getDeviceByDeviceId&deviceId=" + id;
                                        });
                                        $('#deviceMenu_' + deviceItem.id).click(function () {
                                            var id = $(this).attr("id").split("_").last();
                                        });
                                    }
                                }
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
                        layer.error(msg);
                    }
                });

            }

//        数据请求
            function refresh() {
                //  依据客户表ID 加载客户分组
                $.ajax({
                    url: "${path}/deviceGroup/allRelCustomerDeviceGroups",
                    type: "POST",
                    data: {
                        customerId: customer.tabCustomerId
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            var groupList = result.obj;
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
//                                              //WebSocketTest();
                                            }
                                        }
                                    });
                                }
                            }
                            if (deviceGroupArray.length > 0) {
                                current_deviceGroup = deviceGroupArray[0];
                                reloadPageContent(current_deviceGroup);
//                                if (ws != null) {
//                                    ws.send(current_deviceGroup.id);
//                                } else {
////                                    WebSocketTest();
//                                }
                            }
                            if (!isExist("#gateWayId_nomore")) {
                                $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');
                            }
                        } else {
                            layer.alert(result.error);
                            $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');
                        }
                    },
                    error: function () {
                        layer.error();
                        $("#leftM").append('<li id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></li>');
                    }
                });

            }

            /**
             * 请求页面数据
             */
            refresh();
            getTimer();
            //WebSocketTest();

            /**
             * 定时器方法
             */
            function getTimer() {
                t1 = window.setInterval("getFreshAirData()", 10000);//使用字符串执行方法
//                window.clearInterval(t1);//去掉定时器
            }

            /**
             * 定时获取空气监测数据
             */
            function getFreshAirData() {
                $.ajax({
                    url: "${path}/client/device?service=getGatewayListByCustomerId",
                    type: "GET",
                    data: {
                        customerId: customer.id
                    },
                    dataType: "json",
                    success: function (result) {
                        var jsonmsg = JSON.parse(result.data);
                        $("#device_pm_info").html('PM2.5:' + jsonmsg.pm + 'μg/m³');
                        $("#device_shidu_info").html('湿度:' + jsonmsg.shidu + '%');
                        $("#device_wendu_info").html('温度:' + jsonmsg.wendu + '℃');
                        $("#device_voc_info").html('VOC:' + jsonmsg.voc + 'g/L');
                        $("#device_co2_info").html('CO2:' + jsonmsg.co2 + 'ppm');

                    }

                });
            }


            function addDeviceDialog(deviceTypes) {
                var dialog = '<div id="addDeviceDialog" class="box">' +
                    '<form role="form" >' +
                    '<div class="form-group">' +
                    '<label for="name">设备类型</label>' +
                    '<select id="deviceType"  class="form-control" >';

                for (var i in deviceTypes) {
                    dialog += '<option value="' + deviceTypes[i].id + '">' + deviceTypes[i].name + '</option>';
                }
                dialog += '</select>';
                if (deviceTypes.length <= 0) {
                    dialog += '<div class="click-title">当前无设备类型，<span id="addDeviceType">立即添加?</span></div>';
                }

                dialog += '<div class="form-group">' +
                    '</div>' +
                    '<label for="name">设备名称</label>' +
                    '<input type="text" class="form-control" id="add_gatewayName" placeholder="请输入设备名称" required>' +
                    '<label for="name">设备型号</label>' +
                    '<input type="text" class="form-control" id="add_gatewayNo" placeholder="请输入设备型号" required>' +
                    '</div>' +
                    '</form>' +
                    '</div>';

                layer.confirm(dialog, {
                    title: "添加网关",
                    btn: ["提交"], //按钮
                    width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/client/device?service=addDevice",
                        type: "GET",
                        data: {
                            //customerId: customer.id,
                            name: $("#add_gatewayName").val(),
                            deviceCategoryId: $("#deviceType").val(),
                            gatewaySerialNumber: current_deviceGroup.gatewayId,
                            seriaNumber: $("#add_gatewayNo").val(),
                        },
                        dataType: "json",
                        success: function (result) {
                            //console.log(result);
                            if (result.result == "success") {
                                layer.msg("添加成功")
                                reloadPageContent(current_deviceGroup);
                                refresh();
                            } else {
                                layer.alert(result.error);
                            }
                        },
                        error: function () {
                            layer.msg("程序繁忙，请稍后重试。！");
                        }
                    });
                });

                $("#addDeviceType").on('click', function () {
                    var dialog = '<div class="box">' +
//                    '<form >'+
                        '<div class="form-group">' +
                        '<label for="name">设备名称</label>' +
                        '<input type="text" class="form-control" id="add_deviceTypeName" placeholder="请输入设备名称" required>' +
                        '<label for="name">设备型号</label>' +
                        '<input type="text" class="form-control" id="add_deviceTypeModel" placeholder="请输入设备型号" required>' +
                        '</div>' +
                        '<div class="form-group">' +
                        '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
//                    '</form>'+
                        '</div>';


                    layer.confirm(dialog, {
                        title: "添加设备类型",
                        btn: ["提交"], //按钮
//                            width: "100%"
                    }, function () {
                        $.ajax({
                            url: "${path}/client/device?service=addDeviceCategory",
                            type: "GET",
                            data: {
                                customerId: customer.id,
                                model: $("#add_deviceTypeModel").val(),
                                name: $("#add_deviceTypeName").val()
                            },
                            dataType: "json",
                            success: function (result) {
                                if (result.result == "success") {
                                    layer.msg("添加成功")
                                } else {
                                    layer.alert(result.error);
                                }
                            },
                            error: function () {
                                layer.msg("程序繁忙，请稍后重试。！");

                            }
                        });
                    });
                });
            }

            function addGateway() {
                var dialog = '<div class="box">' +
                    '<form >' +
                    '<div class="form-group">' +
                    '<label for="name">网关名称</label>' +
                    '<input type="text" class="form-control" id="add_gatewayName" placeholder="请输入网关编号" required>' +

//                            '<label for="name">网关IP</label>'+
//                            '<input type="text" class="form-control" id="add_gatewayIP" placeholder="请输入网关IP">'+

                    '<label for="name">网关地址</label>' +
                    '<input type="text" class="form-control" id="add_gatewayPort" placeholder="请输入网关地址" required>' +
                    '</div>' +
                    '<div class="form-group">' +
                    '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
                    '</form>' +
                    '</div>';


                layer.confirm(dialog, {
                    title: "添加网关",
                    btn: ["提交"], //按钮
//                            width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/client/device?service=addGatewayForCustomer",
                        type: "GET",
                        data: {
                            customerId: customer.id,
                            groupName: $("#add_gatewayPort").val(),
//                          iP: $("#add_gatewayIP").val(),
                            gatewaySerialNumber: $("#add_gatewayName").val()
                        },
                        dataType: "json",
                        success: function (result) {
                            //console.log(result);
                            if (result.result == "success") {
                                layer.msg("添加成功")
                                refresh();
                            } else {
                                layer.alert(result.error);
                            }
                        },
                        error: function () {
                            layer.msg("程序繁忙，请稍后重试。！");

                        }
                    });
                });
            }

            function addDevice(isAdd, device) {
                var deviceTypes = new Array();
                $.ajax({
                    url: "${path}/client/device?service=getDeviceCategoryList",
                    type: "GET",
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        if (result.result == "success") {
                            for (var i in result.operationResult) {
//                                    alert("result:"+JSON.stringify(result.operationResult));
                                var item = result.operationResult[i];
                                var dt = {
                                    "id": item.id,
                                    "name": item.name,
                                    "model": item.model,
//                                  "attention":item.attention,
//                                  "describtion":item.describtion,
                                };

                                deviceTypes.push(dt);
                            }
                            if (isAdd) {
                                addDeviceDialog(deviceTypes);
                            } else {
                                updateDeviceMsg(device, deviceTypes);
                            }
                        } else {
                            layer.alert(result.error);
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");
                    }
                });
            }


            //            更新设备信息
            function updateDeviceMsg(device, deviceTypes) {
                var dialog = '<div id="updateDeviceDialog" class="box">' +
                    '<form role="form" >' +
                    '<div class="form-group">' +
                    '<label for="name">设备类型</label>' +
                    '<select id="deviceTypeCurrent"  class="form-control" >';

                var index = 0;
                for (var i in deviceTypes) {
                    dialog += '<option value="' + deviceTypes[i].id + '">' + deviceTypes[i].name + '</option>';
                    if (deviceTypes[i].id == device.deviceTypeId) {
                        index = i;
                    }
                }
                dialog += '</select>';

                dialog += '<div class="form-group">' +
                    '</div>' +
                    '<label for="name">设备名称</label>' +
                    '<input type="text" class="form-control" id="update_gatewayName" ' +
                    'placeholder="请输入设备名称" value="' + device.deviceName + '" required>' +
                    '<label for="name">设备型号</label>' +
                    '<input type="text" class="form-control" id="update_gatewayNo" ' +
                    'placeholder="请输入设备型号"  value="' + device.DeviceNo + '" required>' +
                    '</div>' +
                    '</form>' +
                    '</div>';


                layer.confirm(dialog, {
                    title: "修改信息",
                    btn: ["更新"], //按钮
                    width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/client/device?service=updateDeviceById",
                        type: "GET",
                        data: {
                            name: $("#update_gatewayName").val(),
                            deviceId: device.id,
                            deviceCategoryId: current_deviceGroup.id,
                            parentId: customer.id,
                            gatewaySerialNumber: $("#deviceTypeCurrent").val(),
                            seriaNumber: $("#update_gatewayNo").val(),
                            deviceId: device.gatewayDeviceID

                        },
                        dataType: "json",
                        success: function (result) {
                            //console.log(result);
                            if (result.result == "success") {
                                layer.msg("更新成功")
                                reloadPageContent(current_deviceGroup);
                                refresh();
                            } else {
                                layer.alert(result.error);
                            }
                        },
                        error: function () {
                            layer.msg("程序繁忙，请稍后重试。！");
                        }
                    });
                });

                $("#deviceTypeCurrent")[index].selected(true);
            }

            //更新网关信息，留用
            function updateGateway(deviceGroup) {
                var dialog = '<div class="box">' +
                    '<form >' +
                    '<div class="form-group">' +
                    '<label for="name">网关名称</label>' +
                    '<input type="text" class="form-control" id="update_gatewayIP" ' +
                    'placeholder="请输入网关IP地址" value="' + deviceGroup.address + '" required>' +

//                            '<label for="name">网关IP</label>'+
//                            '<input type="text" class="form-control" id="add_gatewayIP" placeholder="请输入网关IP">'+

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
                        url: "${path}/client/device?service=updateGateway",
                        type: "GET",
                        data: {
//                            customerId: customer.id,
                            address: $("#update_gatewayPort").val(),
//                            iP: $("#update_gatewayIP").val(),
                            serialNumber: deviceGroup.tabDeviceGroupId
                        },
                        dataType: "json",
                        success: function (result) {
                            //console.log(result);
                            if (result.result == "success") {
                                layer.msg("更新成功");
                                refresh();
                            } else {
                                layer.alert(result.error);
                            }
                        },
                        error: function () {
                            layer.msg("程序繁忙，请稍后重试。！");

                        }
                    });
                });
            }

//              页面事件响应
            $("#addGateWayBtn").click(function () {
//                addGateway();
                window.location.href = "${path}/client/device?service=addGetway&mobelPhone=" + customer.mobelPhone;
            });
            $("#addGateWay").click(function () {
//                addGateway();
                window.location.href = "${path}/client/device?service=addGetway&mobelPhone=" + customer.mobelPhone;
            });

            $("#personal").click(function () {
                window.location.href = "${path}/client/customer?service=personal&mobelPhone=" + customer.mobelPhone;
            });

            $('#device_shidu_item').click(function () {
                window.location.href = "${path}/client/device?service=chartDetail&deviceId=" + deviceId + "&code=shidu";
            });

            $('#device_wendu_item').click(function () {
                window.location.href = "${path}/client/device?service=chartDetail&deviceId=" + deviceId + "&code=wendu";
            });

            $('#device_pm_item').click(function () {
                window.location.href = "${path}/client/device?service=chartDetail&deviceId=" + deviceId + "&code=pm";
            });
            $('#device_voc_item').click(function () {
                window.location.href = "${path}/client/device?service=chartDetail&deviceId=" + deviceId + "&code=voc";
            });
            $('#device_co2_item').click(function () {
                window.location.href = "${path}/client/device?service=chartDetail&deviceId=" + deviceId + "&code=co2";
            });

            $("#shareWithSomeone").click(function () {
                var dialog = '<div class="box">' +
                    '<form >' +
                    '<div class="form-group">' +
//                '<label for="name">手机号码</label>' +
                    '<input type="text" class="form-control" id="invate_phoneNum" placeholder="请输入邀请用户手机号"  required>' +

//                            '<label for="name">网关IP</label>'+
//                            '<input type="text" class="form-control" id="add_gatewayIP" placeholder="请输入网关IP">'+

                    '</div>' +
                    '<div class="form-group">' +
                    '</div>' +
//                            '<div id="addGatewaySubmit" class="btn-default" >提交</div>'+
                    '</form>' +
                    '</div>';


                layer.confirm(dialog, {
                    title: "共享",
                    btn: ["分享"], //按钮
//                            width: "100%"
                }, function () {
                    $.ajax({
                        url: "${path}/client/customer?service=accountIsExit",
                        type: "GET",
                        data: {
                            mobelPhone: $("#invate_phoneNum").val()
//                        serialNumber: current_deviceGroup.id
                        },
                        dataType: "json",
                        success: function (result) {
                            //console.log(result);
                            if (result.operationResult) {
                                var invitederId = result.customer.customerId;
                                $.ajax({
                                    url: "${path}/client/device?service=addGateWayByInvite",
                                    type: "GET",
                                    data: {
                                        invitederId: invitederId,
                                        gatewaySerialNumber: current_deviceGroup.id,
                                        customerId: customer.id
                                    },
                                    dataType: "json",
                                    success: function (result) {
                                        //console.log(result);
                                        if (result.result == "success") {
                                            layer.msg("已成功发送邀请");


                                        } else {
                                            layer.alert(result.error);
                                        }
                                    },
                                    error: function () {
                                        layer.msg("程序繁忙，请稍后重试。！");
                                    }
                                });

                            } else {
                                layer.msg("该用户未注册，邀请失败");
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

                // alert("gid:"+current_deviceGroup.id+":"+customer.id);
                $.ajax({
                    url: "${path}/client/device?service=switchGatewayIsSendMag",
                    type: "GET",
                    data: {
                        gatewaySerialNumber: current_deviceGroup.id,
                        customerId: customer.id
                    },
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
                        if (result.result == "success") {
                            if (result.operationResult) {
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

        });

    </script>

</head>
<body>
<!--<div style="clear:both;">-->
<!--<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>-->
<!--<script src="/follow.js" type="text/javascript"></script>-->
<!--</div>-->

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
        <li id="addGateWayBtn"><a href="#">添加网关</a></li>
        <li id="openAirKiss_btn"><a href="#">更改网关配置</a></li>
        <li id="shareWithSomeone"><a href="#">分享</a></li>

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
                        <h3 class="title"><span>网关信息</span>
                            <div id="groupName"> Qixu Lorem</div>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
        <%--<div class="row">--%>
        <%--<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">--%>
        <%--<div id class="common-detail">--%>
        <%--<div class="circle-status">--%>
        <%--&lt;%&ndash;<span id="gatewayIP">IP: 192.168.92.13:80</span>&ndash;%&gt;--%>
        <%--<span id="groupStatus" class="menu-status">未开启</span>--%>
        <%--</div>--%>
        <%--<!--<canvas id="shadowcanvas">-->--%>
        <%--<!--</canvas>-->--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>
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
