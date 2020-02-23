<%--
  Created by IntelliJ IDEA.
  User: cxm
  Date: 2020/2/9
  Time: 19:13
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
<form action="${pageContext.request.contextPath}/upload" enctype="multipart/form-data" method="post">
     学号：<input type="text" name="sId"><br/>
     姓名：<input type="text" name="sname"><br/>
     test：<input type="text" name="checkCode"><br/>
    上传文件1：<input type="file" name="file1"><br/>
    上传文件2：<input type="file" name="file2"><br/>
    <input type="submit" value="提交">
</form>
</body>
</html>