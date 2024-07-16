## 介绍
### 该项目是基于SpringBoot+多数据源+shardingJDBC+达梦数据库的测试项目，分库分表组件使用的shardingsphere5.4.1
### 配置文档可参考：https://shardingsphere.apache.org/document/5.4.1/cn/user-manual/shardingsphere-jdbc/yaml-config/

##使用
### 达梦数据表初始化
#### 达梦数据库安装可参考达梦官方文档：https://eco.dameng.com/document/dm/zh-cn/start/
#### 创建数据库：create database SHARDING_TEST;
#### 运行：/resources/demo.sql

### yaml配置
#### application.yml 主配置文件，其中包含了dynamic 多数据源配置，spring.dynamic.primary 可指定主数据源，分库分表数据源默认为sharding
#### 配置文件中还提供两种分表算法：
#### sharding-tables.yaml 取模算法
#### sharding-tables-date.yaml 自定义日期算法+读写分离配置

###说明
#### 该项目只做了对达梦数据库简单测试，分表插入和简单查询，带函数的复杂查询未做测试，仅供学习参考
