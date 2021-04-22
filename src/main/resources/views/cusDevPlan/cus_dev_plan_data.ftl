<!doctype html>
<html>
<head>
    <title>客户开发计划管理</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<div class="layui-col-md12">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <input type="hidden" name="id" value="${(saleChance.id)!}">
                <#-- 设置指派人的ID -->
                <div class="layui-form-item  layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">客户名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="customerName" value="${(saleChance.customerName)!}" readonly="readonly"
                                   id="customerName"
                                   class="layui-input"
                                   >
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">机会来源</label>
                        <div class="layui-input-block">
                            <input type="text" name="chanceSource" value="${(saleChance.chanceSource)!}" readonly="readonly" id="chanceSource" class="layui-input"
                                   >
                        </div>
                    </div>
                </div>

                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">联系人</label>
                        <div class="layui-input-block">
                            <input type="text" name="linkMan" value="${(saleChance.linkMan)!}" readonly="readonly" id="linkMan" class="layui-input"
                                   >
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">联系电话</label>
                        <div class="layui-input-block">
                            <input type="text" name="linkPhone" value="${(saleChance.linkPhone)!}" readonly="readonly" id="linkPhone" class="layui-input"
                                   >
                        </div>
                    </div>

                </div>
                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">概要</label>
                        <div class="layui-input-block">
                            <input type="text" name="overview" value="${(saleChance.overview)!}" readonly="readonly" id="overview" class="layui-input"
                                   >
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">成功几率(%)</label>
                        <div class="layui-input-block">
                            <input type="text" name="cgjl" value="${(saleChance.cgjl)!}" readonly="readonly" id="cgjl"
                                   class="layui-input"
                                   >
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </div>
</div>
<div class="layui-col-md12">
    <table id="cusDevPlanList" class="layui-table" lay-filter="cusDevPlans"></table>
</div>
<#if saleChance.devResult==0 || saleChance.devResult==1>
    <script id="toolbarDemo" type="text/html">
        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
            <i class="layui-icon">&#xe608;</i>
            添加计划项
        </a>
        <a class="layui-btn layui-bg-orange addNews_btn" lay-event="success">
            <i class="layui-icon">&#xe608;</i>
            开发成功
        </a>
        <a class="layui-btn layui-bg-red addNews_btn" lay-event="failed">
            <i class="layui-icon">&#xe608;</i>
            开发失败
        </a>
    </script>

    <script type="text/html" id="cusDevPlanListBar">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>
</#if>
<script type="text/javascript" src="${ctx}/js/cusDevPlan/cus.dev.plan.data.js"></script>
</body>
</html>