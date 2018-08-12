
layui.use(['form', 'upload'], function(){

    var form = layui.form,layer = layui.layer, upload = layui.upload;

    var uploadInst = upload.render({
        elem : '#chooseFile' // 绑定元素
        ,url : 'studentAddBatch.do' // 上传接口
        ,accept: 'file'
        ,auto: false
        ,bindAction: '#upload' //指向一个按钮触发上传
        ,choose: function(obj){
            var files = obj.pushFile();
            obj.preview(function(index, file, result){
                $("#fileName").html(file.name);
            });
        }
        ,before: function(obj) {
            this.data={
                "class_no": $("#class_no").val(),
                "status": $("#status").val()
            }
        }
        ,done : function(res) {
            // 上传完毕回调
            if (res.code == 0) {
                layer.msg(res.message, {icon: 6});
            } else {
                layer.msg(res.message, {icon: 5});
            }
        },
        error : function() {
            // 请求异常回调
        }
    });

    initData();
    function initData() {
        $.ajax({
            url: "classesQueryAll.do",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#class_no").append("<option value='"+data[i].class_id+"'>"+data[i].class_name+"</option>");
                }
                form.render("select");
            }
        })
    };

});
