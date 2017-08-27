<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html lang="en">
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${path}/page/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="${path}/page/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${path}/page/css/adddevice.css" />
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
        <span>扫描设备</span>
    </div>

</nav>

<!--内容区 -->

<section id="device-content" >
    <div class="row">
        <div class="col-xs-12 ">
            <div id="device_list" class="list-content">
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
    </div>
    </div>

    </div>
</section>

<section id="serach-content-bind">

    <div class="row">
        <div class="col-xs-12 ">
            <div class="list-content">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="serach-menu">
                        绑定设备
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
<section id="serach-content">

    <div class="row">
        <div class="col-xs-12 ">
            <div id="" class="list-content">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="serach-menu">
                        搜索设备
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>


<script src='${path}/page/js/jquery.js'></script>
<script src="${path}/page/js/third/layer/layer.js"></script>

<script>
    $(document).ready(function () {
        $(".menuleft").click(function () {
            window.history.back();
        });

        var customer = {
            "tabCustomerId": '${customer.tabCustomerId}',
            "mobelPhone": '${customer.mobelPhone}',
            "wxId": '${customer.wxId}',
            "nickName": '${customer.nickName}',
            "birthday": '${customer.birthday}',
            "sex": '${customer.sex}',
            "token": '${customer.token}'
        };

        var devList = new Array(); //全局数组，存储扫描到的设备信息
        var deviceGroupList = new Array(); //全局数组，存储扫描到的设备信息

        function addListItem(device) {
            var html = '<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">'+
            ' <div id="listItem_'+device.tabDeviceFreshairId+'" class="device-item">'+
            ' <img src="${path}/page/img/addservice/chandelier.png">'+
            ' <div class="rightContent">'+
            ' <span class="title">'+device.deviceCategory+'</span>'+
            ' <span>'+device.deviceSeriaNumber+'</span>'+
            ' <span>'+new Date(device.createdDate).toLocaleDateString()+'</span>'+
            ' </div>'+
            ' </div>'+
            ' </div>';

            $('#device_list').append(html);

        }
        
        
        function postDevicesWithCategory() {
            var dialog = '<div id="addDeviceDialog" class="box">' +
                '<form role="form" >' +
                '<div class="form-group">' +
                '<label for="name">分组</label>' +
                '<select id="deviceType"  class="form-control" >';

            for (var i in deviceGroupList) {
                dialog += '<option value="' + deviceGroupList[i].uid + '">' + deviceGroupList[i].groupName + '</option>';
            }
            dialog += '</select>';
//            if (deviceGroupList.length <= 0) {
//                dialog += '<div class="click-title">当前无设备类型，<span id="addDeviceType">立即添加?</span></div>';
//            }

            dialog += '<div class="form-group">' +
                '</div>' +
//                '<label for="name">设备名称</label>' +
//                '<input type="text" class="form-control" id="add_gatewayName" placeholder="请输入设备名称" required>' +
//                '<label for="name">设备型号</label>' +
//                '<input type="text" class="form-control" id="add_gatewayNo" placeholder="请输入设备型号" required>' +
                '</div>' +
                '</form>' +
                '</div>';

            layer.confirm(dialog, {
                title: "绑定设备",
                btn: ["提交"], //按钮
                width: "100%"
            }, function () {

                var  tabDeviceGroupId=$("#deviceType").val();
                var  deviceSeriaNumberList = '';
                for (var i in devList){
                    deviceSeriaNumberList += devList[i].deviceSeriaNumber+',';
                }

                $.ajax({
                    url: "${path}/deviceGroup/groupBoundDevice",
                    type: "POST",
                    data: {
                        tabCustomerId: customer.tabCustomerId,
                        tabDeviceGroupId: tabDeviceGroupId,
                        deviceSeriaNumberList: deviceSeriaNumberList
                    },
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);
//                        layer.alert("result"+result.code);
                        if (result.code == 0){
//                            layer.alert(result.obj.tabDeviceGroupId);
                            layer.alert("绑定设备成功!");
                         }else{
                        layer.alert("绑定设备失败！");
            }
                    },
                    error: function (error) {
                        layer.msg("程序繁忙，请稍后重试。！"+error.toString());
                    }
                });
            });
        }
        
        function createNewCategory() {
            var dialog = '<div id="addDeviceDialog" class="box">' +
                '<form role="form" >' +
                '<div class="form-group">' ;


            dialog += '<div class="form-group">' +
                '</div>' +
                '<label for="name">分组名称</label>' +
                '<input type="text" class="form-control" id="category_gatewayName" placeholder="请输入设备名称" required>' +
                '<label for="name">分组地址</label>' +
                '<input type="text" class="form-control" id="category_gatewayAddress" placeholder="请输入设备型号" required>' +
                '</div>' +
                '</form>' +
                '</div>';

            layer.confirm(dialog, {
                title: "绑定设备",
                btn: ["提交"], //按钮
                width: "100%"
            }, function () {
//                console.log('a:'+$("#category_gatewayName").val()+'-'+$("#category_gatewayAddress").val());
                layer.alert('hhh:'+$("#category_gatewayName").val());
                $.ajax({
                    url: "${path}/deviceGroup/customerAddNewGroup",
                    type: "POST",
                    data: {
                        tabCustomerId: customer.tabCustomerId,
                        groupName: $("#category_gatewayName").val(),
                        address: $("#category_gatewayAddress").val()
                    },
                    dataType: "json",
                    success: function (result) {
                        //console.log(result);

                        layer.alert('result'+result.code);

                        if (result.code == 0){
                            layer.alert(result.obj.tabDeviceGroupId);
                            var  tabDeviceGroupId=result.obj.tabDeviceGroupId;
                            var  deviceSeriaNumberList = '';
                            for (var i in devList){
                                deviceSeriaNumberList += devList[i].deviceSeriaNumber+',';
                            }

                            $.ajax({
                                url: "${path}/deviceGroup/groupBoundDevice",
                                type: "GET",
                                data: {
                                    tabCustomerId: customer.tabCustomerId,
                                    tabDeviceGroupId: tabDeviceGroupId,
                                    deviceSeriaNumberList: deviceSeriaNumberList
                                },
                                dataType: "json",
                                success: function (result) {
                                    //console.log(result);

                                    if (result.code == 0){
                                        layer.alert("绑定成功");


                                    }

                                },
                                error: function () {
                                    layer.msg("程序繁忙，请稍后重试。！");
                                }
                            });

                        }else{
                            layer.alert("绑定设备失败！");
                        }

                    },
                    error: function (error) {
                        layer.msg("程序繁忙，请稍后重试。！"+error.toString());
                    }
                });
            });
        }

        $('#serach-content').click(function () {
            var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2

            devList = new  Array();
            $('#device_list').empty();

            $.ajax({
                url: "${path}/deviceGroup/findDeviceByIp",
                type: "GET",
                data:{"tabCustomerId":customer.tabCustomerId},
                dataType: "json",
                success: function(result) {
                    layer.close(index);
                    if (result.code == 0) {
                        var re = result.obj;

                        var deviceList = re.deviceList;
                        var groupList = re.deviceGroupList;
//                        layer.alert('aaa:' + groupList.length);

                        if ((null != groupList) && (0 < groupList.length)) {

                            for (var j in groupList) {
                                var itema = groupList[j];

                                var deviceG = {
                                    "tabDeviceGroupId": itema.tabDeviceGroupId,
                                    "address": itema.address,
                                    "uid": itema.uid,
                                    "groupName": itema.groupName
                                };
                                deviceGroupList.push(deviceG);
                            }
                        }

                            if ((null != deviceList) && (0 < deviceList.length)) {
                            $('#device-content').css('display','block');
                            $('#serach-content-bind').css('display','block');
                            $('#serach-content-bind').click(function () {
                                if (deviceGroupList.length > 0) {
                                    postDevicesWithCategory()
                                }else {
                                    createNewCategory();
                                }

                            });

                            for (var i in deviceList) {
                                var item = deviceList[i];

                                var device = {
                                    "deviceCategory": item.deviceCategory,
                                    "createdDate": item.createdDate,
                                    "isDeleted": item.isDeleted,
                                    "ip": item.ip,
                                    "modifiedDate": item.modifiedDate,
                                    "deviceSeriaNumber": item.deviceSeriaNumber,
                                    "tabDeviceFreshairId": item.tabDeviceFreshairId,
                                    "tabDeviceGroupId": item.tabDeviceGroupId
                                };
                                devList.push(device);
                                addListItem(device);
                            }

                        }else {
                            layer.alert('未搜索到相关设备!');
                            }

                    }


                    console.log(JSON.stringify(result))
                },
                error: function(error) {
                    layer.close(index);
                    console.log("程序繁忙，请稍后重试。！" + error.errorMsg);
                }
            });

//                $.ajax({
//                    url: prefixUrl+'/deviceGroup/findDeviceByIp',
//                    type: "GET",
//                    data: {
//                        ip: currentIp
//                    },
//                    success: function (result) {
//                        layer.alert('result:'+result);
//                        layer.close(index);
//                    },
//                    error: function (error) {
//                        layer.close(index);
//                        layer.msg("程序繁忙，请稍后重试。！"+error.errorMsg);
//                    }
//                });




//            setTimeout(function(){
//                layer.close(index);
//
//                $('#device-content').css('display','block');
//
//                $('#serach-content-bind').css('display','block');
//                $('#serach-content-bind').click(function () {
//                    layer.msg('设备绑定成功!');
//                });
//            },1000);


        });

    });
</script>

</body>
</html>
