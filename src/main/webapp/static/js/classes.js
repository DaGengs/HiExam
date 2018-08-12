layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: 'classesQueryByPage.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'class_id', align:'center',title: '班级编号'}
            ,{field: 'class_name', align:'center',title: '班级名称',}
            ,{field: 'number', align:'center',title: '班级人数',}
            ,{field: 'user_no', align:'center',title: '创建人',}
            ,{field: 'createTime', align:'center',title: '修改时间'}
            ,{field: 'status', width:'5%',align:'center',title: '状态',templet: function (obj) {
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
        $("#class_name").val(data.class_name);
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

                    $.ajax({
                        url: "classesUpdete.do",
                        type: "post",
                        data: {
                          "class_name": $("#class_name").val(),
                          "class_id": data.class_id
                        },
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
                    url: "classesDelete.do",
                    data:"class_id="+data.class_id,
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




    /*
        var active = {
            addUser: function () {
                //示范一个公告层
                layer.open({
                    type: 1
                    ,
                    title: false //不显示标题栏
                    ,
                    closeBtn: 2
                    ,
                    area: '360px;'
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
                    content: $("#addModel")
                    ,
                    yes: function (index, layero) {
                        var user = {
                            user_no : $("#user_no").val(),
                            user_password : $("#user_password").val(),
                            realname : $("#realname").val()
                        };
                        $.ajax({
                            url: "saveUser.do",
                            type: "post",
                            contentType : "application/json",
                            data: JSON.stringify(user),
                            success: function (data) {
                                if (data.code == 0) {
                                    layer.msg(data.message);
                                } else {
                                    layer.msg(data.message);
                                }
                            }
                        });
                    },
                    btn2: function(){
                        location.reload();
                    }
                });
            }
        }

        $('#add').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });*/
});