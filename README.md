## 版本问题

高于下面版本的 Sharding 在与Druid 集成时会出现问题。
参考博客：https://zhouzhixiang.blog.csdn.net/article/details/104889534

```xml
<dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>sharding-jdbc-spring-boot-starter</artifactId>
    <version>4.0.0-RC1</version>
</dependency>
```
解决方案：
1、通过手动注入数据源，上述博客中介绍。该项目下并未进行测试。
2、排除Druid数据源自动配置，这里好像与Druid数据源的注入方式有关。

```
@SpringBootApplication(exclude = { DruidDataSourceAutoConfigure.class })
```


## 文档

### 官方主页
https://shardingsphere.apache.org/document/legacy/4.x/document/cn/overview/

### yaml配置文档
https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc/configuration/config-yaml/

## 笔记
理解前提：所有的数据表都存储在一个数据库下，当遇到物理或者并发瓶颈时，考虑分库分表的策略。

参考博客：https://blog.csdn.net/weixin_44062339/article/details/100491744

### 1.分表
### 1.1 水平分表：目的是为解决单表数据量大的问题。水平分表是在同一个数据库内，把同一个表的数据按一定规则拆到多个表中。

	它带来的提升是：
	1.优化单一表数据量过大而产生的性能问题。
	2.避免IO争抢并减少锁表的几率。

库内的水平分表，解决了单一表数据量过大的问题，分出来的小表中只包含一部分数据，从而使得单个表的数据量变小，提高检索性能。

### 1.2 垂直分表：目的是为解决单表字段数据过多的问题。将一个表按照字段分成多表，每个表存储其中一部分字段。
    1.为了避免IO争抢并减少锁表的几率。
    为什么大字段IO效率低：
        第一是由于数据量本身大，需要更长的读取时间。
        第二是跨页，页是数据库存储单位，很多查找及定位操作都是以页为单位，单页内的数据行越多数据库整体性能越好，而大字段占用空间大，单页内存储行数少，因此IO效率较低。
        第三，数据库以行为单位将数据加载到内存中，这样表中字段长度较短且访问频率较高，内存能加载更多的数据，命中率更高，减少了磁盘IO，从而提升了数据库性能。
    2.充分发挥热门数据的操作效率。
    3.通常我们按以下原则进行垂直拆分:
    	1.把不常用的字段单独放在一张表;
    	2.把text，blob等大字段拆分出来放在附表中;
    	3.经常组合查询的列放在一张表中;

### 2.分库
### 2.2 垂直分库：垂直分库是指按照业务（如：配置相关，数据相关等）将表进行分类，分布到不同的数据库上面，每个库可以放在不同的服务器上，它的核心理念是专库专用。

通过垂直分表性能得到了一定程度的提升，但是还没有达到要求，并且磁盘空间也快不够了，因为数据还是始终限制在一台服务器，库内垂直分表只解决了单一表数据量过大的问题，但没有将表分布到不同的服务器上，因此每个表还是竞争同一个物理机的CPU、内存、网络IO、磁盘。

    它带来的提升是：
        1.解决业务层面的耦合，业务清晰。
        2.能对不同业务的数据进行分级管理、维护、监控、扩展等。
        3.高并发场景下，垂直分库一定程度的提升IO、数据库连接数、降低单机硬件资源的瓶颈。

垂直分库通过将表按业务分类，然后分布在不同数据库，并且可以将这些数据库部署在不同服务器上，从而达到多个服务器共同分摊压力的效果，但是依然没有解决单表数据量过大的问题。

### 2.1 水平分库：同一个表的数据按一定规则拆到不同的数据库中，每个库可以放在不同的服务器上。垂直分库是把不同表拆到不同数据库中，它是对数据行的拆分，不影响表结构。

    它带来的提升是：
        1.解决了单库大数据，高并发的性能瓶颈。
        2.提高了系统的稳定性及可用性。稳定性体现在IO冲突减少，锁定减少，可用性指某个库出问题，部分可用。

    带来的问题：
        但由于同一个表被分配在不同的数据库，需要额外进行数据操作的路由工作，因此大大提升了系统复杂度。


### 3.读写分离
目前读写分离是基于MySQL的主从复制，完成数据的同步。
主表主要负责增删改，而从库只允许查询，当主表发生改变时，从库会自动同步更新数据。
这种方式存在一个明显的问题：会出现数据不同步的情况。