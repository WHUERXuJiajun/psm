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
            if(data.mission.label1.length > 0)
                $("#label1").text(data.mission.label1);
            if(data.mission.label2.length > 0)
                $("#label2").text(data.mission.label2);
            if(data.mission.label3.length > 0)
                $("#label3").text(data.mission.label3);
            $("#money").text('悬赏' + data.mission.money);
            $("#post_time").text(data.mission.postTime);
            $("#poster").text(plusXing(data.phone,3,4,'*'));
            if(data.mission.state != 0)
                $('#receiveBtn').attr("disabled", true).css("background","#808080").text('已接受');
        }
    });



    $("#collectBtn").click(function () {
        //收藏任务
        let phone = getUser();
        $.ajax({
            headers: {
                'Authorization': token//此处放置请求到的用户token
            },
            type: "POST",
            url: "/api/collect",
            async: false,
            contentType:'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({ "mid": mid, "phone": phone }),
            success: function (data) {
                if (data == true) {
                    alert("收藏成功");
                } else {
                    alert("已收藏");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                /*错误信息处理*/
                alert('请先登录！');
                window.location.href="login.html"
            }
        });
    })





    $("#receiveBtn").click(function () {
        phone = getUser();
        if(post_phone == phone){
            alert('不能接受自己发布的任务')
            return ;
        }

        //接受任务
        $.ajax({
            headers: {
                'Authorization': token//此处放置请求到的用户token
            },
            type: "POST",
            url: "/api/Rece/accept",
            async: false,
            contentType:'application/json;charset=UTF-8',
            dataType: "json",
            data: JSON.stringify({ "mid": mid, "phone": phone }),
            success: function (data) {
                if (data == true) {
                    alert("接受任务成功");
                    window.location.href="detail.html?mid="+mid;
                } else {
                    alert("已接受");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                /*错误信息处理*/
                alert('请先登录！');
                window.location.href="login.html"
            }
        });
    })


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