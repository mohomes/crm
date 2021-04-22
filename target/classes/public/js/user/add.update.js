layui.use(['form','layer','formSelects'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        form = layui.form,
        formSelects = layui.formSelects;


    form.on('submit(addOrUpdateUser)',function (data) {
        var index = layer.msg("数据提交中,请稍等...",{
            icon:16,
            time:false,
            shade:0.8
        })
        var url =ctx+"/user/add"

        if ($("[name='id']").val()!=null && $("[name='id']").val()!=''){
            url =ctx+"/user/update"
        }
        $.post(url,data.field,function (result) {
            if (result.code==200){
                layer.msg(result.msg,{icon:6})
                layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe")
                parent.location.reload()
            }else{
                layer.msg(result.msg,{icon:5})
            }
        })
        return false;
    })

    /**
     * 多选下拉框 (复显和赋值)
     */
    formSelects.config('selectId',{
        type:"post",
        searchUrl:ctx+"/role/queryAllRoles?userId="+$("[name='id']").val(),
        keyName:"roleName",
        keyVal:"id"
    },true);




    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});