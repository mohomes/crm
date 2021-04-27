<!doctype html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单名</label>
        <div class="layui-input-block">
            <input type="text" name="moduleName" value="" lay-verity="required"
                   id="moduleName"
                   class="layui-input"
                   placeholder="请输入菜单名">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单样式</label>
        <div class="layui-input-block">
            <input type="text" name="moduleStyle" placeholder="请输入菜单样式" id="moduleStyle" class="layui-input"
                   >
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" name="orders" placeholder="请输入排序值"  id="orders"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">权限码</label>
        <div class="layui-input-block">
            <input type="text" name="optValue" placeholder="请输入菜单权限码" lay-verity="required" id="optValue" class="layui-input"
                   >
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">菜单级别</label>
        <div class="layui-input-block">
            <#if grade??>
                <select name="grade" id="">
                    <option value="0"<#if grade==0>selected="selected"</#if> >一级菜单</option>
                    <option value="1"<#if grade==1>selected="selected"</#if> >二级菜单</option>
                    <option value="2"<#if grade==2>selected="selected"</#if> >三级菜单</option>
                </select>
            </#if>
        </div>
    </div>
    <#if grade==1>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">菜单url</label>
            <div class="layui-input-block">
                <input type="text" name="url" placeholder="请输入菜单url" lay-verity="required" id="url"
                       class="layui-input"
                       >
            </div>
        </div>
    </#if>

    <#--添加根级菜单-->
    <input type="hidden" name="parentId" value="${parentId}">
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addModule">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/module/add.update.js"></script>
</body>
</html>