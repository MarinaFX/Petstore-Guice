<%@ page import="com.marinafx.cloudnative.tema6.pet.Pet" %>
<%@ page import="com.google.common.collect.LinkedHashMultimap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ilegra
  Date: 23/10/2020
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
<% LinkedHashMultimap<Pet, String> history = (LinkedHashMultimap<Pet, String>) request.getAttribute("history");%>
<table>
    <%
        for (Map.Entry<Pet, String> entry : history.entries()) {%>
    <tr>
        <td><%= "Pet: " + entry.getKey().getName() %> <br> <%="\nService: " + entry.getValue() %>
        </td>
    </tr>
    <%}%>
</table> <br>
<a href="javascript:history.back()">Go Back</a>
</body>
</html>
