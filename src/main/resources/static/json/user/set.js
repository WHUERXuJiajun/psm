$(function () {
    var token = document.cookie.split(";")[0];//取得Token
    var phone = document.cookie.split(";")[1];//取得phone

    //修改密码
    $("#resetpass").click(function () {
        var oldPwd = $("#L_nowpass").val();
        var newPwd = $("#L_pass").val();
        var newPwd2 = $("#L_repass").val();
        if (!oldPwd) {
            alert("旧密码必填!");
            $("#L_nowpass").focus();//获取焦点
            return;
        }
        if (!newPwd) {
            alert("新密码必填!");
            $("#L_pass").focus();//获取焦点
            return;
        }
        if (!newPwd2) {
            alert("必须确认新密码！");
            $("#L_repass").focus();//获取焦点
            return;
        }
        if(newPwd!==newPwd2){
            alert("两次输入必须一致!");
        }else {
            $.ajax({
                header:{
                    'Authorization':token//此处放置请求到的用户token
                },
                type: "PUT",
                url: "/api/user/updatePwd",
                async: false,
                dataType: "json",
                data: {"phone": phone,"oldPwd":oldPwd,"newPwd":newPwd },
                success: function(data){
                    alert('密码修改成功');
                }
            });
        }
    });

    //上传头像
    $.ajax({
        header:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "POST",
        url: "/api/user/uploadIcon",
        async: false,
        dataType: "json",
        data: { },
        success: function(data){

        }
    });


    return false;



});