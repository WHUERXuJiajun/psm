$(document).ready(function () {

    let user = null;

    let token = window.localStorage.getItem('token');
    $('#d_1').css({"display": "none"});


    $.ajax({
        type: "GET",
        url: "/api/user/user_info",//请求程序页面
        async: false,//当有返回值以后才会进行后面的js程序。
        dataType: "json",
        headers: {
            'Authorization': token//此处放置请求到的用户token
        },
        success: function (data) {
            user = data;
            console.log(user)
            $('#w_1').css({"display": "none"});
            $('#w_2').css({"display": "none"});
            $('#w_3').css({"display": "none"});
            $('#d_1').css({"display": "inline"});
            $('#user').text(plusXing(user.phone,3,4,"*"))
            $('#_icon').attr("src",user.icon)
        }
    });


    //退出登录
    $('#logout').click(function () {
        $.ajax({
            type: "GET",
            url: "/api/user/logout",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            dataType: "json",
            data:{'token':token},
            headers: {
                'Authorization': token//此处放置请求到的用户token
            },
            success: function (data) {
                window.localStorage.setItem('token',"");//删除当前token
                window.location.href="../index/login.html"
            }
        })
    })

    
    $('#goHome').click(function () {
        window.location.href = "../user/home.html?phone="+user.phone;
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