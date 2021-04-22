layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#saleChanceList'
        ,height: 'full-125'
        ,url: ctx+'/sale_chance/list?flag=1' //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', align:'center', sort: true, fixed: 'left'}
            ,{field: 'chanceSource', title: '机会来源', align:'center'}
            ,{field: 'customerName', title: '客户名称', align:'center'}
            ,{field: 'cgjl', title: '成功几率', align:'center'}
            ,{field: 'overview', title: '概要', align:'center'}
            ,{field: 'linkMan', title: '联系人', align:'center'}
            ,{field: 'linkPhone', title: '联系号码', align:'center'}
            ,{field: 'description', title: '描述', align:'center'}
            ,{field: 'createMan', title: '创建人', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{field: 'devResult', title: '开发状态 ', align:'center',templet:function (d) {
                    return formatDevResult(d.devResult)
                }}
            ,{ title: '操作', align:'center',templet:"#op",fixed: 'right',minWidth:150}
        ]]
    });

    /**
     * 开发状态格式化
     *  0=未开发
     *  1=开发中
     *  2=开发成功
     *  3=开发失败
     * @param data
     * @returns {string}
     */
    function formatDevResult(data) {
        if (data == 0){
            return "<div style='color: skyblue'>未开发</div>"
        } else if(data == 1){
            return "<div style='color: orange'>开发中</div>"
        } else if(data ==2){
            return "<div style='color: green'>开发成功</div>"
        }else if(data == 3){
            return "<div style='color: red'>开发失败</div>"
        }else{
            return "<div style='color: blue'>未知</div>"
        }
    }

    $(".search_btn").click(function () {
        /**
         * 表格重载 多条件查询
         */
        tableIns.reload({
            where: {
                customerName: $("[name='customerName']").val()
                ,createMan: $("[name='createMan']").val()
                ,devResult: $("#devResult").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    table.on('tool(saleChances)',function (obj) {
        var data= obj.data
        if (obj.event === 'dev'){
            // 打开计划项开发与详情页面
            openCusDevPlanDialog('计划项数据开发',data.id);
        }else if(obj.event === 'info'){
            openCusDevPlanDialog('计划项数据维护',data.id);
        }
    })

    function openCusDevPlanDialog(title,id){
        var url = ctx + "/cus_dev_plan/toCusDevPlanPage/"+id
        //iframe层

        layui.layer.open({
            type: 2,
            title: title,
            area: ['750px', '550px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }


});