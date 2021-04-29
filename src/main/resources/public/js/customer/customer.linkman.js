layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#linkManList'
        ,height: 'full-125'
        ,url: ctx+'/linkman/list?cusId='+$("[name='id']").val() //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'center'}
            ,{field: 'id', title: '编号', align:'center', sort: true}
            ,{field: 'linkName', title: '联系人', align:'center'}
            ,{field: 'sex', title: '性别', align:'center'}
            ,{field: 'zhiwei', title: '职位', align:'center'}
            ,{field: 'officePhone', title: '办公室电话', align:'center'}
            ,{field: 'phone', title: '手机号码', align:'center'}
            ,{field: 'ceateDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#linkManListBar",fixed: 'right',minWidth:150}
        ]]
    });




    // 关闭弹出层
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    })





});