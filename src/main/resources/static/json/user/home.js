$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "",//请求Token
        async: false,
        dataType: "json",
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