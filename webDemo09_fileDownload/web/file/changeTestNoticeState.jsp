<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/13
  Time: 15:57
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
<form action="${pageContext.request.contextPath}/manager" method="post">
    <input type="hidden" name="oper" value="changeTestNoticeState" />
    考核码 ： <input type="text" name="test"><br/>
    考核状态：<input type="text" name="state"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>