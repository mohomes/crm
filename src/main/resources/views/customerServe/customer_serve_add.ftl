<!doctype html>
<html>
<head>
    <title>暂缓管理</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="state" value="fw_001">
    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">服务类型</label>
            <div class="layui-input-block">
                <select name="serveType" id="serveType">
                    <option value="">请选择</option>
                    <option value="6">咨询</option>
                    <option value="7">建议</option>
                    <option value="8">投诉</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">客户</label>
            <div class="layui-input-block">
                <input type="text" name="customer"
                       id="customer"
                       class="layui-input"
                       placeholder="请输入客户姓名">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <label class="layui-form-label">服务内容</label>
        <div class="layui-input-block">
            <textarea name="serviceRequest" placeholder="请输入服务内容" class="layui-textarea"></textarea>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <label class="layui-form-label">服务概要</label>
        <div class="layui-input-block">
            <textarea name="overview" placeholder="请输入服务概要" class="layui-textarea"></textarea>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addCustomerServe">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>



<script type="text/javascript" src="${ctx}/js/customerServe/customer.serve.add.js"></script>
</body>
</html>