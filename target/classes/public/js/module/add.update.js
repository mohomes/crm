layui.use(['form','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        form = layui.form;


    form.on('submit(addModule)',function (data) {
        console.log(data.field)
        var index = layer.msg("数据提交中,请稍等...",{
            icon:16,
            time:false,
            shade:0.8
        })

        $.post(ctx+"/module/add",data.field,function (result) {
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