layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: 'teacherQueryByPage.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'tch_no', align:'center',title: '教师工号', fixed: 'left'}
            ,{field: 'tch_name', align:'center',title: '教师姓名',}
            ,{field: 'tch_idNumber', align:'center',title: '身份证号',}
            ,{field: 'tch_gender', align:'center',title: '教师性别'}
            ,{field: 'tch_phone', align:'center',title: '手机号'}
            ,{field: 'class_name', align:'center',title: '所属班级'}
            ,{field: 'status',width:'5%',align:'center', title: '状态',templet: function (obj) {
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
                    url: "teacherDelete.do",
                    data:"tch_no="+data.tch_no,
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

});
