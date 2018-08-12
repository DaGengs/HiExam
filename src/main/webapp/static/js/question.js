layui.use(['form', 'table','jquery'], function() {
    var form = layui.form, table = layui.table, $=layui.jquery;
    initSingle();
    form.render();
    form.on('select(que_type)', function(data){
        if (data.value == "单选") {
            initSingle();
            form.render();
        } else if (data.value == "多选") {
            initMultiple();
            form.render();
        } else if (data.value == "判断") {
            initJudgment();
            form.render();
        } else if (data.value == "填空") {
            initBlank();
        } else if (data.value == "简答") {
            initEasy();
        }
    });

    $(document).on('click','#addSingle',function(){
        var options = ['A', 'B', 'C', 'D', 'E', 'F'];
        var index = $("#single").find("span").length;
        if (index == options.length) {
            $("#addSingle").attr(disabled);
        }
        for (var i = 0; i < options.length; i++) {
            var str = "";
            str += "<div class='layui-row' style='margin-bottom: 10px'>";
            str += "<span>选项"+options[index]+"<input type='radio' name='que_RightAnswer' value='"+options[index]+"'></span>";
            str += "<div class='layui-input-inline' style='width: 500px;margin-left: -25px'>";
            str += "<textarea name='que_Answer"+options[index]+"'  class='layui-textarea'></textarea>";
            str += "</div>";
            str += "<a onclick='$(this).parent().remove()'><i class='layui-icon' style='color: red;font-size: 25px;'> &#xe640;</i></a>";
            str += "</div>";
        }
        $("#single").append(str);
        form.render();
    });

    $(document).on('click','#addMultiple',function(){
        var options = ['A', 'B', 'C', 'D', 'E', 'F'];
        var index = $("#multiple").find("span").length;
        if (index == options.length) {
            $("#addMultiple").attr(disabled);
        }
        for (var i = 0; i < options.length; i++) {
            var str = "";
            str += "<div class='layui-row' style='margin-bottom: 10px'>";
            str += "<span>选项"+options[index]+"<input type='checkbox' name='que_RightAnswer' value='"+options[index]+"' lay-skin='primary'></span>";
            str += "<div class='layui-input-inline' style='width: 500px;margin-left: 3px'>";
            str += "<textarea name='que_Answer"+options[index]+"' class='layui-textarea'></textarea>";
            str += "</div>";
            str += "<a onclick='$(this).parent().remove()'><i class='layui-icon' style='color: red;font-size: 25px;'> &#xe640;</i></a>";
            str += "</div>";
        }
        $("#multiple").append(str);
        form.render();
    });

    $(document).on('click','#addBlank',function(){
        var options = ['A', 'B', 'C', 'D', 'E', 'F'];
        var index = $("#blank").find("span").length;
        if (index == options.length) {
            $("#addBlank").attr(disabled);
        }
        for (var i = 0; i < options.length; i++) {
            var str = "";
            str += "<div class='layui-row' style='margin-bottom: 10px'>";
            str += "<span>答案"+(index+1)+":</span>";
            str += "<div class='layui-input-inline' style='width: 500px;margin-left: 10px'>";
            str += "<input type='text' name='que_Answer"+options[index]+"' lay-verify='required'  autocomplete='off' class='layui-input'>";
            str += "</div>";
            str += "<a onclick='$(this).parent().remove()'><i class='layui-icon' style='color: red;font-size: 25px;'> &#xe640;</i></a>";
            str += "</div>";
        }
        $("#blank").append(str);
        form.render();
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

    table.render({
        elem: '#tbdata'
        ,url: 'questionQueryByPage.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'subj_name', align:'center', width:'15%', title: '所属题库',}
            ,{field: 'que_type', align:'center', width:'6%', title: '试题类型',}
            ,{field: 'que_level', align:'center', width:'6%', title: '试题难度',}
            ,{field: 'status', align:'center', width:'6%', title: '试题状态' ,templet: function (obj) {
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
    }}
            ,{field: 'que_title', align:'center', width:'39%', title: '试题题干'}
            ,{field: 'user_no', align:'center', width: '8%', title: '创建人'}
            ,{field: 'createTime', align:'center', width: '10%', title: '修改时间'}
            ,{fixed: 'right', width: '10%',title: '操作', align:'center', toolbar: '#toolbar'}
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
                    url: "questionDelete.do",
                    data:"que_id="+data.que_id,
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

    form.on('submit(add)', function(data){
        var question = data.field;
        console.log(question);
        $.ajax({
            url: "questionAdd.do",
            type: "post",
            contentType : "application/json",
            data: JSON.stringify(question),
            success: function (data) {
                if (data.code == 0) {
                    layer.msg(data.message, {icon: 6});
                } else {
                    layer.msg(data.message, {icon: 5});
                }
            }
        })
        return false;
    });

});

function initSingle() {
    var options = ['A', 'B', 'C', 'D'];
    var single_str = "";
    single_str += "<div style='margin-left: 50px'>";
    single_str += "<div><button class='layui-btn layui-btn-sm layui-btn-normal' type='button' id='addSingle'>添加选项</button></div>";
    single_str += "<hr style='width: 800px'/>";
    single_str += " <div id='single'>";
    for (var i = 0; i < options.length; i++) {
        single_str += " <div class='layui-row' style='margin-bottom: 10px'>";
        single_str += "<span>选项"+options[i]+"<input type='radio' name='que_RightAnswer'value='"+options[i]+"'></span>";
        single_str += "<div class='layui-input-inline' style='width: 500px;margin-left: -25px'>";
        single_str += "<textarea name='que_Answer"+options[i]+"'  class='layui-textarea'></textarea>";
        single_str += "</div>";
        single_str += "<a onclick='$(this).parent().remove()'><i class='layui-icon' style='color: red;font-size: 25px;'> &#xe640;</i></a>";
        single_str += "</div>";
    }
        single_str += "</div>";
        single_str += "</div>";
    $("#options").html(single_str);
}

function initMultiple() {
    var options = ['A', 'B', 'C', 'D'];
    var multiple_str = "";
    multiple_str += "<div style='margin-left: 50px'>";
    multiple_str += "<div><button class='layui-btn layui-btn-sm layui-btn-normal' type='button' id='addMultiple'>添加选项</button></div>";
    multiple_str += "<hr style='width: 800px'/>";
    multiple_str += " <div id='multiple'>";
    for (var i = 0; i < options.length; i++) {
        multiple_str += " <div class='layui-row'>";
        multiple_str += "<span>选项"+options[i]+"<input type='checkbox' name='que_RightAnswer' value='"+options[i]+"' lay-skin='primary'></span>";
        multiple_str += "<div class='layui-input-inline' style='width: 500px;margin-left: 3px'>";
        multiple_str += "<textarea name='que_Answer"+options[i]+"' class='layui-textarea'></textarea>";
        multiple_str += "</div>";
        multiple_str += "<a onclick='$(this).parent().remove()'><i class='layui-icon' style='color: red;font-size: 25px;'> &#xe640;</i></a>";
        multiple_str += "</div>";
        multiple_str += "<br/>";
    }
    multiple_str += "</div>";
    multiple_str += "</div>";
    $("#options").html(multiple_str);
}


function initJudgment() {
    var judgment_str = "";
    judgment_str += "<div style='margin-left: 50px;'>";
    judgment_str += "<hr style='width: 800px'/>";
    judgment_str += " <div class='layui-row'>";
    judgment_str += "<span>正确:<input type='radio' name='que_RightAnswer' value='正确' lay-skin='primary'></span>";
    judgment_str += "<div class='layui-input-inline' style='width: 500px;margin-left: -25px'>";
    judgment_str += "<textarea name='que_AnswerA'  class='layui-textarea'></textarea>";
    judgment_str += "</div>";
    judgment_str += "</div>";
    judgment_str += "<br/>";
    judgment_str += " <div class='layui-row'>";
    judgment_str += "<span>错误:<input type='radio' name='que_RightAnswer' value='错误' lay-skin='primary'></span>";
    judgment_str += "<div class='layui-input-inline' style='width: 500px;margin-left: -25px'>";
    judgment_str += "<textarea name='que_AnswerB'  class='layui-textarea'></textarea>";
    judgment_str += "</div>";
    judgment_str += "</div>";
    judgment_str += "</div>";
    $("#options").html(judgment_str);
}

function initBlank() {
    var blank_str = "";
    blank_str += "<div style='margin-left: 50px'>";
    blank_str += "<div><button class='layui-btn layui-btn-sm layui-btn-normal' type='button' id='addBlank'>添加答案</button></div>";
    blank_str += "<hr style='width: 800px'/>";
    blank_str += " <div id='blank'>";
    blank_str += " <div class='layui-row' style='margin-bottom: 10px'>";
    blank_str += "<span>答案1：</span>";
    blank_str += "<div class='layui-input-inline' style='width: 500px;'>";
    blank_str += "<input type='text' name='que_AnswerA' lay-verify='required' autocomplete='off' class='layui-input'>";
    blank_str += "</div>";
    blank_str += "<a onclick='$(this).parent().remove()'><i class='layui-icon' style='color: red;font-size: 25px;'> &#xe640;</i></a>";
    blank_str += "</div>";
    blank_str += "</div>";
    blank_str += "</div>";
    $("#options").html(blank_str);
}

function initEasy() {
    var easy_str = "";
    easy_str += "<div style='margin-left: 40px'>";
    easy_str += "<hr style='width: 800px'/>";
    easy_str += " <div id='easy'>";
    easy_str += " <div class='layui-row' style='margin-bottom: 10px'>";
    easy_str += "<span>答案设置</span>";
    easy_str += "<div class='layui-input-inline' style='width: 500px;margin-left: 15px'>";
    easy_str += "<textarea name='que_RightAnswer' class='layui-textarea'></textarea>";
    easy_str += "</div>";
    easy_str += "</div>";
    easy_str += "</div>";
    easy_str += "</div>";
    $("#options").html(easy_str);
}

