$(function () {
    $("#login").click(function () {
        var username = $("#L_email").val();
        var password = $("#L_pass").val();
        if (!username) {
            alert("账号必填!");
            $("#username").focus();//获取焦点
            return;
        }
        if (!password) {
            alert("密码必填!");
            $("#password").focus();//获取焦点
            return;
        }

        $.ajax({
            type: "GET",
            url: "/user/login",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            data: {"phone": username, "pwd": password},//请求需要发送的处理数据
            success: function (data) {
                if (data == "true") {
                    window.location.href = '../index/index.html';
                } else {
                    alert("密码错误或用户不存在");
                }
            }
        });
    });
});



