layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(saveBtn)',function(data){
        // 表单非空检验
        console.log(data.field)
        $.ajax({
            type:"post",
            url: ctx + "/user/updatePwd",
            data:{
                oldPwd:data.field.oldPwd,
                newPwd:data.field.newPwd,
                repeatPwd:data.field.repeatPwd
            },
            success:function(result){
                if (result.code==200){
                    // 清空cookie数据
                    layer.msg("用户密码修改成功，系统将在3秒钟后退出...",function () {
                        $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
                        $.removeCookie("userName",{domain:"localhost",path:"/crm"});
                        $.removeCookie("trueName",{domain:"localhost",path:"/crm"});
                        // 跳出登录（父窗口）
                        window.parent.location.href = ctx +"/index"
                    })
                }else{
                    layer.msg(result.msg,{icon:5})
                }
            }
        })
        return false;
    });

});