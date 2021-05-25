<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
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

<h1 class="header-account">All users</h1>

<div class="table-wrapper">
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Phone Number</th>
            <th>Role</th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getName()}</td>
                <td>${user.getLogin()}</td>
                <td>${user.getPhoneNumber()}</td>
                <td><c:if test="${user.getRole() == 2}">Member</c:if>
                    <c:if test="${user.getRole() == 1}">Speaker</c:if>
                    <c:if test="${user.getRole() == 0}">Moderator</c:if>
                </td>
                <td>
                    <form method="post" action="/PetProject_war/main">
                        <input type="hidden" name="command" value="change_role">
                        <input type="hidden" name="roleId" value="${user.getRole()}">
                        <input type="hidden" name="userLogin" value="${user.getLogin()}">
                        <button class="change-role-button" type="submit">
                            <span>Change role</span>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
<div class="pagination-table">
    <table class="table" style="width: fit-content;">
        <tr>
            <c:if test="${currentPage != 1}">
                <td><a href="main?command=display_user&page=${currentPage - 1}">&laquo</a></td>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="main?command=display_user&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="main?command=display_user&page=${currentPage + 1}">&raquo</a></td>
            </c:if>
        </tr>
    </table>
</div>
</body>
</html>
