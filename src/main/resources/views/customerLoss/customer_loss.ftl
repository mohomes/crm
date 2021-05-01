<!DOCTYPE html>
<html>
<head>
    <title>客户管理</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">

                <div class="layui-input-inline">
                    <input type="text" name="cusName" class="layui-input searchVal" placeholder="客户名称"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="cusNo" class="layui-input searchVal" placeholder="客户编号"/>
                </div>

                <div class="layui-input-inline">
                    <select name="state" id="state">
                        <option value="">请选择</option>
                        <option value="0">暂缓流失</option>
                        <option value="1">确认流失</option>
                    </select>
                </div>

                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>搜索
                </a>

            </div>
        </form>
    </blockquote>

    <table id="customerLossList" class="layui-table" lay-filter="customerLoss"></table>


    <#-- 操作工具栏 -->
    <script type="text/html" id="op">
        {{# if(d.state ===0){ }}
        <a href="javascript:;" class="layui-btn layui-btn-warm layui-btn-xs" lay-event="add">添加暂缓</a>
        {{# }else{ }}
        <a href="javascript:;" class="layui-btn layui-btn-warm layui-btn-xs" lay-event="info">详情</a>
        {{# } }}
    </script>

    <#-- 头部工具栏 -->
    <script type="text/html" id="toolbarDemo">

    </script>
</form>
<script type="text/javascript" src="${ctx}/js/customerLoss/customer.loss.js"></script>
</body>
</html>