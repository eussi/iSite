# BlogOfWangxm
A personal blog project

### Usage:
1. 安装mysql数据库，运行doc/BlogOfWangxm.sql
2. resources/application-mysql.yml中配置正确数据库信息
3. jar包运行
    - 需要安装已安装maven
    - 进入pom.xml文件目录运行，mvn clean package -U -Dmaven.test.skip=true
    - java -jar BlogOfWangxm-*.jar
    - 访问地址:http:xxx.xxx.xxx.xxx:xx

##### 项目部署地址：

 - http://www.eussi.top/
 
##### 项目构建:Maven

##### jdk:1.8.0_91

#### 项目主要技术选型：
 - springboot 
 - springmvc
 - freemarker
 - shiro
 - ehcache
 - mybaits
 - mybaits-generator
 - mysql
 - jquery
 - bootstrap
 - sea.js
 - font-awesome
 - ......
 
#### 数据库物理模型：
![image](https://github.com/eussi/BlogOfWangxm/blob/master/doc/db_pdm.png)

#### 首页如图：
![image](https://github.com/eussi/BlogOfWangxm/blob/master/doc/preview/index.png)
#### 用户首页如图：
![image](https://github.com/eussi/BlogOfWangxm/blob/master/doc/preview/user_index.png)
#### 后台首页如图：
![image](https://github.com/eussi/BlogOfWangxm/blob/master/doc/preview/back_index.png)
