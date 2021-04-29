<!doctype html>
<html>
<head>
    <title>客户交往记录</title>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<div class="layui-col-md12">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form">
                <input type="hidden" name="id" value="${(customer.id)!}">
                <div class="layui-form-item  layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">客户名称</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" value="${(customer.name)!}"
                                   id="name"
                                   class="layui-input"
                                   placeholder="请输入客户名称">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">法人</label>
                        <div class="layui-input-block">
                            <input type="text" name="fr" value="${(customer.fr)!}"
                                   id="fr"
                                   class="layui-input"
                                   placeholder="请输入法人代表">
                        </div>
                    </div>
                </div>

                <div class="layui-form-item layui-row">
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">客户地址</label>
                        <div class="layui-input-block">
                            <input type="text" name="address" value="${(customer.address)!}"
                                   id="address"
                                   class="layui-input"
                                   placeholder="请输入客户地址">
                        </div>
                    </div>
                    <div class="layui-col-xs6">
                        <label class="layui-form-label">联系电话</label>
                        <div class="layui-input-block">
                            <input type="text" name="phone" value="${(customer.phone)!}"
                                   id="phone"
                                   class="layui-input"
                                   placeholder="请输入客户联系电话">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="layui-col-md12">
    <table id="contactList" class="layui-table" lay-filter="contacts"></table>
</div>


<script type="text/javascript" src="${ctx}/js/customer/customer.contact.js"></script>
</body>
</html>