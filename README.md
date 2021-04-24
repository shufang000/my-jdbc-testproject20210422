# MySQL事务的隔离级别

> ACID是事务的特性其中 isolation就是MySQL的隔离级别
- read uncommitted;  
    - 在脏读、不可重复度、幻读这几个并发问题中，没有解决一个
- read committed;  
    - 解决了脏读;
- repeatable read; 
    - 解决了脏读(update) 和 不可重复度(insert)
- serializable; 
    - 都解决了，但是性能差；

 **_通常一致性与隔离性是成反比的_**
 
 Oracle的事务隔离级别只有2种
 - read committed
 - serializable
 
 
```sql
 -- 在 MySQL中查看隔离级别
select @@tx_isolation ;

 -- 在MySQL中创建一个用户和密码
create user shufang identified by '888888';

-- 设置MySQL的隔离级别
set transaction isolation read committed; --当前连接的隔离级别
set global transaction isolation read committed; --数据库系统的隔离级别
-- 给MySQL用户设置所有权限
grant all privileges on *.* to shufang@'%' identified by '888888';
-- 给MySQL用户设置某个库的权限
grant select,insert,update,delete on hello.* to shufang@localhost identified by '888888';

 
```