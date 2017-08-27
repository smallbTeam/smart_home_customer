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
        <span>绑定设备</span>
    </div>

</nav>

<!--内容区 -->

<section id="device-content" >
    <div class="row">
        <div class="col-xs-12 ">
            <div id="list-content" class="list-content">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="device-item">
                        <img src="${path}/page/img/addservice/aircleaner.png">
                        <div class="rightContent">
                            <span class="title">新风空调</span>
                            <span>美的xb-102128</span>
                            <span>2017-08-16</span>

                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="device-item">
                        <img src="${path}/page/img/addservice/heater.png">
                        <div class="rightContent">
                            <span class="title">热水器</span>
                            <span>美的xb-102128</span>
                            <span>2017-08-16</span>

                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 ">
                    <div class="device-item">
                        <img src="${path}/page/img/addservice/chandelier.png">
                        <div class="rightContent">
                            <span class="title">新风空调</span>
                            <span>美的xb-102128</span>
                            <span>2017-08-16</span>

                        </div>
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


<script src='js/jquery.js'></script>
<script src="third/layer-v3.0.3/layer/layer.js"></script>

<script>
    $(document).ready(function () {
        $(".menuleft").click(function () {
            window.history.back();
        });

        $('#serach-content').click(function () {
//            layer.msg('设备搜索中...');
            var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2

            setTimeout(function(){
                layer.close(index);
                $('.serach-menu').html('绑定设备');
                $('#serach-content').unbind('click');
                $('.serach-menu').click(function () {
                    layer.msg('设备绑定成功!');
                });
            },1000);


        });

    });
</script>

</body>
</html>
