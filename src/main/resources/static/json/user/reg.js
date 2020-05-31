$(function () {
    $(".headerpage").load("../common/header.html");
    $("#register").click(function () {
        let username = $("#phone").val();
        let password = $("#pwd").val();
        let rePwd = $("#rePwd").val();
        if (!username) {
            alert("学号必填!");
            $("#username").focus();//获取焦点
            return;
        }
        if (!password) {
            alert("密码必填!");
            $("#password").focus();//获取焦点
            return;
        }
        //验证学号格式
        if(!(/^20\d{11}$/.test(username))){
            alert("学号有误，请重填");
            return false;
        }
        if(password.length < 6){
            alert("密码不符合要求，请重新填写");
            return false;
        }

        //验证重复密码是否正确
        if(password !== rePwd){
            alert("密码前后填写不一致!");
            return false;
        }


        $.ajax({
            type: "POST",
            url: "/api/user/register",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            data: {"phone": username, "pwd": password},//请求需要发送的处理数据
            success: function (data) {
                if (data == true) {
                    alert("注册成功！");
                    window.location.href = 'login.html';
                } else {
                    alert("注册失败，电话号码可能已被注册，请重新尝试");
                }
            }
        });
        return false;
    });
});



