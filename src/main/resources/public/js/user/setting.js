layui.use(['form','jquery','jquery_cookie','layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 详细资料
     */
    form.on("submit(saveBtn)", function(data) {
        // 获取表单元素的内容
        // 发送ajax请求，修改用户密码
        $.ajax({
            type:"post",
            url:ctx + "/user/setting",
            data:{
                userName:data.field.userName,
                phone:data.field.phone,
                trueName:data.field.trueName,
                id:data.field.id
            },
            dataType:"json",
            success:function (msg) {
                // 判断是否成功
                if (msg.code == 200) {
                    // 修改成功后，用户自动退出系统
                    layer.msg("保存成功", function () {
                        // 退出系统后，删除对应的cookie
                        $.removeCookie("userIdStr", {domain:"localhost",path:"/crs"});
                        $.removeCookie("userName", {domain:"localhost",path:"/crs"});
                        $.removeCookie("trueName", {domain:"localhost",path:"/crs"});
                        // 跳转到登录页面 (父窗口跳转)
                        window.parent.location.href = ctx + "/index";
                    });
                } else {

                    layer.msg(msg.msg);
                }
            }
        });
        return false;
    });

    $("#pwd").click(function () {
        // 跳转到修改密码页面

        window.location.href = ctx + "/user/toPasswordPage";

    })
});