## psm — 大学生任务互助共享平台

### 文件夹说明
```
src/main/java/whu/web/psm/controller：控制层
src/main/java/whu/web/psm/service：服务层
src/main/java/whu/web/psm/dao：数据库交互层
src/main/java/whu/web/psm/ecxeption：异常处理模块
src/main/java/whu/web/psm/config：配置模块
src/main/java/whu/web/psm/util：常用工具模块
src/main/resources/static：前端模块
```



### 后台环境测试：

1. 导入项目

   IDEA导入项目，导入pom.xml

2. 在本地建一个数据库psm，运行resources/sql/psm.sql

3. 修改resources/application.yaml中的用户名和密码（设置成你的）

4. 在user表中插入一条数据

5. 运行test/whu/web/psm/UserMapperTest.java中的selectExample()方法，如果运行成功，则项目构建无误



### 前端环境测试：

1. 运行PsmApplication.java
2. 在浏览器中输入http://localhost:8080/html/index/index.html，查看网页
3. 在浏览器中输入http://localhost:8080/swagger-ui.html#/，如果能显示swagger API界面，则API文档无误



### github提交规范

```
feat：新功能（feature）
fix：修补bug
docs：文档（documentation）
style： 格式（不影响代码运行的变动）
refactor：重构（即不是新增功能，也不是修改bug的代码变动）
test：增加测试
chore：构建过程或辅助工具的变动
```

比如：添加了查询全部任务的功能

feat：添加了查询全部任务api
