$(document).ready(function () {
    $(".headerpage").load("../common/header.html");

    $("#login").click(function () {
        var username = $("#L_email").val();
        var password = $("#L_pass").val();
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

        $.ajax({
            type: "GET",
            url: "/api/user/login",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "text",
            data: {"phone": username, "pwd": password},//请求需要发送的处理数据
            success: function (data) {
                window.localStorage.setItem('token',data);//保存用户当前token至localStorage
                alert('登陆成功！');
                window.location.href = "index.html";
            },
            error: function (jqXHR, textStatus, errorThrown) {
                /*错误信息处理*/
            	 alert('登陆失败！');
            }
        });
        return false;
    });
});



