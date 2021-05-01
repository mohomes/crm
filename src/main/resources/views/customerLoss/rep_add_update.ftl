

<!doctype html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="id" value="${(customerRep.id)!}">
    <#--流失客户的id-->
    <input type="hidden" name="lossId" value="${lossId!}">
    <div class="layui-form-item layui-row">
        <label class="layui-form-label">暂缓措施</label>
        <div class="layui-input-block">
            <input type="text" name="measure" value="${(customerRep.measure)!}"
                   class="layui-input"
                   placeholder="请输入暂缓措施">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateRep">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/customerLoss/rep.add.update.js"></script>
</body>
</html>