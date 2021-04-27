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
            // 添加菜单 层级=0 父菜单-1
            openAddModuleDialog(0,-1)
        }
    })

    /**
     * 打开添加资源的对话框
     * @param grade
     * @param parentId
     */
    function openAddModuleDialog(grade,parentId){
        var title = "<h3>资源管理 - 添加资源</h3>"
        var url = ctx + "/module/toAddModulePage?grade="+grade+"&parentId="+parentId
        layui.layer.open({
            type: 2,
            title: title,
            area: ['700px', '450px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }


    table.on('tool(munu-table)',function (obj) {
        var data = obj.data
        if(obj.event === "edit"){  // 编辑操作
            var moduleId = data.id;
            openUpdateModuleDialog(moduleId)
        }else if(obj.event === "del"){
            layer.confirm("是否确认删除这条记录吗？",{icon:3,title:"资源管理"},function (index) {
                layer.close(index)
                $.ajax({
                    type:"POST",
                    url:ctx + "/module/delete",
                    data:{
                        id:data.id
                    },
                    success:function(result) {
                        if (result.code==200){
                            layer.msg(result.msg,{icon:6})
                            location.reload()
                        }else{
                            layer.msg(result.msg,{icon:5})
                        }
                    }
                })
            })
        }else if(obj.event=="add"){
            // 添加子项
            // 判断当前的层级 （如果是三级菜单 就不能添加）
            if(data.grade == 2){
                layer.msg("暂不支持添加四级菜单",{icon:5})
                return;
            }
            // 一级|二级菜单  grade = 当前层级+1  parentId = 当前资源的id
            openAddModuleDialog(data.grade+1,data.id)
        }
    })

    function openUpdateModuleDialog(id){
        //
        var title = "<h3>资源管理 - 修改资源</h3>"
        var url = ctx + "/module/toUpdateModulePage?id="+id
        layui.layer.open({
            type: 2,
            title: title,
            area: ['700px', '450px'],
            content: url //iframe的url
            ,maxmin: true // 最大化和最小化
        });
    }


});