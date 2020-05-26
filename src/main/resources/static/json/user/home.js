$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "",//getToken
        async: false,
        success: function (data) {
            $("#credit").text(data.credit);
            $("#motto").text(data.motto);
            $("#score").text(data.score);
            $("#phone").text(data.phone);
            $("#icon").attr("src",data.icon);
        }
    });
    return false;
});
