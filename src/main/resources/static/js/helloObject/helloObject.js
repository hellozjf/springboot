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
        var d = formToObject($('form'));
        console.debug(JSON.stringify(d));       // JSON.stringify是将JSON对象转字符串，JSON.parse是将字符串转JSON对象
        console.debug(basePath + "/helloObject/validate");

        // 运行ajax
        $.ajax({
            url: basePath + "/helloObject/validate",
            data: d,        // 这里提交的是JSONObject
            type: "post",
            success: function(data) {       // 这里接收到的是JSONObject
                console.debug(JSON.stringify(data));
                if (data.code == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        });
    });
});