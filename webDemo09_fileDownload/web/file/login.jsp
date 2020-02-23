<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/13
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件上传</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/method" method="post">
    <input type="hidden" name="oper" value="getWorkDetails" />
    账户：<input type="text" name="adname"><br/>
    密码：<input type="text" name="adid"><br/>
    cookie：<input type="text" name="ck"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>