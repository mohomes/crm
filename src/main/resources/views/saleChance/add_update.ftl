<!doctype html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="id" value="${(saleChance.id)!}">
    <#-- 设置指派人的ID -->
    <input type="hidden" id="assignManId" value="${(saleChance.assignMan)!}">
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">客户名称</label>
        <div class="layui-input-block">
            <input type="text" name="customerName" value="${(saleChance.customerName)!}" lay-verity="required"
                   id="customerName"
                   class="layui-input"
                   placeholder="请输入客户名称">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会来源</label>
        <div class="layui-input-block">
            <input type="text" name="chanceSource" value="${(saleChance.chanceSource)!}" lay-verity="required" id="chanceSource" class="layui-input"
                   placeholder="请输入机会来源">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系人</label>
        <div class="layui-input-block">
            <input type="text" name="linkMan" value="${(saleChance.linkMan)!}" lay-verity="required" id="linkMan" class="layui-input"
                   placeholder="请输入联系人">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-block">
            <input type="text" name="linkPhone" value="${(saleChance.linkPhone)!}" lay-verity="required" id="linkPhone" class="layui-input"
                   placeholder="请输入联系电话">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">概要</label>
        <div class="layui-input-block">
            <input type="text" name="overview" value="${(saleChance.overview)!}" lay-verity="required" id="overview" class="layui-input"
                   placeholder="请输入概要">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">成功几率(%)</label>
        <div class="layui-input-block">
            <input type="text" name="cgjl" value="${(saleChance.cgjl)!}" lay-verity="required" id="cgjl" class="layui-input"
                   placeholder="请输入成功几率">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">机会描述</label>
        <div class="layui-input-block">
                <textarea placeholder="请输入机会描述信息" name="description"
                          class="layui-textarea">${(saleChance.description)!}</textarea>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">指派给</label>
        <div class="layui-input-block">
            <select name="assignMan" id="assignMan">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateSaleChance">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/saleChance/add.update.js"></script>
</body>
</html>