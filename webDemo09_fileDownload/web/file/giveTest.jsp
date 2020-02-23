<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/11
  Time: 16:56
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
<form action="manager" method="post">
    <input type="hidden" name="oper" value="giveTestNotice" />
    考核题目：<input type="text" name="tName"><br/>
    考核内容：<input type="text" name="tContent"><br/>
      考核码：<input type="text" name="test"><br/>
    考核时限：<input type="text" name="time"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>
