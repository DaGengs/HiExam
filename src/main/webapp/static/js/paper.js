layui.use(['form','laydate','table','jquery'], function(){
    var form = layui.form,  laydate = layui.laydate, table = layui.table, $ = layui.jquery;

    laydate.render({
        elem: '#startTime'
        ,type: 'datetime'
    });
    laydate.render({
        elem: '#endTime'
        ,type: 'datetime'
    });

    table.render({
        elem: '#tbdata'
        , url: 'queryByPage.do' //数据接口
        , page: true //开启分页
        , cols: [[ //表头
            {field: 'id', align: 'center', width: '5%', title: 'ID'}
            , {field: 'title', align: 'center', title: '试卷名称',}
            , {field: 'startTime', width:'12%',align: 'center', title: '开考时间',}
            , {field: 'endTime', width:'12%',align: 'center', title: '结束时间',}
            , {field: 'time', width:'6%', align: 'center', title: '考试时长',}
            ,{field: 'user_no',  width:'6%', align: 'center', title: '创建人',}
            , {field: 'createTime', width:'12%',align: 'center', title: '创建时间',}
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

    form.on('select(queryNum)', function(data){
        console.log(data.value); //得到被选中的值
        $.ajax({
            url: "getNumber.do",
            data: {subj_id: data.value},
            success: function (data) {
                console.log(data);
                var numData = "";
                $.each(data,function(i,val){
                    numData += ""+val.type+"共有:<span style='font-weight: bold;color: #0C0C0C'>"+val.count+"</span>道  ";
                })
                $("#number").prepend(numData).css('display','block');
            }
        })
    });

    form.on('submit(save)', function(data){
        var obj = data.field;
        console.log(data.field);
        var condition1 = {
            type: obj.que_type1,
            level: obj.que_level1,
            num: obj.num1,
            score: obj.score1
        };
        var condition2 = {
            type: obj.que_type2,
            level: obj.que_level2,
            num: obj.num2,
            score: obj.score2
        };
        var condition3 = {
            type: obj.que_type3,
            level: obj.que_level3,
            num: obj.num3,
            score: obj.score3
        };
        var condition4 = {
            type: obj.que_type4,
            level: obj.que_level4,
            num: obj.num4,
            score: obj.score4
        };
        var condition5 = {
            type: obj.que_type5,
            level: obj.que_level5,
            num: obj.num5,
            score: obj.score5
        };
        var conditions = [];
        conditions.push(condition1);
        conditions.push(condition2);
        conditions.push(condition3);
        conditions.push(condition4);
        conditions.push(condition5);

        var paperVo = {
            title: $("#title").val(),
            status: $("#status").val(),
            subj_id: $("#subj_id").val(),
            time: $("#time").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            conditionVos: conditions
        };

        $.ajax({
            url: "createPaper.do",
            type: "post",
            contentType:"application/json",
            data: JSON.stringify(paperVo),
            success: function (data) {
                if (data.code == 0) {
                    layer.msg(data.message, {icon: 6});
                    setInterval("location.href='paperList.html'",3000);
                } else {
                    layer.msg(data.message, {icon: 5});
                }
            }
        })
        return false;
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
});