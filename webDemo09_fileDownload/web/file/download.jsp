<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/14
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件上传</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/download" method="post">
    id：<input type="text" name="wid"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>