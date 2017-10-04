<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html lang="en">
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <meta charset="UTF-8">
    <title>设备列表</title>
    <link rel="stylesheet" type="text/css" href="${path}/page/css/normalize.css" />

    <link rel="stylesheet" type="text/css" href="${path}/page/css/bootstrap.css" />

    <link rel="stylesheet" type="text/css" href="${path}/page/css/updateDevice.css" />
    <!--<link rel="stylesheet" type="text/css" href="third/alertcustom/css/example.css">-->
    <link rel="stylesheet" type="text/css" href="${path}/page/js/third/alertcustom/css/sweet-alert.css">
    <script type="text/javascript" src="${path}/page/js/third/alertcustom/js/sweet-alert.min.js"></script>
</head>

<style>

    .row{margin:0;}
    .col-lg-1,.col-lg-10,.col-lg-11,.col-lg-12,.col-lg-2,.col-lg-3,.col-lg-4,.col-lg-5,.col-lg-6,.col-lg-7,.col-lg-8,.col-lg-9,.col-md-1,.col-md-10,.col-md-11,.col-md-12,.col-md-2,.col-md-3,.col-md-4,.col-md-5,.col-md-6,.col-md-7,.col-md-8,.col-md-9,.col-sm-1,.col-sm-10,.col-sm-11,.col-sm-12,.col-sm-2,.col-sm-3,.col-sm-4,.col-sm-5,.col-sm-6,.col-sm-7,.col-sm-8,.col-sm-9,.col-xs-1,.col-xs-10,.col-xs-11,.col-xs-12,.col-xs-2,.col-xs-3,.col-xs-4,.col-xs-5,.col-xs-6,.col-xs-7,.col-xs-8,.col-xs-9{padding:0;}

</style>

<body>


<!--<div style="clear:both;">-->
<!--<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>-->
<!--<script src="/follow.js" type="text/javascript"></script>-->
<!--</div>-->

<nav>
    <div class="menuleft">
        <div class="dropdownIcon">
            <i class="arrowUp"></i>
        </div>
    </div>
    <div id="titleV" class="titleView">
        <span>设备列表</span>
    </div>
</nav>

<!--内容区 -->
<section id="user-group">
    <%--<section id="device-content-0" class="device-content" >--%>

        <%--<div class="top">--%>
            <%--<div id="  " class="topContent">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">--%>
                        <%--<h3 class="title"><span>网关信息</span>Qixu Lorem</h3>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<div class="col-xs-12 ">--%>
                <%--<div id="" class="list-content">--%>
                    <%--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">--%>
                        <%--<div class="device-item">--%>
                            <%--<img src="${path}/page/img/addservice/aircleaner.png">--%>
                            <%--<div class="rightContent">--%>
                                <%--<span class="title">新风空调</span>--%>
                                <%--<span>美的xb-102128</span>--%>
                                <%--<span>2017-08-16</span>--%>

                            <%--</div>--%>
                            <%--<div class="operation pull-right">--%>
                                <%--<div class="edit">--%>
                                    <%--<img src="${path}/page/img/addservice/editicon.png"/>--%>
                                <%--</div>--%>
                                <%--<div class="delete">--%>
                                    <%--<img src="${path}/page/img/addservice/delete.png"/>--%>
                                <%--</div>--%>


                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">--%>
                        <%--<div class="device-item">--%>
                            <%--<img src="${path}/page/img/addservice/aircleaner.png">--%>
                            <%--<div class="rightContent">--%>
                                <%--<span class="title">新风空调</span>--%>
                                <%--<span>美的xb-102128</span>--%>
                                <%--<span>2017-08-16</span>--%>

                            <%--</div>--%>
                            <%--<div class="operation pull-right">--%>
                                <%--<div class="edit">--%>
                                    <%--<img src="${path}/page/img/addservice/editicon.png"/>--%>
                                <%--</div>--%>
                                <%--<div class="delete">--%>
                                    <%--<img src="${path}/page/img/addservice/delete.png"/>--%>
                                <%--</div>--%>


                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">--%>
                        <%--<div class="device-item">--%>
                            <%--<img src="${path}/page/img/addservice/aircleaner.png">--%>
                            <%--<div class="rightContent">--%>
                                <%--<span class="title">新风空调</span>--%>
                                <%--<span>美的xb-102128</span>--%>
                                <%--<span>2017-08-16</span>--%>

                            <%--</div>--%>
                            <%--<div class="operation pull-right">--%>
                                <%--<div class="edit">--%>
                                    <%--<img src="${path}/page/img/addservice/editicon.png"/>--%>
                                <%--</div>--%>
                                <%--<div class="delete">--%>
                                    <%--<img src="${path}/page/img/addservice/delete.png"/>--%>
                                <%--</div>--%>


                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</section>--%>

    <%--<section id="device-content-1"  class="device-content">--%>

        <%--<div class="top">--%>
            <%--<div id="topContent" class="topContent">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">--%>
                        <%--<h3 class="title"><span>网关信息</span>Qixu Lorem</h3>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<div class="col-xs-12 ">--%>
                <%--<div id="list-content" class="list-content">--%>
                    <%--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">--%>
                        <%--<div class="device-item">--%>
                            <%--<img src="${path}/page/img/addservice/aircleaner.png">--%>
                            <%--<div class="rightContent">--%>
                                <%--<span class="title">新风空调</span>--%>
                                <%--<span>美的xb-102128</span>--%>
                                <%--<span>2017-08-16</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">--%>
                        <%--<div class="device-item">--%>
                            <%--<img src="${path}/page/img/addservice/heater.png">--%>
                            <%--<div class="rightContent">--%>
                                <%--<span class="title">热水器</span>--%>
                                <%--<span>美的xb-102128</span>--%>
                                <%--<span>2017-08-16</span>--%>

                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">--%>
                        <%--<div class="device-item">--%>
                            <%--<img src="${path}/page/img/addservice/chandelier.png">--%>
                            <%--<div class="rightContent">--%>
                                <%--<span class="title">新风空调</span>--%>
                                <%--<span>美的xb-102128</span>--%>
                                <%--<span>2017-08-16</span>--%>

                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</section>--%>

</section>



<script src='${path}/page/js/jquery.js'></script>
<script src="${path}/page/js/third/layer/layer.js"></script>
<script>
    $(document).ready(function () {
        $(".menuleft").click(function () {
            window.history.back();
        });


        var deviceGroupArray = new  Array();



        var customer = {
            "tabCustomerId": '${account.tabCustomerId}',
            "mobelPhone": '${account.mobelPhone}',
            "wxId": '${account.wxId}',
            "nickName": '${account.nickName}',
            "birthday": '${account.birthday}',
            "sex": '${account.sex}',
            "token": '${account.token}'
        };

        customer.tabCustomerId = 1;

        refreshData();

        function updateDevice(id) {
            var dialog = '<div class="box">' +
                '<form >' +
                '<div class="form-group">' +
                '<label for="name">设备名称</label>' +
                '<input type="text" class="form-control" id="update_name" ' +
                'placeholder="请输入设备名称" value="" required>' +
//                '<label for="name">网关地址</label>' +
//                '<input type="text" class="form-control" id="update_gatewayPort" ' +
//                'placeholder="请输入网关端口" value="' + deviceGroup.address + '"  required>' +
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
                    url: "${path}/freshair/CustomerUpdateTabDeviceFreshair",
                    type: "POST",
                    data: {
                        tabDeviceFreshairId:id,
                        tabCustomerId: customer.tabCustomerId,
                        name: $("#update_name").val(),
//
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.msg("更新成功");

                            refreshData();
                        } else {
                            layer.msg("更新失败");
                        }
                    },
                    error: function () {
                        layer.msg("程序繁忙，请稍后重试。！");

                    }
                });
            });
        }

            function userGroupDeviceItemContenReload(device) {

            var html = '<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">'+
                '<div class="device-item">'+
            '<img src="${path}/page/img/addservice/aircleaner.png">'+
            '<div class="rightContent">'+
            '<span class="title">'+device.ip+'</span>'+
            '<span>'+device.deviceSeriaNumber+'</span>'+
            '<span>'+device.createdDate+'</span>'+
            '</div>'+
                '<div class="operation pull-right">'+
                '<div class="edit" id="edit_'+device.id+'">'+
                ' <img src="${path}/page/img/addservice/editicon.png"/>'+
                ' </div>'+
                '  <div class="delete" id="delete_'+device.id+'">'+
                '  <img src="${path}/page/img/addservice/delete.png"/>'+
                '  </div>'+
                '  </div>'+
            '</div>'+
            '</div>';

            $('#list-content').append(html);

            $("#edit_"+device.id).click(function () {
                var deviceID = $(this).attr("id").split("_")[1];

                updateDevice(deviceID);

            });
                $("#delete_"+device.id).click(function () {
                    var deviceID = $(this).attr("id").split("_")[1];
                    $.ajax({
                        url: "${path}/freshair/CustomerUpdateTabDeviceFreshair",
                        type: "POST",
                        data: {tabDeviceFreshairId:deviceID,tabCustomerId: customer.tabCustomerId,isDeleted:1},
                        dataType: "json",
                        success: function (result) {
                            if (result.code == 0) {
                                layer.msg("删除成功");
                            }else{
                                layer.msg("删除失败");
                            }
                        },
                        error: function (msg) {
                            layer.msg('error:'+msg.toString());
                         }
                    });
                });

        }
        function userGroupContenReload(groupItem) {

            var html = '<section id="device-content-1"  class="device-content">'+
            '<div class="top">'+
                '<div id="topContent" class="topContent">'+
                '<div class="row">'+
                '<div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">'+
                ' <h3 class="title"><span>'+groupItem.groupName+'</span>网关信息</h3>'+
            ' </div>'+
            '</div>'+
            '</div>'+
            ' </div>'+
            '<div class="row">'+
                '<div class="col-xs-12 ">'+
                '<div id="list-content" class="list-content">'+
                '</div>'+
                '</div>'+
                '</div>'+
                '</section>';

            $('#user-group').append(html);
//            {relCustomerDeviceGroupId}
            $.ajax({
                url: "${path}/freshair/tabDeviceFreshairsInGroup/"+groupItem.tabDeviceGroupId,
                type: "GET",
                data: { },
                dataType: "json",
                success: function (result) {
                    layer.alert('resultaaaaa:'+JSON.stringify(result));
                    if (result.code == 0) {
                        var deviceListIngroup = new Array();
                        var groupdeviceList = result.obj;
                        for (var i in groupdeviceList) {
                            var item = groupdeviceList[i];
                            var groupItem = {
                                "deviceSeriaNumber":item.deviceSeriaNumber,
                                    "deviceCategory": item.deviceCategory,
                                "ip": item.ip,
                                    "name": item.name,
                                "tabDeviceGroupId": item.tabDeviceGroupId,
                                    "state":item.state,
                                "createdDate": item.createdDate,
                                    "modifiedDate": item.modifiedDate,
                                "isDeleted": item.isDeleted,
                                    "id":item.tabDeviceFreshairId
                            };
                            if ($.inArray(groupItem, deviceListIngroup) == -1) {
                                deviceListIngroup.push(groupItem);
                                userGroupDeviceItemContenReload(groupItem);

                            }
                        }
                        $("#user-group").append('<div id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></div>');

                    } else {
                        layer.alert(result.error);
                        $("#user-group").append('<div id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></div>');
                    }
                },
                error: function (msg) {
                    layer.msg('error:'+msg.toString());
//                    $("#user-group").append('<div id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></div>');
                }
            });

        }

        function refreshData() {


            $.ajax({
                url: "${path}/deviceGroup/allRelCustomerDeviceGroups",
                type: "POST",
                data: {
                    tabCustomerId: customer.tabCustomerId
                },
                dataType: "json",
                success: function (result) {
                    deviceGroupArray = new  Array();
                    $("#user-group").empty();

                    if (result.code == 0) {

                        var groupList = result.obj;

                        for (var i in groupList) {
                            var item = groupList[i];
                            var groupItem = {
                                "tabDeviceGroupId": item.tabDeviceGroupId,
                                "groupName": item.groupName,
                                "isSendMsg": item.isSendMsg
                            };

                            layer.alert(groupItem.tabDeviceGroupId);
                            if ($.inArray(groupItem, deviceGroupArray) == -1 && groupItem != null) {
//                                layer.msg("itemaaa:"+groupItem.tabDeviceGroupId);
                                deviceGroupArray.push(groupItem);
                                userGroupContenReload(groupItem);

                            }
                        }

                        if (deviceGroupArray.length == 0){
                            $("#user-group").append('<div id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></div>');
                        }

                    } else {
                        layer.alert(result.error);
                        $("#user-group").append('<div id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></div>');
                    }
                },
                error: function (msg) {
                    layer.msg('error:'+msg);
                    layer.error(msg);
                    $("#user-group").append('<div id="gateWayId_nomore"><a href="#">没有更多数据了哦！</a></div>');
                }
            });

        }

    });
//        var success = swal("Good job!", "You clicked the button!", "success");


//        document.querySelector('.delete').onclick = function(){
//            swal({
//                    title: "确定操作吗？",
//                    text: "你确定要删除此设备吗？",
//                    type: "warning",
//                    showCancelButton: true,
//                    confirmButtonColor: '#DD6B55',
//                    confirmButtonText: '确定',
//                    cancelButtonText:'取消'
//                },
//                function(){
//                layer.msg("删除成功");
//            });
//
//            layer.confirm('确定要删除吗？',function () {
//                layer.msg("删除成功");
//            });


//        };

//        document.querySelector('.edit').onclick = function(){
//
//
//            var dialog = '<div id="updateDeviceDialog" class="box">' +
//                '<form role="form" >' +
//                '<div class="form-group">' ;
//                '<label for="name">设备类型</label>'
//                '<select id="deviceTypeCurrent"  class="form-control" >';

//            var index = 0;
//            for (var i in deviceTypes) {
//                dialog += '<option value="' + deviceTypes[i].id + '">' + deviceTypes[i].name + '</option>';
//                if (deviceTypes[i].id == device.deviceTypeId) {
//                    index = i;
//                }
//            }
//            dialog += '</select>';

//            dialog += '<div class="form-group">' +
//                '</div>' +
//                '<label for="name">设备名称</label>' +
//                '<input type="text" class="form-control" id="update_gatewayName" placeholder="请输入设备名称" value="" required>' +
//                '<label for="name">设备型号</label>' +
//                '<input type="text" class="form-control" id="update_gatewayNo" placeholder="请输入设备型号"  value="" required>' +
//                '</div>' +
//                '</form>' +
//                '</div>';
//
//            layer.confirm(dialog, {
//                title: "修改信息",
//                btn: ["更新"], //按钮
//                width: "100%"
//            }, function () {
//                layer.msg('更新成功');
//                $.ajax({
//                    url: "
<%--${path}/client/device?action=updateDeviceById",--%>
//                    type: "GET",
//                    data: {
//                        name: $("#update_gatewayName").val(),
//                        deviceId: device.id,
//                        deviceCategoryId: current_gateway.id,
//                        parentId: account.id,
//                        gatewaySerialNumber: $("#deviceTypeCurrent").val(),
//                        seriaNumber: $("#update_gatewayNo").val(),
//                        deviceId: device.gatewayDeviceID
//
//                    },
//                    dataType: "json",
//                    success: function (result) {
//                        //console.log(result);
//                        if (result.result == "success") {
//                            layer.msg("更新成功")
//                            reloadPageContent(current_gateway);
//                            refresh();
//                        } else {
//                            layer.alert(result.error);
//                        }
//                    },
//                    error: function () {
//                        layer.msg("程序繁忙，请稍后重试。！");
//                    }
//                });
//            });
//        };
//    });
</script>

</body>
</html>
