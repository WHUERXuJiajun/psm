$(function () {
    let token = window.localStorage.getItem('token');
    let user = null;

    user = getUser();

    $("#headerpage").load("../common/header.html");


    function getUser(){
        token = window.localStorage.getItem('token');
        $.ajax({
            type: "GET",
            url: "/api/user/user_info",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "json",
            headers:{
                'Authorization':token//此处放置请求到的用户token
            },
            success: function (data) {
                user = data;
                $('#L_sign').text(user.motto)
            }
        });
        return user;
    }

    //增加座右铭
    $('#addMottoBtn').click(function () {
        user = getUser()
        let motto = $('#L_sign').val();
        user.motto = motto;
        $.ajax({
            type: "PUT",
            url: "/api/user",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "json",
            contentType:'application/json;charset=UTF-8',
            headers:{
                'Authorization':token//此处放置请求到的用户token
            },
            data:JSON.stringify(user),
            success: function (data) {
                alert('更新成功')
            }
        });
    })


    //修改密码
    $("#resetpass").click(function () {
        var oldPwd = $("#L_nowpass").val();
        var newPwd = $("#L_pass").val();
        var newPwd2 = $("#L_repass").val();
        phone = getUser();
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
                headers:{
                    'Authorization':token//此处放置请求到的用户token
                },
                type: "PUT",
                url: "/api/user/updatePwd",
                async: false,
                dataType: "json",
                data: {"phone": user.phone,"oldPwd":oldPwd,"newPwd":newPwd },
                success: function(data){
                    alert('密码修改成功');
                }
            });
        }
    });

    //上传头像
    // $.ajax({
    //     header:{
    //         'Authorization':token//此处放置请求到的用户token
    //     },
    //     type: "POST",
    //     url: "/api/user/uploadIcon",
    //     async: false,
    //     dataType: "json",
    //     data: { },
    //     success: function(data){
    //
    //     }
    // });


    return false;



});