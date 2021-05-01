layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#reqList'
        ,height: 'full-125'
        ,url: ctx+'/customer_reprieve/list?lossId='+$("[name='id']").val() //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'center'}
            ,{field: 'id', title: '编号', align:'center', sort: true}
            ,{field: 'measure', title: '暂缓措施', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#reqBar",fixed: 'right',minWidth:150}
        ]]
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(reqs)',function(data){
        if(data.event==="edit"){
            openAddOrUpdateCustomerRepDialog(data.data.id);
        }else if(data.event==="del"){
            deleteCustomerRepDialog(data.data.id);
        }
    })

    function deleteCustomerRepDialog(id){
        layer.confirm("是否确认删除这条记录吗？",{icon:3,title:"客户管理"},function (index) {
            layer.close(index)
            $.ajax({
                type:"POST",
                url:ctx + "/customer_reprieve/delete",
                data:{
                    id:id
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

    /**
     * 头部工具栏
     */
    table.on('toolbar(reqs)',function(obj){
        if (obj.event==="add"){
            openAddOrUpdateCustomerRepDialog();
        }else if(obj.event==="confirm"){
            // 客户是否确认流失状态
            updateCustomerLossState();
        }
    })

    function openAddOrUpdateCustomerRepDialog(id){
        let title ="<h3>暂缓管理 - 添加暂缓信息</h3>"
        let url = ctx + "/customer_reprieve/toAddOrUpdateCustomerReprPage?lossId="+$("[name='id']").val();

        if (id!=null && id!=''){
            title ="<h3>暂缓管理 - 修改暂缓信息</h3>"
            url += "&id="+id;
        }
        layui.layer.open({
            type:2,
            title:title,
            area:['500px','300px'],
            content:url,
            maxmin:true
        })
    }


    function updateCustomerLossState(){
        layer.confirm('确认标记当前客户为确认流失吗？',{icon:3,title:"客户流失管理"},function(index){
            // 关闭输入框
            layer.close(index)
            layer.prompt({title:'请输入流失原因',formType:2},function(text,index){
                layer.close(index);
                /*更新指定流失客户的流失状态*/
                $.ajax({
                    type:"post",
                    url: ctx + "/customer_loss/updateCustomerLossState",
                    data:{
                        id: $("[name='id']").val(),
                        lossReason:text
                    },
                    dataType:"json",
                    success:function(data){
                        if(data.code==200){
                            layer.msg(data.msg,{icon:6})
                            // 关闭窗口
                            layer.closeAll("iframe");
                            // 刷新父页面
                            parent.location.reload();
                        }else{
                            layer.msg(data.msg,{icon:5})
                        }
                    }
                })
            })
        })
    }

    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});