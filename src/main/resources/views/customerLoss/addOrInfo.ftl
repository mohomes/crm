<!doctype html>
<html>
<head>
    <title>暂缓管理</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<div class="layui-col-md12">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <input type="hidden" name="id" value="${(customerLoss.id)!}">
                <div class="layui-form-item  layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">客户名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="cusName" value="${(customerLoss.cusName)!}"
                                   readonly="readonly"
                                   id="name"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">客户编号</label>
                        <div class="layui-input-block">
                            <input type="text" name="cusNo" value="${(customerLoss.cusNo)!}" readonly="readonly"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <#if customerLoss.state==1>
                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">确认流失时间</label>
                        <div class="layui-input-block">
                            <input type="text" name="confirmLossTime" value="${(customerLoss.confirmLossTime?string
                            ("yyyy-MM-dd HH:mm:ss"))!}"
                                   class="layui-input" readonly="readonly">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">流失原因</label>
                        <div class="layui-input-block">
                            <input type="text" name="lossReason" value="${(customerLoss.lossReason)!}" readonly="readonly"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                </#if>
            </form>
        </div>
    </div>
</div>
<div class="layui-col-md12">
    <table id="reqList" class="layui-table" lay-filter="reqs"></table>
</div>



<#-- 头部工具栏 -->
<#if customerLoss.state=0>
    <script type="text/html" id="reqBar">
        <a class="layui-btn layui-btn-xs" id="info" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" id="info" lay-event="del">删除</a>
    </script>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
            <i class="layui-icon">&#xe608;</i>
            添加暂缓
        </a>
        <a class="layui-btn layui-bg-orange delNews_btn" lay-event="confirm">
            <i class="layui-icon">&#xe608;</i>
            确认流失
        </a>
    </div>
</script>
</#if>

<script type="text/javascript" src="${ctx}/js/customerLoss/customer.req.js"></script>
</body>
</html>