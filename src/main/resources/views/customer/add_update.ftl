

<!doctype html>
<html>
<head>
    <#include "../common.ftl">
</head>

<body class="childrenBody">
<form class="layui-form" action="" style="width: 80%">
    <input type="hidden" name="id" value="${(customer.id)!}">
    <div class="layui-form-item layui-row">
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
            <label class="layui-form-label">区域</label>
            <div class="layui-input-block">
                <input type="text" name="area" value="${(customer.area)!}"
                       id="area" class="layui-input"
                       placeholder="请输入区域">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">客户经理</label>
            <div class="layui-input-block">
                <input type="text" name="cusManager" value="${(customer.cusManager)!}"
                       id="cusManager" class="layui-input"
                       placeholder="请输入客户经理">
            </div>
        </div>
    </div>
    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">客户级别</label>
            <div class="layui-input-block">
                <select name="level" id="level">
                    <option value="">请选择</option>
                    <option value="战略合作伙伴">战略合作伙伴</option>
                    <option value="大客户">大客户</option>
                    <option value="重点开发客户">重点开发客户</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">信用度</label>
            <div class="layui-input-block">
                <input type="text" name="xyd" value="${(customer.xyd)!}"
                       id="xyd"
                       class="layui-input"
                       placeholder="请输入客户信用等级">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">邮编</label>
            <div class="layui-input-block">
                <input type="text" name="postCode" value="${(customer.postCode)!}"
                       id="postCode"
                       class="layui-input"
                       placeholder="请输入客户邮编">
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
            <label class="layui-form-label">传真</label>
            <div class="layui-input-block">
                <input type="text" name="fax" value="${(customer.fax)!}"
                       id="fax"
                       class="layui-input"
                       placeholder="请输入客户传真">
            </div>
        </div>

    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">网站</label>
            <div class="layui-input-block">
                <input type="text" name="webSite" value="${(customer.webSite)!}"
                       id="webSite"
                       class="layui-input"
                       placeholder="请输入客户网站网址">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">注册资金</label>
            <div class="layui-input-block">
                <input type="text" name="zczj" value="${(customer.zczj)!}"
                       id="zczj"
                       class="layui-input"
                       placeholder="请输入注册资金">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">开户行</label>
            <div class="layui-input-block">
                <input type="text" name="khyh" value="${(customer.khyh)!}"
                       id="khyh"
                       class="layui-input"
                       placeholder="请输入客户开户行">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">开户账号</label>
            <div class="layui-input-block">
                <input type="text" name="khzh" value="${(customer.khzh)!}"
                       id="khzh"
                       class="layui-input"
                       placeholder="请输入开户账号">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">国税</label>
            <div class="layui-input-block">
                <input type="text" name="gsdjh" value="${(customer.gsdjh)!}"
                       id="gsdjh"
                       class="layui-input"
                       placeholder="请输入国税">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">地税</label>
            <div class="layui-input-block">
                <input type="text" name="dsdjh" value="${(customer.dsdjh)!}"
                       id="dsdjh"
                       class="layui-input"
                       placeholder="请输入地税">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">年营业额</label>
            <div class="layui-input-block">
                <input type="text" name="nyye" value="${(customer.nyye)!}" lay-verity="required"
                       id="nyye"
                       class="layui-input"
                       placeholder="请输入客户年营业额">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCustomer">确认</button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/customer/add.update.js"></script>
</body>
</html>