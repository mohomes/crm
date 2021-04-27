$(function () {
    // 加载资源树
    loadModuleData();
});
var zTreeObj;
function loadModuleData(){
    $.ajax({
        type:"get",
        // 查询所有的资源列表时 传递角色资源ID 查询当前角色对应的已经授权的资源
        url:ctx+"/module/queryAllModules?roleId="+$("[name='roleId']").val()
        ,dataType:'json',
        success:function (data) {

            // 参数配置
            var setting={
                check: {
                    enable: true
                },
               data: {
                   simpleData: {
                       enable: true
                   }
               },
                callback:{
                    onCheck: zTreeOnCheck   // 选中效果
                }
            };
            zTreeObj = $.fn.zTree.init($("#test1"),setting,data);
        }
    })


}

function zTreeOnCheck(event,treeId,treeNode){
    // alert(treeNode.tId+","+treeNode.name+","+treeNode.checked)
    var nodes = zTreeObj.getCheckedNodes(true)
    if (nodes.length > 1){
        var roleId = $("[name='roleId']").val()

        var mIds = "mIds="

        for (var i = 0; i < nodes.length; i++) {
            var module = nodes[i]
            if (i < nodes.length-1){
                mIds+=module.id+"&mIds="
            }else{
                // 如果循环拼接到最后一个元素 则不再拼接“ids"
                mIds+=module.id
            }
        }

    }

    //
    $.ajax({
        type:"post",
        url:ctx + "/role/addGrant",
        data: mIds+"&roleId="+roleId,
        dataType:'json'
        ,success(data){
            console.log(data)
        }
    })


}

