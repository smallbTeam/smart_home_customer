<%--
  Created by IntelliJ IDEA.
  User: ligw
  Date: 2017/6/6
  Time: 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入页面变量配置--%>
<%@include file="/page/common/jsp/base.jsp" %>
<html>
<head>
    <%--引入基础设置--%>
    <%@include file="/page/common/jsp/baseInclude.jsp" %>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${path}/page/css/register.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/page/css/site.css">
    <link rel="stylesheet" id="cal_style" type="text/css" href="${path}/page/css/flatpickr.min.css">
    <script type="text/javascript" src="${path}/page/js/third/md5.js" charset="utf8"></script>
    <script type="text/javascript" src="${path}/page/js/common.js" charset="utf8"></script>
    <style>
        .row {
            margin: 0;
        }

        .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9,
        .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8,
        .col-md-9, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7,
        .col-sm-8, .col-sm-9, .col-xs-1, .col-xs-10, .col-xs-11, .col-xs-12, .col-xs-2, .col-xs-3, .col-xs-4, .col-xs-5, .col-xs-6,
        .col-xs-7, .col-xs-8, .col-xs-9 {
            padding: 0;
        }
    </style>
</head>
<body>
<div class="top-icon">
    <div class="logo">
        <img src="${path}/page/img/icon/logo.png"/>
    </div>
</div>

<canvas id="contentConvas">
</canvas>

<div class="reg-content container ">

    <div class="row">
        <div class="col-sm-8 col-sm-offset-2 col-xs-12 col-md-6 col-md-offset-3 col-lg-4 col-lg-offset-4">
            <div class="register-section">

                <div id="phone" class="row-item">
                    <img src="${path}/page/img/icon/phone.png" class=" icon pull-left"/>
                    <img src="${path}/page/img/visible.png" class=" remark pull-right"/>
                    <div class="row-center">
                        <input id="phoneNumber" type="text" class="" placeholder="手机号码">
                    </div>
                </div>

                <div id="validate" class="validate">
                    <img src="${path}/page/img/icon/validate.png" class="icon pull-left"/>
                    <button id="sendCode" class=" pull-right">发送验证码</button>
                    <div class="row-center">
                        <input id="validateCode" type="text" class="" placeholder="验证码">
                    </div>
                </div>
                <div class="row-item">
                    <img src="${path}/page/img/icon/nickname.png" class=" icon pull-left"/>
                    <img src="${path}/page/img/visible.png" class=" remark pull-right"/>
                    <div class="row-center">
                        <input id="nickName" type="text" class="" placeholder="姓名">
                    </div>
                </div>

                <div class="row-item">
                    <img src="${path}/page/img/icon/pwd.png" class=" icon pull-left"/>
                    <img src="${path}/page/img/visible.png" class=" remark pull-right"/>
                    <div class="row-center">
                        <input id="pwd" type="password" class="" placeholder="密码">
                    </div>
                </div>

                <div class="row-item">
                    <img src="${path}/page/img/icon/pwdagain.png" class=" icon pull-left"/>
                    <img src="${path}/page/img/visible.png" class=" remark pull-right"/>
                    <div class="row-center">
                        <input id="pwdAgain" type="password" class="" placeholder="确认密码">
                    </div>
                </div>
                <div class="row-item">
                    <img src="${path}/page/img/icon/gender.png" class=" icon pull-left"/>
                    <img src="${path}/page/img/visible.png" class=" remark pull-right"/>
                    <div class="gender row-center ">
                        <!--<input type="text" class="" placeholder="性别">-->
                        <select id="gender" class="">
                            <option value="1">男</option>
                            <option value="2">女</option>
                            <%--<option value="0">未知</option>--%>

                        </select>
                    </div>
                </div>
                <div class="row-item">
                    <img src="${path}/page/img/icon/birth.png" class=" icon pull-left"/>
                    <img src="${path}/page/img/visible.png" class=" remark pull-right"/>
                    <div class=" example  sec2 row-center " data-desc="A basic datepicker" id="basic">
                        <input id="birth" placeholder="出生日期.." class=""/>
                    </div>
                    <!--<input type="text" class="" placeholder="出生日期">-->
                </div>

                <div class=" btn-content">
                    <button id="regbtn">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src='${path}/page/js/index.js' charset="utf8"></script>
<script type="text/javascript" src='${path}/page/js/flatpickr.js' charset="utf8"></script>

<script type="text/javascript">
    var wxId = '${wxId}';
    $(document).ready(function () {
        //console.log(JSON.stringify(wxId));
        Flatpickr.l10n.weekdays.shorthand = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
        Flatpickr.l10n.months.longhand = ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'];

        document.getElementById('birth').flatpickr();

        var canvas = document.getElementById("contentConvas");
        var context = canvas.getContext("2d");

        context.strokeStyle = "#ffffff";
        context.fillStyle = "#ffffff";
        context.lineWidth = "1";

        context.beginPath();
        context.moveTo(0, 0);
        context.quadraticCurveTo(canvas.width / 4, 45, canvas.width * 0.6, 35);
        context.quadraticCurveTo(canvas.width * 0.8, 30, canvas.width, 50);
        context.lineTo(canvas.width, canvas.height);
        context.lineTo(0, canvas.height);
        context.lineTo(0, 0);
        context.scale(4, 4);
        context.closePath();
        //context.stroke();
        context.fill();

        var timer;
        var phoneReg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        var phoneValidate = false;

        function listenField(id, vai) {
            if (!vai.test($(id).val())) {
                //                $(id).
                $(id).parent().prev("img").attr("src", "${path}/page/img/icon/failed.png");
                $(id).val("");

                return false;
            } else {
                $(id).parent().prev("img").attr("src", "${path}/page/img/icon/success.png");
                return true;
            }
        }

        $("#phoneNumber").keydown(function () {
            if (event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
                $("#validateCode").focus();
            }
        });
        $("#validateCode").keydown(function () {
            if (event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
                $("#nickName").focus();
            }
        });
        $("#nickName").keydown(function () {
            if (event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
                $("#pwd").focus();
            }
        });
        $("#pwd").keydown(function () {
            if (event.keyCode == "13") {//判断如果按下的是回车键则执行下面的代码
                $("#pwdAgain").focus();
            }
        });
        function validatePWD() {
            var reg = /^[a-zA-z]\w{3,15}$/;
            if (listenField("#pwdAgain", reg) && listenField("#pwd", reg)) {
                if (($("#pwdAgain").val() == $("#pwd").val()) && isNotNull("#pwd")) {
                    $("#pwdAgain").parent().prev("img").attr("src", "${path}/page/img/icon/success.png");

                    return true;
                } else {
                    //layer.msg("val:" + $("#pwdAgain").val() + "；f：" + $("#pwd").val());
                    $("#pwdAgain").parent().prev("img").attr("src", "${path}/page/img/icon/failed.png");
                    $("#pwdAgain").val("");
                    $("#pwdAgain").attr('placeholder', "密码输入不一致");
                    return false;
                }
            } else {
                $("#pwdAgain").attr('placeholder', "请输入6-22位数字字母组合!");
            }

        }

        $("#phoneNumber").change(function () {
            //            var validate = ' <div id="validate" class="validate">'+
            //                '<img src="img/icon/validate.png" class=" icon pull-left"/>'+
            //                '<button class=" pull-right">发送验证码</button>'+
            //                '<div class="row-center">'+
            //                '<input type="text" class="" placeholder="验证码">'+
            //                '</div>'+
            //                '</div>';

            if (listenField("#phoneNumber", phoneReg)) {
                $('#validate').slideDown("slow");
            } else {
                $(this).attr('placeholder', "请输入正确的手机号码!");
            }

        });

        $("#nickName").change(function () {
            var res = /^[\S]{1,}$/;
            if (listenField("#nickName", res)) {
            } else {
                $(this).attr('placeholder', "请填写姓名!");
            }

        });

        $("#pwd").change(function () {
            var reg = /^[a-zA-z]\w{3,15}$/;
            //console.log("pwd:" + $("#pwdAgain").val() + ":pwd:" + $("#pwd").val());
            //            if (listenField("#pwd",reg)){
            if (isNotNull("#pwdAgain")) {
                //                    //console.log("hihi");
                validatePWD();
            } else {
                if (listenField("#pwd", reg)) {

                } else {
                    $(this).attr('placeholder', "请输入6-22位数字字母组合!");
                }
            }
            //            }else{
            //                $(this).attr('placeholder',"请输入6-22位数字字母组合!");
            //            }

        });

        $("#pwdAgain").change(function () {
            var reg = /^[a-zA-z]\w{3,15}$/;
            //            if (listenField("#pwdAgain",reg)){
            if (isNotNull("#pwd")) {
                //                    //console.log("hello");
                validatePWD();
            } else {
                if (listenField("#pwdAgain", reg)) {

                } else {
                    $(this).attr('placeholder', "请输入6-22位数字字母组合!");
                }
            }
            //            }else{
            //                $(this).attr('placeholder',"请输入6-22位数字字母组合!");
            //            }

        });

        $("#sendCode").click(function () {
            if (!listenField("#phoneNumber", phoneReg)) {
                //                //console.log("false");

                return;
            } else {
                //                //console.log("sendCode");
            }

            var mobelPhone = $("#phoneNumber").val();
            //发送验证码请求
            var timestamp = Date.parse(new Date());
            <%--var url = "${path}/verificationMsg?service=sendMsg&mobelPhone=" + mobelPhone + "&timeStamp=" + timestamp;--%>
            $.ajax({
                url: "${path}/shortMessage/sendMsg",
                type: "GET",
                data: {
                    "mobelPhone": mobelPhone,
                },
                dataType: "json",
                success: function (result) {
                    console.log(JSON.stringify(result));
                    if (result.code == 0) {
                        var countdown = 60;
                        var _this = $(this);
                        $("#sendCode").attr("disabled", "true");
                        $("#sendCode").text(countdown + "秒后重发");
                        $("#phoneNumber").attr("disabled", "true");

                        timer = setInterval(function () {
                            if (countdown - 0 > 1) {
                                --countdown;
                                $("#sendCode").text(countdown + "秒后重新获取");
                            } else {
                                clearInterval(timer);
                                $("#sendCode").removeAttr("disabled");
                                $("#phoneNumber").removeAttr("disabled");
                                $("#sendCode").text("重新发送验证码");
                            }
                        }, 1000);
                    } else {
                        layer.msg(result.errorMsg);
                    }
                },
                error: function () {
                    layer.msg("验证失败");
                }
            });
        });

        function validateWithCode() {
            //console.log("validateCode");
            if (!listenField("#phoneNumber", phoneReg)) {
                return;
            }

            if (!isNotNull("#validateCode")) {
                //                //console.log("false");
                return;
            }

            //console.log("[" + validateUrl + "]");
            $.ajax({
                url: "${path}/shortMessage/veridateMsg",
                type: "POST",
                data: {
                    "mobelPhone": $("#phoneNumber").val(),
                    "veridateMsg": $("#validateCode").val()
                },
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        if (result.obj) {
                            $('#validate').slideUp("slow");
                            phoneValidate = true;
                        } else {
                            phoneValidate = false;
                            layer.msg("验证失败!");
                            $('#validatecode').val("");
                            $('#validatecode').attr("placeholder", "验证码错误！");
                            clearInterval(timer);
                            $("#sendCode").removeAttr("disabled");
                            $("#phoneNumber").removeAttr("disabled");
                            $("#sendCode").text("重新发送验证码");
                        }
                    } else {
                        layer.msg(result.errorMsg);
                    }
                },
                error: function () {
                    layer.msg("验证失败");
                }
            });
        }

        //        $("#validateCode").onmouseout(function () {
        //            validateWithCode();
        //        });

        $("#validateCode").change(function () {
            validateWithCode();
        });

        $("#regbtn").click(function () {
            //验证手机号是否已存在
            $.ajax({
                url: "${path}/customer/accountIsExit/" + $("#phoneNumber").val(),
                type: "GET",
                data: {},
                dataType: "json",
                success: function (result) {
                    if (result.code == 0) {
                        var obj = result.obj;
                        if (!obj.isExit) {
                            var sex = $("#gender").val();
                            var str = $("#birth").val(); // 日期字符串
                            str = str.replace(/-/g, '/'); // 将-替换成/，因为下面这个构造函数只支持/分隔的日期字符串
                            var birthday = new Date(str).getTime();
                            if (wxId == null || wxId == undefined || wxId == '') {
                                wxId = "";
                            }
                            $.ajax({
                                url: "${path}/customer/tabCustomer",
                                type: "POST",
                                data: {
                                    "mobelPhone": $("#phoneNumber").val(),
                                    "password": hex_md5($("#pwd").val()),
                                    "wxId": wxId,
                                    "nickName":  $("#nickName").val(),
                                    "birthday": "2016-11-10 12",
                                    "sex": sex
                                },
                                dataType:"json",
                                success:function (result) {
                                    console.log(JSON.stringify(result));
                                    if (result.code == 0) {
                                        //请求成功
                                        window.location.href = "${path}/customer/index?mobelPhone=" + result.obj;
                                        layer.closeAll();
                                    } else {
                                        //请求失败
                                        layer.msg("注册失败！");
                                    }
                                },
                                error: function () {
                                    layer.msg("注册失败!");
                                }
                            });
                        } else {
                            layer.msg("用户已存在");
                        }
                    } else {
                        layer.msg("系统繁忙，请稍后重试");
                    }
                },
                error: function () {
                    layer.msg("验证失败");
                }
            });
        });
    });
</script>

</body>
</html>
