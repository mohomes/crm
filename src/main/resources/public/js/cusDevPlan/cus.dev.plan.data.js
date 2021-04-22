layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#cusDevPlanList'
        ,height: 'full-125'
        ,url: ctx+'/cus_dev_plan/list?saleChanceId='+$("[name='id']").val() //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', align:'center', sort: true, fixed: 'left'}
            ,{field: 'planItem', title: '计划项', align:'center'}
            ,{field: 'planDate', title: '计划时间', align:'center'}
            ,{field: 'exeAffect', title: '执行效果', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#cusDevPlanListBar",fixed: 'right',minWidth:150}
        ]]
    });


    table.on('toolbar(cusDevPlans)',function (data) {
        if (data.event==='add'){
            openAddOrUpdateCusDevPlanDialog();
        }else if(data.event==='success'){
            devSuccess(2);
        }else if(data.event==='failed'){
            devSuccess(3);
        }
    })

    function devSuccess(devResult){
        layer.confirm("是否确认执行该操作吗？",{icon:3,title:"营销机会管理"},function (index) {
            layer.close(index)
            var id = $("[name='id']").val()
            $.ajax({
                type:"POST",
                url:ctx + "/cus_dev_plan/updateSaleChanceDevResult/"+id+"/"+devResult,
                data:{
                },
                success:function(result) {
                    if (result.code==200){
                        layer.msg(result.msg,{icon:6})
                        layer.closeAll("iframe")
                        parent.location.reload()
                    }else{
                        layer.msg(result.msg,{icon:5})
                    }
                }
            })
        })
    }

    function openAddOrUpdateCusDevPlanDialog(id){
        var url = ctx + "/cus_dev_plan/toAddOrUpdateCusPlanPage/"+$("[name='id']").val()
        var title = "计划项管理 - 添加计划项"
        // 判断计划项id 是否为空
        if (id != null && id!=''){
            //更新计划项
            title = "计划项管理 - 更新计划项"
            url += "/"+id
        }

        layui.layer.open({
            type: 2,
            title: title,
            area: ['500px', '300px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }


    table.on('tool(cusDevPlans)',function (data) {
        if (data.event == "edit"){
            openAddOrUpdateCusDevPlanDialog(data.data.id);
        }else if(data.event=="del"){
            deleteCusDevPlan(data.data.id)
        }
    })


    function deleteCusDevPlan(id){
        layer.confirm("是否确认删除这条记录吗？",{icon:3,title:"计划项数据管理"},function (index) {
            layer.close(index)
            $.ajax({
                type:"POST",
                url:ctx + "/cus_dev_plan/delete/"+id,
                data:{
                },
                success:function(result) {
                    if (result.code==200){
                        layer.msg(result.msg,{icon:6})
                        tableIns.reload()
                    }else{
                        layer.msg(result.msg,{icon:5})
                    }
                }
            })
        })
    }



    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});