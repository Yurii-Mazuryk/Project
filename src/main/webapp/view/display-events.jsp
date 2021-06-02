<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
    <title>Users</title>
    <link rel="stylesheet" href="resources/styles/test.css">
</head>

<header>
    <div class="header-menu">
        <c:if test="${not empty user_name}">
            <i id="name">${user_name}</i>
        </c:if>
        <a href="http://localhost:8080/PetProject_war/"><i>Home</i></a>
        <a href="main?command=display_event&events_type=future"><i style="color: blue">Events</i></a>
        <c:if test="${not empty user_name}">
            <a href="main?command=account_page"><i>Account</i></a>
            <a href="main?command=log_out"><i>Log Out</i></a>
        </c:if>
        <c:if test="${empty user_name}">
            <a href="main?command=login_page"><i>Account</i></a>
            <a href="main?command=login_page"><i>Log in</i></a>
        </c:if>

    </div>
</header>

<body style="background: rgb(238,174,202);
background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);">


<div class="events-header">
    <p><%=request.getParameter("events_type")%> events</p>
</div>
<div class="table-wrapper">
    <table class="table">
        <tr>
            <th>Name</th>
            <th>Address</th>
            <th>Date</th>
            <th>Members</th>
            <th>Reports</th>
        </tr>

        <c:forEach var="event" items="${events}">
                <td><a href="main?command=subscribe_event&event_id=${event.getId()}">${event.getName()}</a></td>
                <td>${event.getAddress()}</td>
                <td>${event.getDate()}</td>
                <td>${event.getCountOfParticipant()}</td>
                <td>${event.getCountOfReports()}</td>
            </tr>
        </c:forEach>
    </table>
</div>
    <div class="pagination-table">
        <table class="table" style="width: fit-content;">
    <tr>
        <c:if test="${currentPage != 1}">
            <td><a href="main?command=display_event&events_type=<%=request.getParameter("events_type")%>&page=${currentPage - 1}">&laquo</a></td>
        </c:if>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="main?command=display_event&events_type=<%=request.getParameter("events_type")%>&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="main?command=display_event&events_type=<%=request.getParameter("events_type")%>&page=${currentPage + 1}">&raquo</a></td>
        </c:if>
        </tr>
        </table>
    </div>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>


