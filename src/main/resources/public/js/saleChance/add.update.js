layui.use(['form','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        form = layui.form;


    form.on('submit(addOrUpdateSaleChance)',function (data) {
        var index = layer.msg("数据提交中,请稍等...",{
            icon:16,
            time:false,
            shade:0.8
        })
        var url =ctx+"/sale_chance/add"

        if ($("[name='id']").val()!=null && $("[name='id']").val()!=''){
            url =ctx+"/sale_chance/update"
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

    $.ajax(
        {
            type:"GET",
            url: ctx + "/user/queryAllSales",
            data:{},
            success:function(result){
                console.log(result)
                if (result != null) {
                    // 获取隐藏域中的指派人ID
                    var assignManId = $("#assignManId").val()
                    // 遍历返回的数据
                    for (let i = 0; i < result.length; i++) {
                        var opt = ''
                        if (assignManId == result[i].id) {
                            opt =" <option value='"+result[i].id+"' selected>"+result[i].uname+"</option>"
                        }else{
                            opt =" <option value='"+result[i].id+"'>"+result[i].uname+"</option>"
                        }

                        $("#assignMan").append(opt)
                    }
                }
                layui.form.render('select')
            }
        }
    )

    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});