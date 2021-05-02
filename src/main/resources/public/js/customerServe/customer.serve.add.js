layui.use(['form','layer','jquery_cookie'],function () {
    var form = layui.form,
            layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($)
    ;

    form.on('submit(addCustomerServe)',function (data) {
        var index = layer.msg("数据提交中,请稍等...",{
            icon:16,
            time:false,
            shade:0.8
        })
        data.field.createPeople=$.cookie("trueName")
        var formData =data.field
        var url = ctx+"/customer_serve/add"
        $.post(url,formData,function (result) {
            if (result.code==200){
                setTimeout(function () {
                    top.layer.close(index)
                    top.layer.msg(result.msg,{icon:6})
                    // 关闭弹出层
                    layer.closeAll("iframe")
                    parent.location.reload()
                })
            }else{
                layer.msg(result.msg,{icon:5})
            }
        })
        return false;
    })


    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});