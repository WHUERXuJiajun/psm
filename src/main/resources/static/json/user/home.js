$(document).ready(function () {
    var token =window.localStorage.getItem('token');//取得Token
    var phone = getUser();//取得phone


    function getUser(){
        let phone = "";
        $.ajax({
            type: "GET",
            url: "/api/user",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "text",
            headers:{
                'Authorization':token//此处放置请求到的用户token
            },
            success: function (data) {
                phone = data;
            }
        });
        return phone;
    }

    //用户信息显示
    $.ajax({
        headers:{
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