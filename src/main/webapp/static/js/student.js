var tableIns;
layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    tableIns = table.render({
        elem: '#tbdata'
        ,url: 'studentQueryByPage.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'stu_no', align:'center',title: '考生号', fixed: 'left'}
            ,{field: 'stu_name', align:'center',title: '考生姓名',}
            ,{field: 'stu_idNumber', align:'center',title: '身份证号',}
            ,{field: 'stu_gender', align:'center',title: '考生性别'}
            ,{field: 'stu_phone', align:'center',title: '手机号'}
            ,{field: 'class_name', align:'center',title: '所属班级'}
            ,{field: 'status', width:'5%', align:'center',title: '状态',templet: function (obj) {
                    var r;
                    switch (obj.status) {
                        //有效
                        case 1:
                            r = "<span class='layui-badge layui-bg-blue'>有效</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-red'>无效</span>";
                            break;
                    }
                    return r;
                }}
            ,{fixed: 'right', width:150,title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });




    table.on('tool(tbop)', function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        console.log(data);
        $("#subj_name").val(data.subj_name);
        $("#info").val(data.info);
        if (layEvent == 'edit') {
            layer.open({
                type: 1
                ,
                title: false //不显示标题栏
                ,
                closeBtn: 2
                ,
                area: '500px'
                ,
                shade: 0.8
                ,
                id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,
                btn: ['提交','关闭']
                ,
                btnAlign: 'c'
                ,
                moveType: 1 //拖拽模式，0或者1
                ,
                content: $("#editModel")
                ,
                yes: function (index, layero) {
                    var subject = {
                        subj_name: $("#subj_name").val(),
                        info: $("#info").val()
                    }

                    $.ajax({
                        url: "subjectUpdate.do",
                        type: "post",
                        contentType : "application/json",
                        data: JSON.stringify(subject),
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.message, {icon: 6});
                            } else {
                                layer.msg(data.message, {icon: 5});
                            }
                        }
                    })
                },
                btn2: function(){
                    location.reload();
                }
            });
        } else if(layEvent == 'del'){ //删除
            layer.confirm('是否确认删除?', function(index){
                $.ajax({
                    url: "studentDelete.do",
                    data:"stu_no="+data.stu_no,
                    success: function(data){
                        if(data.code==0){
                            obj.del();//删除表格中的对应行数据
                            layer.close(index);
                            layer.msg(data.message, {icon: 6});
                        }else{
                            layer.msg(data.message, {icon: 5});
                        }
                    }
                });
            });
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
function searchData() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            class_no: $("#class_no").val()
            , status: $("#status").val()
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}