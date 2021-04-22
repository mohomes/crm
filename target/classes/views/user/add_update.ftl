<!doctype html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="id" value="${(userInfo.id)!}">
    <#-- 设置指派人的ID -->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="userName" value="${(userInfo.userName)!}" lay-verity="required"
                   id="userName"
                   class="layui-input"
                   placeholder="请输入用户名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">真实名字</label>
        <div class="layui-input-block">
            <input type="text" name="trueName" value="${(userInfo.trueName)!}" lay-verity="required"
                   id="trueName" class="layui-input"
                   placeholder="请输入真实名字">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" value="${(userInfo.email)!}" lay-verity="required" id="email"
                   class="layui-input"
                   placeholder="请输入联系邮箱">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" name="phone" value="${(userInfo.phone)!}" lay-verity="required" id="phone"
                   class="layui-input"
                   placeholder="请输入电话号码">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <select name="roleIds" xm-select="selectId">

            </select>
        </div>
    </div>



    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateUser">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/user/add.update.js"></script>
</body>
</html>