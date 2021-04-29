
-- 客户自创建起，超过六个月未与企业生产任何订单或者客户最后下单时间距离现在时间
-- 超过六个月的客户定义为流失客户
-- 查询没有订单的记录的客户
-- 查询最后的订单时间距离现在时间超过6个月的客户 并去除重复数据
-- 具体实现: 查询产生过订单记录并且最后的下单时间距离现在时间不超过6个月的客户，然后从客户表中排除客户
-- DATE_ADD() 函数 向日期添加指定的时间间隔
-- 格式：DATE_ADD(date,INTERVAL express type)
-- date 合法的日期表达式
-- INTERVAL
-- express 时间间隔
-- type 具体的类型 可以是年 YEAR MONTH DAY
-- 向当前时间添加6个月
-- DATA_ADD(now(),INTERVAL 6 MONTH)
select * from t_customer c
where c.is_valid = 1
and c.state=0 and DATE_ADD(c.create_date,INTERVAL 6 MONTH) < now()
and c.id not in (select distinct cus_id from t_customer_order o
                 where o.is_valid = 1 and o.state = 1 and
                         date_add(o.order_date,INTERVAL 6 MONTH) > now())
;