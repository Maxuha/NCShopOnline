<%--
  Created by IntelliJ IDEA.
  User: maksym
  Date: 15.07.2020
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Access is Denied!</h1>
    <a style="color: blue" href="${pageContext.request.contextPath}/">Home</a>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Sign Out" />
    </form>
</body>
</html>
