<!DOCTYPE html>
<html>
<head>
    <title>客户贡献分析</title>
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
                        <option value="1">0-1000</option>
                        <option value="2">1000-3000</option>
                        <option value="3">3000-5000</option>
                        <option value="4">5000以上</option>
                    </select>
                </div>

                <div class="layui-input-inline">
                    <input type="text" name="time"
                           id="time" class="layui-input searchVal"
                           placeholder="下单时间" readonly="readonly"/>
                </div>
                <a class="layui-btn search_btn" data-type="reload">
                    <i class="layui-icon">&#xe615;</i>搜索
                </a>

            </div>
        </form>
    </blockquote>

    <table id="contriList" class="layui-table" lay-filter="contris"></table>


    <#-- 头部工具栏 -->
    <script type="text/html" id="toolbarDemo">

    </script>
</form>
<script type="text/javascript" src="${ctx}/js/report/customer.contris.js"></script>
</body>
</html>