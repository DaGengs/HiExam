layui.use(['form', 'table'], function(){
    var form = layui.form, table = layui.table;

    table.render({
        elem: '#tbdata'
        ,url: 'roleQueryByPage.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'role_id', align:'center',title: '角色ID', fixed: 'left'}
            ,{field: 'role_name', align:'center',title: '角色名称',}
            ,{field: 'menu_name', align:'center',title: '权限名称', templet: '#menu_nameTpl'}
            // ,{field: 'menu_p_name', title: '上级菜单',}
            ,{field: 'menu_url', align:'center',title: '菜单路径',templet: '#menu_urlTpl'}
            ,{field: 'menu_icon', align:'center',title: '菜单图标', templet: '#menu_iconTpl'}
            ,{fixed: 'right', width:150,align:'center',title: '操作', align:'center', toolbar: '#toolbar'}
        ]]
    });

    table.on('tool(tbop)', function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        console.log(data);
        if (layEvent == 'addMenu') {
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
                content: $("#menuModel")
                ,
                yes: function (index, layero) {
                    var menu_id=$("input[name='menu']");
                    var menu_ids = [];
                    for(var i in menu_id){
                        if(menu_id[i].checked)
                            menu_ids.push(menu_id[i].value);
                    }
                    var ids = {role_id: data.role_id, menu_ids: menu_ids};
                    $.ajax({
                        url: "updateMyMenu.do",
                        type: "post",
                        data:ids,
                        success: function (data) {
                            if (data.code == 0) {
                                layer.msg(data.message);
                            } else {
                                layer.msg(data.message);
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
                    url: "deleteUser.do",
                    data:"user_id="+data.user_id,
                    success: function(data){
                        if(data.code==1){
                            obj.del();//删除表格中的对应行数据
                            layer.close(index);
                            layer.msg(data.message, {icon: 5});
                        }else{
                            layer.msg(data.message, {icon: 6});
                        }
                    }
                });
            });
        }
    });

    var active = {
        addRole: function () {
            //示范一个公告层
            layer.open({
                type: 1
                ,
                title: false //不显示标题栏
                ,
                closeBtn: 2
                ,
                area: '360px'
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
                    var role_name = $("#role_name").val();

                    if (role_name == "") {
                        layer.msg("请输入角色名称");
                        return false;
                    }
                    $.ajax({
                        url: "saveRole.do",
                        type: "post",
                        data: {
                            role_name: role_name,
                        },
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
    });
    initData();
    function initData() {
        $.ajax({
            url: "menuQueryAll.do",
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    $("#menuList").append("<input type='checkbox' name='menu' value='"+data[i].menu_id+"' title='"+data[i].menu_name+"' lay-skin='primary'>");
                }
                form.render("checkbox");
            }
        })
    }
});
