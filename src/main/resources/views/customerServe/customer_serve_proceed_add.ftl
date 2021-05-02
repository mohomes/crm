<!doctype html>
<html>
<head>
    <title>服务处理</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="id" value="${(customerServe.id)!}">
    <input type="hidden" name="state" value="fw_003">
    <input type="hidden" name="man" value="${(customerServe.assigner)!}">
    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">服务类型</label>
            <div class="layui-input-block">
                <select name="serveType" id="serveType" disabled="disabled">
                    <option value="">请选择</option>
                    <option value="6" <#if customerServe.serveType=="6">selected="selected"</#if>>咨询</option>
                    <option value="7" <#if customerServe.serveType=="7">selected="selected"</#if>>建议</option>
                    <option value="8" <#if customerServe.serveType=="8">selected="selected"</#if>>投诉</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">客户</label>
            <div class="layui-input-block">
                <input type="text" name="customer"
                       id="customer" value="${(customerServe.customer)!}"
                       class="layui-input"
                       readonly="readonly">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <label class="layui-form-label">服务内容</label>
        <div class="layui-input-block">
            <textarea name="serviceRequest" readonly="readonly" class="layui-textarea">${(customerServe.serviceRequest)!}</textarea>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <label class="layui-form-label">服务概要</label>
        <div class="layui-input-block">
            <textarea name="overview" readonly="readonly" class="layui-textarea">${(customerServe.overview)!}</textarea>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">指派人</label>
            <div class="layui-input-block">
                <select name="assigner" id="assigner" disabled="disabled">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">指派时间</label>
            <div class="layui-input-block">
                <input type="text" name="assignTime"
                       id="assignTime" value="${(customerServe.assignTime?string("yyyy-MM-dd HH:mm:ss"))!}"
                       class="layui-input"
                       readonly="readonly">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <label class="layui-form-label">服务处理</label>
        <div class="layui-input-block">
            <textarea name="serviceProce" class="layui-textarea"></textarea>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="updateCustomerServeProceed">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>



<script type="text/javascript" src="${ctx}/js/customerServe/customer.serve.proceed.add.js"></script>
</body>
</html>