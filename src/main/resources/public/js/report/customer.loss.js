layui.use(['table','layer','laydate'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate;

    laydate.render({
        elem:"#time"
    })

    var tableIns= table.render({
        id:"listTable",
        elem: '#lossList'
        ,height: 'full-125'
        ,url: ctx+'/customer_loss/list?state=1' //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox',fixed:'left'}
            ,{field: 'cusNo', title: '客户编码', align:'center'}
            ,{field: 'cusName', title: '客户名字', align:'center'}
            ,{field: 'cusManager', title: '客户经理', align:'center'}
            ,{field: 'lastOrderTime', title: '最后下单时间', align:'center'}
            ,{field: 'lossReason', title: '流失原因', align:'center'}
            ,{field: 'confirmLossTime', title: '确认流失时间', align:'center'}
        ]]
    });


    $(".search_btn").click(function () {
        /**
         * 表格重载 多条件查询
         */
        tableIns.reload({
            where: {
                cusName: $("input[name='cusName']").val()
                ,cusNo: $("input[name='cusNo']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })


});