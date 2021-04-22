layui.use(['form','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        form = layui.form;


    form.on('submit(addOrUpdateCusDevPlan)',function (data) {
        var index = top.layer.msg("数据提交中,请稍等...",{
            icon:16,
            time:false,
            shade:0.8
        })
        var url =ctx+"/cus_dev_plan/add"

        if ($("[name='id']").val()!=null && $("[name='id']").val()!=''){
            url =ctx+"/cus_dev_plan/update"
        }

        $.post(url,data.field,function (result) {
            if (result.code==200){
                top.layer.msg(result.msg,{icon:6})
                top.layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe")
                parent.location.reload()
            }else{
                top.layer.msg(result.msg,{icon:5})
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