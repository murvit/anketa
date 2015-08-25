<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.08.2015
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <link rel="shortcut icon" href="/static/favicon.ico">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">

</head>
<body>


<div class="panel panel-default">
    <div class="panel-heading">Thank you, <%=request.getParameter("firstname")%>
    </div>
    <div class="panel-body">

        <table class="table">
            <tbody>
            <tr>
                <td><b>Your results</b></td>
                <td></td>
            </tr>
            <tr>
                <td>Like to learn java</td>
                <td><%=request.getParameter("islike")%>
                </td>

            </tr>
            <tr>
                <td>Favorite OS</td>
                <td><%=request.getParameter("os")%>
                </td>
            </tr>
            <tr>
                <td><b>Statistics</b></td>
                <td></td>
            </tr>
            <tr>
                <td>Total votes</td>
                <td><%=request.getAttribute("counter")%>
                </td>
            </tr>
            <tr>
                <td>People like to learn java</td>
                <td><%=request.getAttribute("yesanswers")%>
                </td>
            </tr>
            <tr>
                <td>Average age</td>
                <td><%=request.getAttribute("averageage")%>
                </td>
            </tr>
            <tr>
                <td>Choose Windows</td>
                <td><%=request.getAttribute("windows")%>
                </td>
            </tr>
            <tr>
                <td>Choose Linux</td>
                <td><%=request.getAttribute("linux")%>
                </td>
            </tr>
            <tr>
                <td>Choose other OS</td>
                <td><%= request.getAttribute("other") %>
        </table>
    </div>
</div>
<p class="navbar-text"><a href="anketa.html" class="navbar-link">Answer questions again</a></p>

<p class="navbar-text"><a href="index.html" class="navbar-link">Homepage</a></p>

</body>
</html>
