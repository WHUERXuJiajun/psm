$(document).ready(function () {
    var token = document.cookie.split(";")[0];
    var phone = document.cookie.split(";")[1];
    //已发布
    $.ajax({
        header:{
            'Authorization':token
        },
        type: "GET",
        url: "",//接口待定
        async: false,
        dataType: "json",
        data: {"phone": phone},
        success: function (data) {
            //获取的任务
            //[
            //   {
            //     "comment": "string",
            //     "description": "string",
            //     "endTime": "yyyy-MM-dd",
            //     "label1": "string",
            //     "label2": "string",
            //     "label3": "string",
            //     "mid": 0,
            //     "money": 0,
            //     "postTime": "yyyy-MM-dd",
            //     "state": 0,
            //     "title": "string"
            //   }
            // ]
        }
    });

    //已收藏
    $.ajax({
        header:{
            'Authorization':token//此处放置请求到的用户token
        },
        type: "GET",
        url: "/api/collect",//请求程序页面
        async: false,//当有返回值以后才会进行后面的js程序。
        dataType: "json",
        data: {"phone": phone},//请求需要发送的处理数据
        success: function (data) {
            //获取的任务
            //[
            //   {
            //     "comment": "string",
            //     "description": "string",
            //     "endTime": "yyyy-MM-dd",
            //     "label1": "string",
            //     "label2": "string",
            //     "label3": "string",
            //     "mid": 0,
            //     "money": 0,
            //     "postTime": "yyyy-MM-dd",
            //     "state": 0,
            //     "title": "string"
            //   }
            // ]
        }
    });
    return false;
});