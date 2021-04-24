layui.use(['table','layer','treetable'],function () {
    var layer =parent.layer === undefined ?layui.layer:top.layer,
        $ = layui.jquery,
        table = layui.table;
    var treeTable = layui.treetable;

    treeTable.render({
        treeColIndex:1,
        treeSpid:-1,
        treeIdName:"id",
        treePidName:"parentId",
        elem: '#munu-table'
        ,url: ctx+'/module/list' //数据接口
        ,toolbar:'#toolbarDemo'
        ,treeDefaultClose:true
        ,page: true //开启分页,
        ,cols: [[ //表头
            {type: 'numbers'}
            ,{field: 'moduleName', title: '菜单名称',minWidth:100}
            ,{field: 'optValue', title: '授权码'}
            ,{field: 'url', title: '菜单url'}
            ,{field: 'createDate', title: '创建时间'}
            ,{field: 'updateDate', title: '更新时间'}
            ,{field: 'grade', width:80,align:'center',title:'类型',templet: function (d) {
                    if (d.grade==0) {
                        return '<span class="layui-badge layui-bg-blue">目录</span>'
                    }
                    if (d.grade==1) {
                        return '<span class="layui-badge-rim">菜单</span>'
                    }
                    if (d.grade==2) {
                        return '<span class="layui-badge layui-bg-gray">按钮</span>'
                    }
                }}
            ,{ title: '操作', align:'center',templet:"#auth-state",fixed: 'right',minWidth:150}
        ]]
    });


    table.on('toolbar(munu-table)',function (data) {
        if (data.event==='expand'){
            treeTable.expandAll("#munu-table")
        }else if(data.event==='fold'){
            treeTable.foldAll("#munu-table")
        }else if(data.event==='add'){

        }
    })


    table.on('tool(munu-table)',function (obj) {
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