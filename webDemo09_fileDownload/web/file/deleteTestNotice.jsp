<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/13
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>deleteTestNotice</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/manager" method="post">
    <input type="hidden" name="oper" value="deleteTestNotice" />
    考核码 ： <input type="text" name="test"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>