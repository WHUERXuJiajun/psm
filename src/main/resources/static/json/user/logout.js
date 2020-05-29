$(document).ready(function () {
    var token = document.cookie.split(";")[0];//取得Token
    var phone = document.cookie.split(";")[1];//取得phone

    //退出登录
    $.ajax({
        header:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "DELETE",
        url: "/api/user/logout",
        async: false,
        dataType: "json",
        data: {"token": token},
        success: function(data){
            alert('已退出登录');
            window.location.href = "index.html";
        }
    });
    return false;
});