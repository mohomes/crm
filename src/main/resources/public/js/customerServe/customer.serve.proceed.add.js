layui.use(['form','layer','jquery_cookie'],function () {
    var form = layui.form,
        layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($)
    ;
    form.render()
    // 加载指派人的（客户经理）
    $.ajax(
        {
            type:"GET",
            url: ctx + "/user/queryAllManager",
            data:{},
            success:function(result){
                if (result != null) {
                    // 遍历返回的数据
                    var assigner = $("[name='man']").val()
                    // 遍历返回的数据
                    for (let i = 0; i < result.length; i++) {
                        var opt = "";
                        if (assigner == result[i].id) {
                            opt =" <option value='"+result[i].id+"' selected>"+result[i].uname+"</option>"
                        }else{
                            opt =" <option value='"+result[i].id+"'>"+result[i].uname+"</option>"
                        }

                        $("#assigner").append(opt)
                    }
                }
                layui.form.render('select')
            }
        }
    )

    form.on('submit(updateCustomerServeProceed)',function (data) {
        var index = layer.msg("数据提交中,请稍等...",{
            icon:16,
            time:false,
            shade:0.8
        })
        var formData =data.field
        data.field.serviceProcePeople = $.cookie("trueName")
        var url = ctx+"/customer_serve/update"
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