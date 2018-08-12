layui.use('form', function() {
    var form = layui.form, $ = layui.jquery;

    form.on('submit(commit)', function(data){
        var checked = $("input:checked");
        var answers = [];
        for (var i = 0; i < checked.length; i++) {
            answers.push(checked[i].value);
        }

        var ques_ids = [];
        var rightAnswers = [];
        for (var i = 0; i < questions.length; i++) {
            ques_ids.push(questions[i].que_id);
            rightAnswers.push(questions[i].que_RightAnswer);
        }

        var scores = [];
        for (var i = 0; i < questions.length; i++) {
            if (answers[i] == rightAnswers[i]) {
                scores.push(10);
            } else {
                scores.push(0);
            }
        }
        var arr = {test_id: obj.testPaperId, answers:answers, ques_ids: ques_ids, scores: scores};
        $.ajax({
            url: "commitPage.do",
            type: "post",
            data: arr,
            success: function (data) {
                console.log(data);
                if (data.code == 0) {
                    layer.msg(data.message, {icon: 6});
                    setInterval("location.href='testList.html'",3000);
                } else {
                    layer.msg(data.message, {icon: 5});
                }
            }
        })
        return false;
    });

});
var obj;
$(function () {
    var json = localStorage.getItem("pageData");
    obj=JSON.parse(json);
    //显示数据
    showData(obj);
})
//显示数据
var questions;
function showData(obj) {
    console.log(obj);
    var options = ['A', 'B', 'C', 'D', 'E', 'F'];
    $("#title").html(obj.testPaperName);
    questions = obj.questionVos;
    var str = "";
    $.each(questions, function (i, question) {
        str += "<span style='font-size: 20px'>"+(i+1)+"、"+question.que_title+"</span>";
        str += "<br/>";
        switch (question.que_type) {
            case '单选':
                $("#type").html("<h2 style='font-weight: bold'>单选题</h2>");
                $.each(question.options, function (o, option) {
                    if (option != "") {
                        str += "<input type='radio' name='answer" +(i)+ "' value='"+options[o]+"'><label style='font-size: 18px;margin-left: -20px;'>"+options[o]+"、"+option+"</label>";
                        str += "<input type='hidden' name='ques"+(i)+"' value='"+question.que_id+"'>";
                        str += "<br/>";
                    }
                });
                break;
            case '多选':
                $("#type").html("<h2 style='font-weight: bold'>多选题</h2>");
                $.each(question.options, function (o, option) {
                    if (option != "") {
                        str += "<input type='checkbox' name='answer" +(i)+ "' value='"+options[o]+"'><label style='font-size: 18px'>"+option+"</label>";
                        str += "<input type='hidden' name='ques"+(i)+"' value='"+question.que_id+"'>";
                        str += "<br/>";
                    }
                });
                break;
        }
        str += "<br/>";
    })
    $("#questionList").prepend(str);
}

var maxtime;
if(window.name==''){
    maxtime = 10*60;
}else{
    maxtime = window.name;
}

function CountDown(){
    if(maxtime>=0){
        minutes = Math.floor(maxtime/60);
        seconds = Math.floor(maxtime%60);
        msg = "距离结束还有"+minutes+"分"+seconds+"秒";
        document.getElementById("timer").innerHTML = msg;
        if(maxtime == 5*60) alert('注意，还有5分钟!');
        --maxtime;
        window.name = maxtime;
    } else {
        clearInterval(timer);
        alert("考试时间到，结束!");
    }
}
timer = setInterval("CountDown()",1000);