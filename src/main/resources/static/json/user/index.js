$(document).ready(function () {
    var token = window.localStorage.getItem('token');
    let phone = getUser();


    $("#headerpage").load("../common/header.html");
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



    return false;
});