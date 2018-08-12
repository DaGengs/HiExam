layui.use(['form', 'table','jquery'], function() {
    var form = layui.form, table = layui.table, $ = layui.jquery;

    table.render({
        elem: '#tbdata'
        , url: 'TestListqueryBypage.do' //数据接口
        , page: true //开启分页
        , cols: [[ //表头
            {field: 'id', align: 'center', width: '5%', title: 'ID'}
            ,{field: 'user_no', align: 'center', title: '考生号',}
            , {field: 'test_title', align: 'center', title: '试卷名称',}
            , {field: 'createTime', align: 'center', title: '创建时间',}
            , {field: 'score', align: 'center', width: '5%', title: '分数',}
            , {field: 'commitTime', align: 'center', title: '交卷时间',}
            , {field: 'time', align: 'center', width: '5%', title: '耗时',}
            , {
                field: 'status', align: 'center', width: '5%', title: '状态', templet: function (obj) {
                    var r;
                    switch (obj.status) {
                        //有效
                        case 1:
                            r = "<span class='layui-badge layui-bg-red'>未完成</span>";
                            break;
                        case 2:
                            r = "<span class='layui-badge layui-bg-blue'>已完成</span>";
                            break;
                    }
                    return r;
                }
            }
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#toolbar'}
        ]]
    });

    initData();
    function initData() {
        $.ajax({
            url: "getSubject.do",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#subj_id").append("<option value='"+data[i].subj_id+"'>"+data[i].subj_name+"</option>");
                }
                form.render("select");
            }
        })
    };

    form.on('submit(add)', function(data){
        $.ajax({
            url: "createTest.do",
            type: "post",
            data: {
                "subj_id": $("#subj_id").val(),
                "que_type": $("#que_type").val(),
                "que_level": $("#que_level").val()
            },
            success: function (data) {
                console.log(data);
                localStorage.setItem("pageData", JSON.stringify(data));
                location.href="testPage.html";
            }
        })
        return false;
    });
});

