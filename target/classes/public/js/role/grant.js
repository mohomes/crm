$(function () {
    // 加载资源树
    loadModuleData();
});

function loadModuleData(){
    $.ajax({
        type:"get",
        url:ctx+"/module/queryAllModules"
        ,dataType:'json',
        success:function (data) {
            var zTreeObj;

            // 参数配置
            var setting={
                check: {
                    enable: true
                },
               data: {
                   simpleData: {
                       enable: true
                   }
               }
            };
            zTreeObj = $.fn.zTree.init($("#test1"),setting,data);
        }
    })
}