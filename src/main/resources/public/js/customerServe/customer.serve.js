layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#customerServeList'
        ,height: 'full-125'
        ,url: ctx+'/customer_serve/list?state=fw_001' //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox',fixed:'center'}
            ,{field: 'id', title: '编号', align:'center'}
            ,{field: 'customer', title: '客户名', align:'center'}
            ,{field: 'dicValue', title: '服务类型', align:'center'}
            ,{field: 'overview', title: '概要信息', align:'center'}
            ,{field: 'createPeople', title: '创建人', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
        ]]
    });

    $(".search_btn").click(function () {
        /**
         * 表格重载 多条件查询
         */
        tableIns.reload({
            where: {
                customer: $("[name='customer']").val()
                ,serveType: $("#type").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    table.on('toolbar(customerServes)',function(obj){
        if (obj.event === "add"){
            openCustomerServeDialog("<h3>服务管理 - 服务创建</h3>")
        }
    })

    function openCustomerServeDialog(title){
        layui.layer.open({
            type:2,
            title:title,
            area:['700px','500px'],
            content: ctx +"/customer_serve/openCustomerServeDialog",
            maxmin:true
        })
    }


});