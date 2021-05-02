<!DOCTYPE html>
<html>
<head>
    <title>服务反馈</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">

                <div class="layui-input-inline">
                    <input type="text" name="customer" class="layui-input searchVal" placeholder="客户名称"/>
                </div>

                <div class="layui-input-inline">
                    <select name="type" id="type">
                        <option value="">请选择</option>
                        <option value="6">咨询</option>
                        <option value="7">建议</option>
                        <option value="8">投诉</option>
                    </select>
                </div>

                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>搜索
                </a>
            </div>
        </form>
    </blockquote>

    <table id="customerServeBackList" class="layui-table" lay-filter="customerServeBacks"></table>


    <#-- 头部工具栏 -->
    <script type="text/html" id="customerServeListBar">
        <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs" lay-event="back">
            反馈
        </a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/customerServe/customer.serve.back.js"></script>
</body>
</html>