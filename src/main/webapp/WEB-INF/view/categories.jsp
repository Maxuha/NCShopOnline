<%--
  Created by IntelliJ IDEA.
  User: maksym
  Date: 08.06.2020
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello, the page is for Admin!</h1>
    <a style="color: blue" href="${pageContext.request.contextPath}/">Home</a>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Sign Out" />
    </form>
</body>
</html>
