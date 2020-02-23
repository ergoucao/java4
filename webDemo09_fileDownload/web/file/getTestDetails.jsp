<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/12
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件上传</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/manager" method="post">
    <input type="hidden" name="oper" value="getTestDetails" />
    页数：<input type="text" name="page"><br/>
    CAPages：<input type="text" name="CAPages"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
