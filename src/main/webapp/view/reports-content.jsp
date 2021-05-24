<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: ipeople
  Date: 08.05.21
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="resources/styles/test.css">
</head>
<body style="background: rgb(238,174,202);
background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);">
<header>
    <div class="header-menu">
        <c:if test="${not empty user_name}">
            <i id="name">${user_name}</i>
        </c:if>
        <a href="http://localhost:8080/PetProject_war/"><i>Home</i></a>
        <a href="main?command=display_event&events_type=future"><i>Events</i></a>
        <a href="main?command=account_page" style="color: blue"><i>Account</i></a>
        <c:if test="${empty user_name}">
            <a href="main?command=login_page"><i>Log in</i></a>
        </c:if>
        <c:if test="${not empty user_name}">
            <a href="main?command=log_out"><i>Log Out</i></a>
        </c:if>
    </div>
</header>

<h1 class="header-account">Reports text</h1>


<div class="table-wrapper">
    <table class="table">
        <thead>
        <tr>
            <th>Report ID</th>
            <th>Report title</th>
            <td>Speaker Login</td>
            <th>Content</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="report" items="reports">
            <tr>
                <td>${report.getId()}</td>
                <td>${report.getTitle()}</td>
                <td>${report.getText()}</td>
                <td>${report.getSpeakerLogin()}</td>
            </tr>
        </tbody>
        </c:forEach>
    </table>
</div>

<div class="pagination-table">
    <table class="table" style="width: fit-content;">
        <tr>
            <td><a href="#">&laquo;</a></td>
            <td><a href="#">1</a></td>
            <td><a href="#">2</a></td>
            <td><a href="#">3</a></td>
            <td><a href="#">&raquo;</a></td>
        </tr>
    </table>
</div>



</body>
</html>