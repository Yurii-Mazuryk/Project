<%@ page import="com.pond.project.service.dao.EventDao" %>
<%@ page import="com.pond.project.service.models.Event" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% EventDao dao = new EventDao();
    Event event = dao.closest();
    request.setAttribute("event", event);
%>
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
        <a href="http://localhost:8080/PetProject_war/"><i style="color: blue">Home</i></a>
        <a href="main?command=display_event&events_type=future"><i>Events</i></a>
        <a href="main?command=account_page"><i>Account</i></a>
        <c:if test="${empty user_name}">
            <a href="main?command=login_page"><i>Log in</i></a>
        </c:if>
        <c:if test="${not empty user_name}">
            <a href="main?command=log_out"><i>Log Out</i></a>
        </c:if>
    </div>
</header>




<div class="main-conference">
    <p>Conference:</p>
    <p>${event.name}</p>
</div>

<div class="conference-date">
    <p>
    <div class="date"><span class="text">${event.date}</span></div>
    <div class="date" id="city"><span class="text">${event.address}</span></div>
    <div class="date" id="reg"><a href="main?command=subscribe_event&event_id=<%=event.getId()%>"><span class="text">Register</span></a></div>
    </p>
</div>


</body>
</html>