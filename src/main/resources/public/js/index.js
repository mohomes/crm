layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(login)',function(data){
        // 表单非空检验
        /**
         * 发送ajax请求
         */
        $.ajax({
            type:"post",
            url: ctx + "/user/login",
            data:{
                userName:data.field.username,
                userPwd:data.field.password
            },
            success:function(result){
                if (result.code==200){
                    layer.msg("登录成功",function () {
                        // 判断用户是否选择记住密码 如果选中 设置cookie 7天有效
                        if($("#rememberMe").prop("checked")){
                            $.cookie("userIdStr",result.result.userIdStr,{expires:7});
                            $.cookie("userName",result.result.userName,{expires:7});
                            $.cookie("trueName",result.result.trueName,{expires:7});
                        }

                        // 将登录信息存储到cookie中
                        $.cookie("userIdStr",result.result.userIdStr);
                        $.cookie("userName",result.result.userName);
                        $.cookie("trueName",result.result.trueName);
                        // 登录成功之后跳转
                        window.location.href = ctx +"/main"
                    })
                }else{
                    layer.msg(result.msg,{icon:5})
                }
            }
        })
        return false;
    });

});