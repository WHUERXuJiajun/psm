$(document).ready(function () {
    var token =window.localStorage.getItem('token');


    function logout(){
        $.ajax({
            headers:{
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
    }

});