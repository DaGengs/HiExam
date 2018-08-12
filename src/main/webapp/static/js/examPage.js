
var maxtime;
layui.use('form', function() {
    var form = layui.form, $ = layui.jquery;
    var answers = [];
    form.on('radio(radio)', function(data){
        var theAnswer = data.value;
        var theQues_id = $(data.elem).next().next().next()[0].value;
        var arr = {
            quesId: theQues_id,
            answer: theAnswer
        };
        answers.push(arr);
    });

            var a = "";
    form.on('checkbox(checkbox)', function(data){
        console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        a += data.value;

        var theAnswer = a;
        var theQues_id = $(data.elem).next().next().next()[0].value;
        var arr = {
            quesId: theQues_id,
            answer: theAnswer
        };
        answers.push(arr);
    });

    form.on('submit(commit)', function(data){
        // var checked = $("input:checked");

        /*$.each($('input:radio:checked'),function(){
            answers.push($(this).val());
        });*/
       /* for (var i = 0; i < checked.length; i++) {
            answers.push(checked[i].value);
        }*/
       /* var cbs = "";
        $.each($('input:checkbox:checked'),function(){
            cbs += $(this).val();
            answers.push(cbs);
        });*/

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
        var arr = {paper_id: paper.id, answers:answers, ques_ids: ques_ids, scores: scores};
        console.log(arr);
       /* $.ajax({
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
        })*/
        return false;
    });




    var paper;
    var questions;

/*------------------------------------------------试题展示-------------------------------------------------------------------*/


$(function () {
    var json = getData();
    var obj = JSON.parse(decodeURI(json));
    showData(obj);
    /*倒计时*/



})

function getData() {
    var url = window.location.search;
    index = url.indexOf("?");
    if (index > -1) {
        var str = url.substr(index + 1);
        if (str.indexOf('=')) {
            return str.split('=')[1];
        }
    }
    return null;
}

function showData(obj) {
    $.ajax({
        url: "getPaperData.do",
        type: "post",
        data: {
            paper_id: obj.id
        },
        success: function (data) {
            showPaper(data);
        }
    })
}

function showPaper(data) {
    paper = data.paper;
    questions = data.questionVos;
    var options = ['A', 'B', 'C', 'D', 'E', 'F'];
    $("#title").html(paper.title);
    $("#time").append(paper.time+"分钟");
    var str = "";
    $.each(questions, function (i, question) {
        str += "<span style='font-size: 20px'>"+(i+1)+"、"+question.que_title+"</span>";
        str += "<br/>";
        switch (question.que_type) {
            case '单选':
                $("#type").html("<h2 style='font-weight: bold'>单选题</h2>");
                $.each(question.options, function (o, option) {
                    if (option != "") {
                        str += "<input type='radio' name='answer" +(i)+ "' lay-filter='radio'  value='"+options[o]+"'><label style='font-size: 18px;margin-left: -20px;'>"+options[o]+"、"+option+"</label>";
                        str += "<input type='hidden' name='ques"+(i)+"' value='"+question.que_id+"'>";
                        str += "<br/>";
                    }
                });
                break;
            case '多选':
                $("#type").html("<h2 style='font-weight: bold'>多选题</h2>");
                $.each(question.options, function (o, option) {
                    if (option != "") {
                        str += "<input type='checkbox' name='answer" +(i)+ "' lay-filter='checkbox' value='"+options[o]+"'><label style='font-size: 18px'>"+option+"</label>";
                        str += "<input type='hidden' name='ques"+(i)+"' value='"+question.que_id+"'>";
                        str += "<br/>";
                    }
                });
                break;
        }
        str += "<br/>";
    })
    $("#questionList").prepend(str);
    form.render();

    if(window.name==''){
        maxtime = paper.time*60;
    }else{
        maxtime = window.name;
    }
}


});
function CountDown(){
    if(maxtime>=0){
        hours = Math.floor(maxtime/3600);
        minutes = Math.floor((maxtime - hours*3600)/60);
        seconds = Math.floor(maxtime%60);
        msg = "距离结束还有"+hours+"时"+minutes+"分"+seconds+"秒";
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