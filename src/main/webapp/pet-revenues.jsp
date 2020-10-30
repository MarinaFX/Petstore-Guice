<%@ page import="java.util.Map" %>
<%@ page import="com.marinafx.cloudnative.tema6.pet.Pet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ilegra
  Date: 23/10/2020
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
<% List<Map.Entry<Pet, Double>> entries = (List<Map.Entry<Pet, Double>>) request.getAttribute("entries");%>
<table>
    <%
        for (Map.Entry<Pet, Double> entry : entries) {%>
    <tr>
        <td><%= "Pet: " + entry.getKey().getName() %> <br> <%= "Revenue: " + entry.getValue() %>
        </td>
    </tr>
    <%}%>
</table> <br>
<a href="javascript:history.back()">Go Back</a>
</body>
</html>
