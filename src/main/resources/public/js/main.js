 layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单初始化
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0" src="welcome"></iframe>')
    layuimini.initTab();

    $(".login-out").click(function(){
        layer.confirm("确定退出系统吗？",{icon:3,title:'系统提示'},function (index) {

            // 跳出登录（父窗口）
            layer.close(index);

            // 清空cookie信息
            $.removeCookie("userIdStr",{domain:"localhost",path:"/crm"});
            $.removeCookie("userName",{domain:"localhost",path:"/crm"});
            $.removeCookie("trueName",{domain:"localhost",path:"/crm"});
            window.parent.location.href = ctx +"/index"
            // 关闭询问框

        })

    })


});

