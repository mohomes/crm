layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#customerLossList'
        ,height: 'full-125'
        ,url: ctx+'/customer_loss/list' //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox',fixed:'center'}
            ,{field: 'id', title: '编号', align:'center'}
            ,{field: 'cusNo', title: '客户编号', align:'center'}
            ,{field: 'cusName', title: '客户名称', align:'center'}
            ,{field: 'cusManager', title: '客户经理', align:'center'}
            ,{field: 'lastOrderTime', title: '最后下单时间', align:'center'}
            ,{field: 'lossReason', title: '流失原因', align:'center'}
            ,{field: 'confirmLossTime', title: '确认流失时间', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#op",fixed: 'right',minWidth:150}
        ]]
    });

    $(".search_btn").click(function () {
        /**
         * 表格重载 多条件查询
         */
        tableIns.reload({
            where: {
                cusNo: $("[name='cusNo']").val()
                ,cusName: $("[name='cusName']").val()
                ,state: $("[name='state']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    table.on('tool(customerLoss)',function(obj){
        if (obj.event === "add"){
            openCustomerLossDialog("<h3>流失管理 - 暂缓措施维护</h3>",obj.data.id)
        }else if(obj.event === "info"){
            openCustomerLossDialog("<h3>流失管理 - 暂缓措施查看</h3>",obj.data.id)
        }
    })

    function openCustomerLossDialog(title,id){
        layui.layer.open({
            type:2,
            title:title,
            area:['700px','500px'],
            content: ctx +"/customer_loss/openCustomerLossPage?id="+id,
            maxmin:true
        })
    }


});