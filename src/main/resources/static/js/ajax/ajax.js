function ajaxSendFile() {

    // 首先获取<input type="file">
    var file = $('#file');
    if(file.value == "") {
        alert('file不能为空');
    }

    // 然后取出该控件下面的文件数
    var files = $('#file').prop('files');
    if (files.length == 0) {
        alert('请选择文件');
        return false;
    } else {
        var reader = new FileReader();
        reader.onload = function(e) {
            var data = e.target.result;
            console.debug(data);

            // 我们将在这里进行ajax发送文件的操作
            var formData = new FormData($('#uploadForm')[0]);
            $.ajax({
                url: contextRoot + "/ajax/uploadFile",
                data: formData,
                type: 'POST',
                async: false,
                contentType: false,
                enctype: 'multipart/form-data',
                dataType: "json",
                processData: false,
                success: function (response) {
                    console.debug(response);
                }
            });
        };
        reader.readAsText(files[0], "UTF-8");
    }
    return false;
}

const readFile = (file) => new Promise((resolve) => {
    var reader = new FileReader();
    reader.onload = function(e) {
        console.debug("onload");
        var data = e.target.result;
        resolve(data);
    };
    reader.readAsText(file, "UTF-8");
});

var ajaxSyncSendFile = (async () => {

    // 首先获取<input type="file">
    var file = $('#file');
    if(file.value == "") {
        alert('file不能为空');
    }

    // 然后取出该控件下面的文件数
    var files = $('#file').prop('files');
    if (files.length == 0) {
        alert('请选择文件');
        return false;
    } else {
        console.debug("begin");
        let result = await readFile(files[0]);
        console.debug("result=" + result);
    }
    console.debug("return false");
    return false;
})