layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#customerOrderList'
        ,height: 'full-125'
        ,url: ctx+'/order/list?cusId='+$("[name='id']").val() //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'center'}
            ,{field: 'id', title: '编号', align:'center', sort: true}
            ,{field: 'orderNo', title: '订单编号', align:'center'}
            ,{field: 'orderDate', title: '下单日期', align:'center'}
            ,{field: 'address', title: '收获地址', align:'center'}
            ,{field: 'state', title: '支付状态', align:'center',templet: function(data){
                if (data.state==1){
                    return "已支付"
                }else{
                    return "未支付"
                }
                }}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#customerOrderListBar",fixed: 'right',minWidth:150}
        ]]
    });



    table.on('tool(orders)',function (data) {
        if (data.event == "info"){
            openCustomerOrderDialog(data.data.id);
        }
    })

    function openCustomerOrderDialog(data){
        var url = ctx +"/order/toOrderDetailPage?orderId="+data
        layui.layer.open({
            type: 2,
            title: '<h3>客户管理 - 订单详情</h3>',
            area: ['700px', '500px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }




    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});