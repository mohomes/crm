layui.use(['table','layer'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns= table.render({
        id:"listTable",
        elem: '#userList'
        ,height: 'full-125'
        ,url: ctx+'/user/list' //数据接口
        ,toolbar:'#toolbarDemo'
        ,cellMinWidth:95
        ,page: true //开启分页,
        ,limit:10
        ,limits:[10,20,30]
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', align:'center', sort: true, fixed: 'left'}
            ,{field: 'userName', title: '用户名', align:'center'}
            ,{field: 'trueName', title: '真实姓名', align:'center'}
            ,{field: 'email', title: '用户邮箱', align:'center'}
            ,{field: 'phone', title: '手机号码', align:'center'}
            ,{field: 'createDate', title: '创建时间', align:'center'}
            ,{field: 'updateDate', title: '更新时间', align:'center'}
            ,{ title: '操作', align:'center',templet:"#userListBar",fixed: 'right',minWidth:150}
        ]]
    });


    $(".search_btn").click(function () {
        /**
         * 表格重载 多条件查询
         */
        tableIns.reload({
            where: {
                userName: $("[name='userName']").val()
                ,phone: $("[name='phone']").val()
                ,email: $("[name='email']").val()
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    })

    table.on('toolbar(users)',function (data) {
        if (data.event==='add'){
            openAddOrUpdateUserDialog();
        }else if(data.event==='delete'){
            deleteUser(data);
        }
    })


    /**
     * 删除多条记录
     * @param data
     */
    function deleteUser(data){
        var checkStatus = table.checkStatus("listTable")
        // 判断用户是否选择了记录
        var userData = checkStatus.data
        if (userData.length < 1) {
            layer.msg("请选择要删除的记录",{icon:5})
            return;
        }

        layer.confirm("您确定要删除选中的记录吗？",{icon:3,title:"用户管理"},function (index) {
            layer.close(index)

            var ids = "ids="
            //循环
            for (let i = 0; i < userData.length; i++) {
                if (i<userData.length-1){
                    ids = ids + userData[i].id + "&ids="
                }else{
                    ids = ids + userData[i].id;
                }
            }

            $.ajax({
                type:"POST",
                url:ctx + "/user/delete",
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

    function openAddOrUpdateUserDialog(userId){
        var url = ctx + "/user/toAddOrUpdateUserPage"
        //iframe层
        var title = "<h2>用户管理 - 添加用户信息</h2>"
        if (userId!=null && userId!=''){
            title = "<h2>用户管理 - 更新用户信息</h2>"
            url += "?userId="+userId;
        }
        layui.layer.open({
            type: 2,
            title: title,
            area: ['650px', '400px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }

    table.on('tool(users)',function (obj) {
        var data = obj.data
        if(obj.event === "edit"){  // 编辑操作
            var userId = data.id;
            openAddOrUpdateUserDialog(userId)
        }else if(obj.event === "del"){
            layer.confirm("是否确认删除这条记录吗？",{icon:3,title:"用户管理"},function (index) {
                layer.close(index)
                $.ajax({
                    type:"POST",
                    url:ctx + "/user/delete",
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