<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/13
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>changeWorkPass</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/manager" method="post">
    <input type="hidden" name="oper" value="changeWorkPass" />
    考核通过 ： <input type="text" name="pass"><br/>
    考核作业id：<input type="text" name="wid"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
