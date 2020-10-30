<%--
  Created by IntelliJ IDEA.
  User: ilegra
  Date: 23/10/2020
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Showered pet</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
     <% if (request.getAttribute("showered").equals(true)) {%>
        <h1> The pet was successfully showered! </h1>
      <%}else {%>
            <h1> Sorry, but it was not possible to shower the Pet with the provided Id</h1>
        <%}%>

        <a href="javascript:history.back()">Go Back</a>
</body>
</html>
