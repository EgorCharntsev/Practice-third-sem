<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 26.09.2023
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>City info</title>
</head>
<body>

<%
    String temp = null;
    String hum = null;
    String desc = null;
    String name = null;
    String err = null;
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
        for (Cookie cookie :cookies) {
            if ("temp".equalsIgnoreCase(cookie.getName())) {
                temp = cookie.getValue();
            } else if ("humidity".equalsIgnoreCase(cookie.getName())) {
                hum = cookie.getValue();
            } else if ("description".equalsIgnoreCase(cookie.getName())) {
               desc = cookie.getValue();
            } else if ("name".equalsIgnoreCase(cookie.getName())) {
                name = cookie.getValue();
            }
        }
    } else {
        err = "City not found";
    }
%>

<%
    if (err == null) {
%>
    <h3>
        Welcome to the <%=name%>!
        <br>
        <br>

        Today in our city:
        <br>
        <br>
        Temperature is <%=temp%> °C
        <br>
        <br>
        Humidity is <%=hum%> %
        <br>
        <br>
        <%=desc%>

    </h3>
<%
    } else {
%>
<h3>
    <%=err%>
</h3>
<% } %>

</body>
</html>
