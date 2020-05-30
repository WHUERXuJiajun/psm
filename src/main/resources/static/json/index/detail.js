$(document).ready(function () {
    var urlinfo = window.location.href;
    var len = urlinfo.length;
    var offset = urlinfo.indexOf("?");
    var newsidinfo = urlinfo.substr(offset, len)
    var newsids = newsidinfo.split("=");
    var newsid = newsids[1];//得到参数值
    var newsname = newsids[0];
    var mid = newsid;//取得任务id
    if (isNaN(mid)) {
        location.href = document.referrer;
    }

    var token = window.localStorage.getItem('token');
    var phone = "";//取得phone
    let post_phone = "";//发布者phone

    let mission = null;//当前任务

    $("#headerpage").load("../common/header.html");//加载导航栏

    function getUser() {
        let token = window.localStorage.getItem('token');
        let phone = "";
        $.ajax({
            type: "GET",
            url: "/api/user",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "text",
            headers: {
                'Authorization': token//此处放置请求到的用户token
            },
            success: function (data) {
                phone = data;
            }
        });
        return phone;
    }

    //任务信息显示
    $.ajax({
        headers: {
            'Authorization': token//此处放置请求到的用户token
        },
        type: "GET",
        url: "/api/MissionTable/getDetails",
        async: false,
        dataType: "json",
        data: { "mid": mid },
        success: function (data) {
            mission = data.mission;
            post_phone = data.phone;
            $("#description").text(data.mission.description);
            $("#title").text(data.mission.title);
            $("#label1").val(data.mission.label1);
            $("#label2").val(data.mission.label2);
            $("#label3").val(data.mission.label3);
            $("#money").text('悬赏' + data.mission.money);
            $("#post_time").text(data.mission.postTime);
            $("#poster").text(plusXing(data.phone,3,4,'*'));
        }
    });

    function addCollect() {
        //收藏任务
        $.ajax({
            headers: {
                'Authorization': token//此处放置请求到的用户token
            },
            type: "POST",
            url: "/api/collect",
            async: false,
            dataType: "json",
            data: { "mid": mid, "phone": phone },
            success: function (data) {
                if (data == true) {
                    alert("收藏成功");
                } else {
                    alert("收藏失败");
                }
            }
        });
    }


    function cancelCollect() {
        //取消收藏
        $.ajax({
            headers: {
                'Authorization': token//此处放置请求到的用户token
            },
            type: "DELETE",
            url: "/api/collect",
            async: false,
            dataType: "json",
            data: { "mid": mid, "phone": phone },
            success: function (data) {
                if (data == true) {
                    alert("取消收藏成功");
                } else {
                    alert("取消收藏失败");
                }
            }
        });
    }



    function receiveMission() {
        //接受任务
        $.ajax({
            headers: {
                'Authorization': token//此处放置请求到的用户token
            },
            type: "POST",
            url: "/api/Rece/accept",
            async: false,
            dataType: "json",
            data: { "mid": mid, "phone": phone },
            success: function (data) {
                if (data == true) {
                    alert("接受任务成功");
                } else {
                    alert("接受任务失败");
                }
            }
        });
    }


    /* 电话号码部分隐藏处理
    ** str 需要处理的字符串
    ** frontLen 保留的前几位
    ** endLen 保留的后几位
    ** cha 替换的字符串
    */
    function plusXing(str, frontLen, endLen, cha) {
        var len = str.length - frontLen - endLen;
        var xing = '';
        for (var i = 0; i < len; i++) {
            xing += cha;
        }
        return str.substring(0, frontLen) + xing + str.substring(str.length - endLen);
    }


    return false;
});