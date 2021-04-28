<!doctype html>
<html>
<head>
    <title>订单详情查看</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<div class="layui-col-md12">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <input type="hidden" name="id" value="${(customerOrder.id)!}">
                <div class="layui-form-item  layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">订单编号</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" value="${(customerOrder.order_no)!}" readonly="readonly"
                                   id="name"
                                   class="layui-input"
                                   placeholder="请输入订单编号">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">总金额</label>
                        <div class="layui-input-block">
                            <input type="text" name="fr" value="${(customerOrder.total)!}" readonly="readonly"
                                   id="fr"
                                   class="layui-input"
                                   placeholder="请输入总金额">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">物流地址</label>
                        <div class="layui-input-block">
                            <input type="text" name="address" value="${(customerOrder.address)!}"
                                   id="address"
                                   class="layui-input" readonly="readonly"
                                   placeholder="请输入物流地址">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">支付状态</label>
                        <div class="layui-input-block">
                            <input type="text" name="phone" value="${(customerOrder.status)!}" readonly="readonly"
                                   id="phone"
                                   class="layui-input"
                                   placeholder="请输入支付状态">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="layui-col-md12">
    <table id="orderList" class="layui-table" lay-filter="orders"></table>
</div>

<script type="text/html" id="orderListBar">
    <a class="layui-btn layui-btn-xs" id="info" lay-event="info">订单详情</a>
</script>

<script type="text/javascript" src="${ctx}/js/customer/customer.order.detail.js"></script>
</body>
</html>