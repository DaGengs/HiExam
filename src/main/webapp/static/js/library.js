layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: 'subjectQueryByPage.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'subj_name', align:'center',title: '题库名称', fixed: 'left'}
            ,{field: 'tatol', align:'center',title: '试题数量',}
            ,{field: 'user_no', align:'center',title: '创建人',}
            ,{field: 'createTime', align:'center',title: '修改时间'}
            ,{field: 'status', align:'center',width:'5%',title: '状态',templet: function (obj) {
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
                    url: "subjectDelete.do",
                    data:"subj_id="+data.subj_id,
                    success: function(data){
                        if(data.code==0){
                            obj.del();//删除表格中的对应行数据
                            layer.close(index);
                            layer.msg(data.message, {icon: 6});
                            setInterval("location.href='libraryList.html'",2000);
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