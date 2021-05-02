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
        elem: '#contriList'
        ,height: 'full-125'
        ,url: ctx+'/customer/queryContribute' //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox',fixed:'left',width:50}
            ,{field: 'name', title: '客户名',minWidth:50, align:'center'}
            ,{field: 'total', title: '总金额(￥)',minWidth:50, align:'center'}
        ]]
    });


    $(".search_btn").click(function () {
        /**
         * 表格重载 多条件查询
         */
        tableIns.reload({
            where: {
                customerName: $("input[name='customerName']").val()
                ,type: $("#type").val()  // 金额区间
                ,time: $("input[name='time']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })


});