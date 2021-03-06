
<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>空气检测</title>
    <script type="text/javascript">
        var wxId = "${wxId}";

        $(function () {

        });

        //初始化页面
        $(document).ready(function () {

        });
    </script>
    <link rel="stylesheet" type="text/css" href="${path}/page/css/detail.css">
    <style>
        .row {
            margin: 0;
        }
        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6, .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
        }

        .current {
            color: #2BB4EA;
        }

    </style>
</head>
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
        <span>详情</span>
    </div>
    <div class="menu">
        <i class="return"></i>
    </div>
    <ul id="rightM" class="dropDown">
        <li id="yearAccount"><a href="#">本年</a></li>
        <li id="monthAccount"><a href="#">本月</a></li>
        <li id="todayAccount"><a href="#">今天</a></li>
    </ul>
</nav>


<!--内容区 -->
<section id="dev " class="device-content">
    <div class="row">
        <div class="col-xs-12 col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-8 col-lg-offset-2 ">
            <h3 class="title"><span id="chartTitle">图表</span>数据统计内容区</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12 ">
            <div id="chart" class="charts">
                <canvas style="width: 100%;height: 100%"></canvas>
            </div>
        </div><!--col-xs-12 -->
    </div><!--row -->
    <div class="row">
        <div class="col-xs-12 col-sm-12">
            <p class="title">最高值：<span id="max_data">--</span></p>
            <p class="title">最低值：<span id="min_data">--</span></p>
            <p class="title">平均值：<span id="avg_data">--</span></p>
        </div>
    </div>
</section>

<script type="text/javascript" src='${path}/page/js/third/echarts.min.js' charset="utf8"></script>
<script type="text/javascript" src='${path}/page/js/index.js' charset="utf8"></script>

<script>
    $(document).ready(function () {
        function fomatFloat(src,pos){
            return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
        }

        $('.dropDown').mouseleave(function () {
            $('.dropDown').slideUp("slow", function () {
                $(this).fadeOut(2000);
            });
        });
        $("#chartTitle").html("今天");

        var deviceD = {
            tabDeviceFreshairId: "${tabDeviceFreshairId}",
            code: "${code}",
            type: "day"
        };

        var dom = document.getElementById("chart");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        var base = +new Date();
        var oneDay = 24 * 3600 * 1000;
        var oneHour= 3600 * 1000;
        var oneMinute= 60*1000;
        var oneMonth= 30 * 24 * 3600 * 1000;
        var oneYear=  12 * 30 * 24 * 3600 * 1000;

        var unit = "";
        var date = [];

        var data = [];

        var now = new Date().getTime();
        now -= oneMonth;


        function add0(m){return m<10?'0'+m:m }
        function format(timestamp,type){
            var time = new Date(timestamp);

            var year = time.getFullYear();
            var month = time.getMonth()+1;
            var date = time.getDate();
            var hours = time.getHours();
            var minutes = time.getMinutes();
            if (type == "year"){             //年
                //return year+'/'+add0(month)+'/'+add0(date);
                return add0(month)+'/'+add0(date);
            }else if (type == "mounth") { //月
                //return year+'/'+add0(month)+'/'+add0(date)+' '+add0(hours);
                return add0(date)+'日'+add0(hours)+'时';
            }                  //2 小时
            return add0(hours)+':'+add0(minutes);
        }

        function setOption() {
            option = {
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: date
                },
                yAxis: {
                    name:unit,
//                boundaryGap: [0, '20%'],
                    type: 'value'
                },
                series: [
                    {
                        name:'',
                        type:'line',
                        smooth:true,
                        symbol: 'none',
                        stack: 'a',
                        areaStyle: {
                            normal: {}
                        },
                        data: data
                    }
                ]
            };
        }

        function accountMsgs(deviceD) {
            $.ajax({
                url: "${path}/freshair/getDeviceDataMap",
                type: "POST",
                data: {
                    "tabDeviceFreshairId": deviceD.tabDeviceFreshairId,
                    "code": deviceD.code,
                    "type": deviceD.type
                },
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        var codeData = result.obj;
                        if ((codeData.data == null) || (0 == codeData.data.length)) {
                            date.push("当前无数据");
                            data.push(0);
                            return;
                        }
                        unit = codeData.name+" "+codeData.unit;
                        data = codeData.data;
                        if(deviceD.code == "voc"){
                            $("#max_data").html(fomatFloat(codeData.max, 4) + " " + codeData.unit);
                            $("#min_data").html(fomatFloat(codeData.min, 4) + " " + codeData.unit);
                            $("#avg_data").html(fomatFloat(codeData.avg, 4) + " " + codeData.unit);
                        } else {
                            $("#max_data").html(fomatFloat(codeData.max, 2) + " " + codeData.unit);
                            $("#min_data").html(fomatFloat(codeData.min, 2) + " " + codeData.unit);
                            $("#avg_data").html(fomatFloat(codeData.avg, 2) + " " + codeData.unit);
                        }
                        for (var i in codeData.recordTime) {
                            var  item = codeData.recordTime[i];
                            var newDateStr = format(item,deviceD.type);
                            date.push(newDateStr);
                        }
                        setOption();
                        if (option && typeof option === "object") {
                           myChart.clear();
                           // 显示折线图。
                           myChart.setOption(option, true);
                        }
                        //显示最高最低和平均值

                    } else {
                        layer.alert(result.errorMsg);
                    }
                },
                error: function () {
                    layer.msg("请求失败！");
                }
            });
        }
        //界面进入数据加载
        accountMsgs(deviceD);
        $(window).resize(function () {
            myChart.resize(); // 使图表适应
        });

        $("#todayAccount").click(function () {

            $("#chartTitle").html("今天");

            deviceD.type = "day";
            date = [];
            data = [];
            accountMsgs(deviceD);
        });
        $("#monthAccount").click(function () {
            deviceD.type = "mounth";
            date = [];
            data = [];
            $("#chartTitle").html("本月");
            accountMsgs(deviceD);
        });
        $("#yearAccount").click(function () {
            deviceD.type = "year";
            date = [];
            data = [];
            $("#chartTitle").html("本年");
            accountMsgs(deviceD);
        });

        $(".menuleft").click(function () {
            window.history.back();
        });
    });

</script>

</body>
</html>
