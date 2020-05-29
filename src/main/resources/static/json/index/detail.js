$(document).ready(function () {
    var urlinfo = window.location.href;
    var len = urlinfo.length;
    var offset = urlinfo.indexOf("?");
    var newsidinfo = urlinfo.substr(offset, len)
    var newsids = newsidinfo.split("=");
    var newsid = newsids[1];//得到参数值
    var newsname = newsids[0];
    var mid = newsid;//取得任务id
    if(isNaN(mid)){
        location.href = document.referrer;
    }

    var token = window.localStorage.getItem('token');;//取得Token
    var phone = getUser();//取得phone

    function getUser(){
        let token = window.localStorage.getItem('token');
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

    //任务信息显示
    $.ajax({
        headers:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "GET",
        url: "/api/MissionTable/getDetails",
        async: false,
        dataType: "json",
        data: {"mid": mid},
        success: function(data){
            $("#description").val(data.description);
            $("#title").val(data.title);
            $("#label1").val(data.label1);
            $("#label2").val(data.label2);
            $("#label3").val(data.label3);
            $("#money").val(data.money);
        }
    });

    //收藏任务
    $.ajax({
        headers:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "POST",
        url: "/api/collect",
        async: false,
        dataType: "json",
        data: {"mid": mid, "phone":phone},
        success: function(data){
            if (data == "true") {
                alert("收藏成功");
            } else {
                alert("收藏失败");
            }
        }
    });

    //取消收藏
    $.ajax({
        headers:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "DELETE",
        url: "/api/collect",
        async: false,
        dataType: "json",
        data: {"mid": mid, "phone":phone},
        success: function(data){
            if (data == "true") {
                alert("取消收藏成功");
            } else {
                alert("取消收藏失败");
            }
        }
    });

    //接受任务
    $.ajax({
        headers:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "POST",
        url: "/api/Rece/accept",
        async: false,
        dataType: "json",
        data: {"mid": mid, "phone":phone},
        success: function(data){
            if (data == "true") {
                alert("接受任务成功");
            } else {
                alert("接受任务失败");
            }
        }
    });

    return false;
});