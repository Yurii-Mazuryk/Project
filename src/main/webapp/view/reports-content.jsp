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
    <meta charset="UTF-8">
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
    <c:if test="${not empty user_name}">
        <a href="main?command=account_page"><i style="color: blue">Account</i></a>
        <a href="main?command=log_out"><i>Log Out</i></a>
    </c:if>
    <c:if test="${empty user_name}">
        <a href="main?command=login_page"><i>Account</i></a>
        <a href="main?command=login_page"><i>Log in</i></a>
    </c:if>
    </div>
</header>

<c:if test="${role == 0}">

<h1 class="header-account">Reports</h1>
<div class="table-wrapper">
    <table class="table">
        <thead>
        <tr>
            <th>Report ID</th>
            <th>Report title</th>
            <th>Content</th>
            <td>Speaker Login</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.getId()}</td>
                <td>${report.getTitle()}</td>
                <td>${report.getText()}</td>
                <td>${report.getSpeakerLogin()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="pagination-table">
    <table class="table" style="width: fit-content;">
        <tr>
            <c:if test="${currentPage != 1}">
                <td><a href="main?command=report_content&page=${currentPage - 1}">&laquo</a></td>
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="main?command=report_content&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="main?command=report_content&page=${currentPage + 1}">&raquo</a></td>
            </c:if>
        </tr>
    </table>
</div>
</c:if>

<c:if test="${role == 1}">
    <h2 class="header-account">Free reports</h2>
    <div class="table-wrapper">
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Event Name</th>
                <th>Content</th>
            </tr>

            <c:forEach var="report" items="${reports}">
                <tr>
                    <td>${report.getId()}</td>
                    <td>${report.getTitle()}</td>
                    <td>${report.getEventName()}</td>
                    <td><a href="#">${report.getText()}</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="pagination-table">
        <table class="table" style="width: fit-content;">
            <tr>
                <c:if test="${currentPage != 1}">
                    <td><a href="main?command=report_content&page=${currentPage - 1}">&laquo</a></td>
                </c:if>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="main?command=report_content&page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage lt noOfPages}">
                    <td><a href="main?command=report_content&page=${currentPage + 1}">&raquo</a></td>
                </c:if>
            </tr>
        </table>
    </div>
</c:if>
</body>
</html>