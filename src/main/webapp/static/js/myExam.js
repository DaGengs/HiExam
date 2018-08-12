layui.use(['form','laydate','table','jquery'], function() {
    var form = layui.form, laydate = layui.laydate, table = layui.table, $ = layui.jquery
    table.render({
        elem: '#tbdata'
        , url: 'queryByStatus.do' //数据接口
        , page: true //开启分页
        , cols: [[ //表头
            {field: 'id', align: 'center', width: '5%', title: 'ID'}
            , {field: 'title', align: 'center', title: '试卷名称',}
            , {field: 'startTime', align: 'center', title: '开考时间',}
            , {field: 'endTime', align: 'center', title: '结束时间',}
            , {field: 'time', width:'6%', align: 'center', title: '考试时长',}
            ,{field: 'user_no', align: 'center', title: '创建人',}
            , {field: 'createTime',align: 'center', title: '创建时间',}
            , {
                field: 'status', align: 'center', width: '5%', title: '状态', templet: function (obj) {
                    var r;
                    switch (obj.status) {
                        //有效
                        case 1:
                            r = "<span class='layui-badge layui-bg-blue'>开放</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-red'>关闭</span>";
                            break;
                    }
                    return r;
                }
            }
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolbar'}
        ]]
    });

    table.on('tool(tbop)', function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        if (layEvent == 'begin') {
            layer.confirm('确认开始考试么?', function(index){
                location.href = "examPage.html?paper_id=" + encodeURI(JSON.stringify(data));
            });
        }
    });

});
