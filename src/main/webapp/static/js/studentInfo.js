$(function () {
    $.ajax({
        url:'studentQueryByNo.do'
        ,success: function (data) {
            $('#stu_no').val(data.stu_no);
            $('#stu_name').val(data.stu_name);
            $('#stu_gender').val(data.stu_gender);
            $('#stu_idNumber').val(data.stu_idNumber);
            $('#stu_phone').val(data.stu_phone);
            $('#class_name').val(data.class_name);
        }
    })
})