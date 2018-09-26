function formToObject(form) {
    var o = {};
    var fs = form.serializeArray();
    $.each(fs, function () {
        o[this.name] = this.value;
    });
    return o;
}

$(function () {
    $('#submit').click(function () {

        // 显示form里面的参数
        var d = formToObject($('form'));        // 这个是JSONObject
        var dd = [];                            // 这个是JSONArray
        dd.push(d);
        console.debug(JSON.stringify(d));       // JSON.stringify是将JSON对象转字符串，JSON.parse是将字符串转JSON对象
        console.debug(contextRoot + "/helloObject/validate");

        // 运行ajax，发送一个form
        var bSendForm = false;
        $.ajax({
            url: contextRoot + "/helloObject/validate",
            data: d,        // 这里提交的是JSONObject
            type: "post",
            async: false,
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",
            success: function(data) {       // 这里接收到的是JSONObject
                console.debug(JSON.stringify(data));
                if (data.code == 0) {
                    bSendForm = true;
                }
            }
        });

        // 运行ajax，发送一个JSON数组
        var bSendArray = false;
        $.ajax({
            url: contextRoot + "/helloObject/validateArray",
            data: JSON.stringify(dd),        // 这里提交的是JSONArray
            type: "post",
            async: false,
            contentType: "text/plain",
            dataType: "json",
            success: function(data) {       // 这里接收到的是JSONObject
                console.debug(JSON.stringify(data));
                if (data.code == 0) {
                    bSendArray = true;
                }
            }
        });

        console.debug("bSendForm=" + bSendForm + " bSendArray=" + bSendArray);
        return false;
        // if (bSendForm && bSendArray) {
        //     return true;
        // } else {
        //     return false;
        // }
    });
});