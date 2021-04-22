layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#saleChanceList'
        ,height: 'full-125'
        ,url: ctx+'/sale_chance/list' //数据接口
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
            ,{field: 'uname', title: '分配人', align:'center'}
            ,{field: 'assignTime', title: '分配时间', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{field: 'state', title: '分配状态', align:'center',templet: function (d) {
                return formatState(d.state)
                }}
            ,{field: 'devResult', title: '开发状态 ', align:'center',templet:function (d) {
                return formatDevResult(d.devResult)
                }}
            ,{ title: '操作', align:'center',templet:"#saleChanceListBar",fixed: 'right',minWidth:150}
        ]]
    });

    /**
     * 格式化状态值
     * @param data
     */
    function formatState(data) {
        if (data==0){
            return "<div style='color: skyblue'>未分配</div>"
        } else if(data == 1){
            return "<div style='color: green'>已分配</div>"
        } else{
            return "<div style='color: red'>未知</div>"
        }
    }

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
                ,state: $("#state").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    table.on('toolbar(saleChances)',function (data) {
        if (data.event==='add'){
            openSaleChanceDialog();
        }else if(data.event==='delete'){
            deleteSaleChance(data);
        }
    })


    /**
     * 删除多条记录
     * @param data
     */
    function deleteSaleChance(data){
        var checkStatus = table.checkStatus("listTable")
        // 判断用户是否选择了记录
        var saleChanceData = checkStatus.data
        if (saleChanceData.length < 1) {
            layer.msg("请选择要删除的记录",{icon:5})
            return;
        }

        layer.confirm("您确定要删除选中的记录吗？",{icon:3,title:"营销机会管理"},function (index) {
            layer.close(index)

            var ids = "ids="
            //循环
            for (let i = 0; i < saleChanceData.length; i++) {
                if (i<saleChanceData.length-1){
                    ids = ids + saleChanceData[i].id + "&ids="
                }else{
                    ids = ids + saleChanceData[i].id;
                }
            }

            $.ajax({
                type:"POST",
                url:ctx + "/sale_chance/delete",
                data:ids,
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



    function openSaleChanceDialog(saleChanceId){
        var url = ctx + "/sale_chance/toSaleChancePage"
        //iframe层
        var title = "<h2>营销机会管理 - 添加营销机会</h2>"
        if (saleChanceId!=null && saleChanceId!=''){
            title = "<h2>营销机会管理 - 更新营销机会</h2>"
            url += '?saleChanceId='+saleChanceId;
        }


        layui.layer.open({
            type: 2,
            title: title,
            area: ['500px', '620px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }

    table.on('tool(saleChances)',function (obj) {
        var data = obj.data
        if(obj.event === "edit"){  // 编辑操作
            var saleChanceId = data.id;
            openSaleChanceDialog(saleChanceId)
        }else if(obj.event === "del"){
            layer.confirm("是否确认删除这条记录吗？",{icon:3,title:"营销机会管理"},function (index) {
                layer.close(index)
                $.ajax({
                    type:"POST",
                    url:ctx + "/sale_chance/delete",
                    data:{
                        ids:data.id
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
    })


});