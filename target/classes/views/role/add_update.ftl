

<!doctype html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="id" value="${(role.id)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="roleName" value="${(role.roleName)!}" lay-verity="required"
                   id="customerName"
                   class="layui-input"
                   placeholder="请输入角色名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">角色描述</label>
        <div class="layui-input-block">
            <input type="text" name="roleRemark" value="${(role.roleRemark)!}" lay-verity="required"
                   id="chanceSource" class="layui-input"
                   placeholder="请输入角色描述">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateRole">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/role/add.update.js"></script>
</body>
</html>