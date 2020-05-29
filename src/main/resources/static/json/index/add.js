$(document).ready(function () {

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


    $("#confirm").click(function () {
        var title = $("#L_title").val();
        var description = $("#L_content").val();
        var money = $("#money").val();
        var label1 = $("#label1").val();
        var label2 = $("#label2").val();
        var label3 = $("#label3").val();
        let year = $("#year").val();
        let month = $("#month").val();
        let day = $("#day").val();
        let end_time = new Date(year+"/"+month+"/"+day);
        let token = window.localStorage.getItem('token');
        //var phone = document.cookie.split(";")[1];
        let phone = getUser();
        if (!title) {
            alert("任务名称必填!");
            $("#L_title").focus();//获取焦点
            return;
        }
        if (!description) {
            alert("任务描述必填!");
            $("#L_content").focus();//获取焦点
            return;
        }

        $.ajax({
            headers:{
                'Authorization':token//此处放置请求到的用户token
            },
            type: "POST",
            url: "/api/post/post_mission",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "json",
            data: {"phone":phone,"title": title, "description": description, "money":money, "label1":label1, "label2":label2, "label3":label3,"end_time":end_time},//请求需要发送的处理数据
            success: function (data) {
                if (data == "true") {
                    alert("任务发布成功");
                } else {
                    alert("任务发布失败");
                }

            }
        });
        return false;
    });
});