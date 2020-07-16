<%--
  Created by IntelliJ IDEA.
  User: olbe0615
  Date: 30.03.2020
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs" %>
<html>
<head>
    <title>Hello</title>
</head>
<h1>Hello, This is Home page!</h1>
<a style="color: blue" href="${pageContext.request.contextPath}/main">User Page</a>
<br />
<a style="color: blue" href="${pageContext.request.contextPath}/categories">Admin Page</a>
</html>