$(function () {
    $("#confirm").click(function () {
        var title = $("#L_title").val();
        var description = $("#L_content").val();
        var money = $("#money").val();
        var label1 = $("#label1").val();
        var label2 = $("#label2").val();
        var label3 = $("#label3").val();
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
            type: "POST",
            url: "/post/post_mission",//请求程序页面
            async: false,//当有返回值以后才会进行后面的js程序。
            data: {"title": title, "description": description, "money":money, "label1":label1, "label2":label2, "label3":label3},//请求需要发送的处理数据
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