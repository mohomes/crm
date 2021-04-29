layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#contactList'
        ,height: 'full-125'
        ,url: ctx+'/contact/list?cusId='+$("[name='id']").val() //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'center'}
            ,{field: 'id', title: '编号', align:'center', sort: true}
            ,{field: 'contactTime', title: '交往时间', align:'center'}
            ,{field: 'address', title: '地址', align:'center'}
            ,{field: 'overview', title: '概述', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#contactListBar",fixed: 'right',minWidth:150}
        ]]
    });





    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});