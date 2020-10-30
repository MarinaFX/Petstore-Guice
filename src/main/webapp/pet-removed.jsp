<%--
  Created by IntelliJ IDEA.
  User: ilegra
  Date: 22/10/2020
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pet Removed</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
    <% if (request.getAttribute("petRemoved").equals(true)){%>
        <h1> The pet was successfully removed! </h1>
        <%} else {%>
            <h1> Sorry, but it was not possible to remove the Pet with the provided Id</h1>
        <%}%>

    <a href="javascript:history.back()">Go Back</a>
</body>
</html>
