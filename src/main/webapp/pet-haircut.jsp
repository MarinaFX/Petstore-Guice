<%--
  Created by IntelliJ IDEA.
  User: ilegra
  Date: 23/10/2020
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pet cut</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
    <% if (request.getAttribute("haircut").equals(true)){%>
        <h1> Yay, your pet looks bright and shinny with his new ${cutStyle} cut! </h1>
    <%} else {%>
        <h1> Sorry, but it was not possible to cut the hair of the Pet with the provided Id</h1>
    <%}%>

    <a href="javascript:history.back()">Go Back</a>
</body>
</html>
