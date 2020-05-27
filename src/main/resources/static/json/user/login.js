$(document).ready(function () {
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
            url: "/api/user/login",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "text",
            data: {"phone": username, "pwd": password},//请求需要发送的处理数据
            success: function (data) {
                document.cookie = data;
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



