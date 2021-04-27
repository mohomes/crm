layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#customerList'
        ,height: 'full-125'
        ,url: ctx+'/customer/list' //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox',fixed:'center'}
            ,{field: 'id', title: '编号', align:'center'}
            ,{field: 'name', title: '客户名称', align:'center'}
            ,{field: 'fr', title: '法人', align:'center'}
            ,{field: 'khno', title: '客户编号', align:'center'}
            ,{field: 'area', title: '地区', align:'center'}
            ,{field: 'cusManager', title: '客户经理', align:'center'}
            ,{field: 'myd', title: '满意度', align:'center'}
            ,{field: 'level', title: '客户级别', align:'center'}
            ,{field: 'xyd', title: '信用度', align:'center'}
            ,{field: 'address', title: '详细地址', align:'center'}
            ,{field: 'postCode', title: '邮编', align:'center'}
            ,{field: 'phone', title: '电话', align:'center'}
            ,{field: 'webSite', title: '网站', align:'center'}
            ,{field: 'fax', title: '传真', align:'center'}
            ,{field: 'zczj', title: '注册资金', align:'center'}
            ,{field: 'yyzzzch', title: '营业执照', align:'center'}
            ,{field: 'khyh', title: '开户行', align:'center'}
            ,{field: 'khzh', title: '开户账号', align:'center'}
            ,{field: 'gsdjh', title: '国税', align:'center'}
            ,{field: 'dsdjh', title: '地税', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#customerListBar",fixed: 'right',minWidth:150}
        ]]
    });


    $(".search_btn").click(function () {
        /**
         * 表格重载 多条件查询
         */
        tableIns.reload({
            where: {
                customerName: $("[name='name']").val()
                ,customerNo: $("[name='khno']").val()
                ,level: $("[name='level']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    table.on('toolbar(customers)',function (data) {
        if (data.event==='add'){
            openRoleDialog();
        }else if(data.event==='grant'){
            var checkStatus = table.checkStatus(data.config.id)
            openRoleGrantPage(checkStatus);
        }
    })

    function openRoleGrantPage(data){
        console.log(data)
        // 判断用户是否选择了角色记录
        if (data.data.length==0){
            layer.msg("请选择要授权的角色",{icon:5})
            return;
        }
        if (data.data.length>1){
            layer.msg("暂不支持批量授权",{icon:5})
            return;
        }
        var title = "<h2>角色管理 - 授权菜单</h2>"
        var url = ctx + "/role/toGrantRolePage?roleId="+data.data[0].id
        layui.layer.open({
            type: 2,
            title: title,
            area: ['400px', '500px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }


    function openRoleDialog(roleId){
        var url = ctx + "/role/toAddOrUpdateRolePage"
        //iframe层
        var title = "<h2>角色管理 - 添加角色信息</h2>"
        if (roleId!=null && roleId!=''){
            title = "<h2>角色管理 - 更新角色信息</h2>"
            url += '?roleId='+roleId;
        }


        layui.layer.open({
            type: 2,
            title: title,
            area: ['500px', '400px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }

    table.on('tool(customers)',function (obj) {
        var data = obj.data
        if(obj.event === "edit"){  // 编辑操作
            var roleId = data.id;
            openRoleDialog(roleId)
        }else if(obj.event === "del"){
            layer.confirm("是否确认删除这条记录吗？",{icon:3,title:"角色管理"},function (index) {
                layer.close(index)
                $.ajax({
                    type:"POST",
                    url:ctx + "/role/delete",
                    data:{
                        id:data.id
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