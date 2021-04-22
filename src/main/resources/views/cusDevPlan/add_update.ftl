<!doctype html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="id" value="${(cusDevPlan.id)!}">
    <input type="hidden" name="saleChanceId" value="${sid!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">计划项内容</label>
        <div class="layui-input-block">
            <input type="text" name="planItem" value="${(cusDevPlan.planItem)!}" lay-verity="required"
                   id="planItem"
                   class="layui-input userName"
                   placeholder="请输入计划项内容">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">计划时间</label>
        <div class="layui-input-block">
            <#if (cusDevPlan.planDate)??>
            <input type="text" name="planDate" value="${(cusDevPlan.planDate)?string("yyyy-MM-dd")}"
                   lay-verity="required"
                   id="planDate" class="layui-input userName"
                   placeholder="请输入计划项时间">
                <#else>
            <input type="text" name="planDate" lay-verity="required"
                   id="planDate" class="layui-input userName"
                   placeholder="请输入计划项时间">
            </#if>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">执行效果</label>
        <div class="layui-input-block">
            <input type="text" name="exeAffect" value="${(cusDevPlan.exeAffect)!}" lay-verity="required" id="exeAffect"
                   class="layui-input"
                   placeholder="请输入执行效果">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCusDevPlan">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/cusDevPlan/add.update.js"></script>
</body>
</html>