<!DOCTYPE html>
<html>
<head>
    <title>客户流失分析</title>
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
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>搜索
                </a>

            </div>
        </form>
    </blockquote>

    <table id="lossList" class="layui-table" lay-filter="losses"></table>


    <#-- 头部工具栏 -->
    <script type="text/html" id="toolbarDemo">

    </script>
</form>
<script type="text/javascript" src="${ctx}/js/report/customer.loss.js"></script>
</body>
</html>