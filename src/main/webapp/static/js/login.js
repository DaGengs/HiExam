layui.use(['layer', 'form'], function () {
    var form = layui.form, $ = layui.jquery;

    $('#forgot').on('click', function () {
        layer.msg('请联系管理员.');
    });

    form.on('submit(login)', function (data) {
        $.ajax({
            url: "login.do",
            type: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data.field),
            success: function (data) {
                console.log(data);
                if (data.code == 0) {
                    location.href = "index.html";
                } else {
                    layer.msg(data.message);
                }
            }
        });
        return false;
    });
});