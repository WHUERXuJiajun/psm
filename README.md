## psm — 大学生任务互助共享平台

后台环境测试：

1. 导入项目

   IDEA导入项目，导入pom.xml

2. 在本地建一个数据库psm，运行resources/sql/psm.sql

3. 修改resources/application.yaml中的用户名和密码（设置成你的）

4. 在user表中插入一条数据

5. 运行test/whu/web/psm/UserMapperTest.java中的selectExample()方法，如果运行成功，则项目构建无误



前端环境测试：

1. 运行PsmApplication.java
2. 在浏览器中输入localhost:8080/html/index/index.html，查看网页
