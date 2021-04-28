layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#orderList'
        ,height: 'full-125'
        ,url: ctx+'/order_detail/list?orderId='+$("input[name='id']").val()//数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'center'}
            ,{field: 'id', title: '编号', align:'center', sort: true}
            ,{field: 'goodsName', title: '商品名称', align:'center'}
            ,{field: 'goodsNum', title: '商品数量', align:'center'}
            ,{field: 'unit', title: '单位', align:'center'}
            ,{field: 'price', title: '单价(￥)', align:'center'}
            ,{field: 'sum', title: '总价(￥)', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
        ]]
    });




    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});