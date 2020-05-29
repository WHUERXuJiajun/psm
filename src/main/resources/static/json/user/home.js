$(document).ready(function () {
    var token = document.cookie.split(";")[0];//取得Token
    var phone = document.cookie.split(";")[1];//取得phone

    //用户信息显示
    $.ajax({
        header:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "GET",
        url: "/api/user",
        async: false,
        dataType: "json",
        data: {"phone": phone},
        success: function(data){
            $("#credit").val(data.credit);
            $("#motto").val(data.motto);
            $("#score").val(data.score);
            $("#phone").val(data.phone);
            $("#phone1").val(data.phone);
            $("#icon").attr("src",data.icon);
            $("#icon1").attr("src",data.icon);
        }
    });

    

    return false;
});