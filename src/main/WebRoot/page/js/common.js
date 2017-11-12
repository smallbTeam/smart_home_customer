function isNotNull(id) {
    if ($(id).val() != "" && $(id).val() != null && $(id).val() != undefined) {
        return true;
    }
    return false;
}

function isNotNullOfStr(str) {
    if (str != "" && str != null && str != undefined) {
        return true;
    }
    return false;
}


/**
 *
 * @param content 判空内容
 * @param defword 空值返回值
 * @param str 非空时拼接串
 */
function removeempetconnect(content, defword, str) {
    content = ((content != undefined) && (content != null)
    && (content != "")) ? (str + "" + content) : defword;
    return content;
}

function removeempetconnect_after(content, defword, str) {
    content = ((content != undefined) && (content != null)
    && (content != "")) ? (content + "" + str ) : defword;
    return content;
}

//日期格式化
//调用格式:Date().format("yyyy-MM-dd hh:mm:ss")
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}


