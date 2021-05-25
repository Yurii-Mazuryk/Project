<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Speakers</title>
    <link rel="stylesheet" href="resources/styles/test.css">
    <script src="https://kit.fontawesome.com/70d19259f2.js" crossorigin="anonymous"></script>
    <script>
        function showProposeReports() {
            document.getElementById("propose-reports").showModal();
        }
        function closeProposeReports() {
            document.getElementById("propose-reports").close();
        }
    </script>
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

<dialog id="propose-reports">
    <div class="registration_form">
        <div class="close-icon">
            <i onclick="closeProposeReports()" class="far fa-times-circle"></i>
        </div>
        <form method="post" class="form">

            <div class="input-change-password">
                <input
                        min="0"
                        type="number"
                        name="speakerId"
                        placeholder="Speaker ID"
                        class="input"
                        required
                />
            </div>
            <div class="input-change-password">
                <input
                        min="0"
                        type="number"
                        name="reportId"
                        placeholder="Report ID"
                        class="input"
                        required
                />
            </div>

            <button type="submit" onclick="closeProposeReports()" class="gradient-button is-small">
                Propose report
            </button>
        </form>
    </div>
</dialog>

<h1 class="header-account">All speakers</h1>

<div class="table-wrapper">
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Phone Number</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="speaker" items="${speakers}">
            <tr>
                <td>${speaker.getId()}</td>
                <td>${speaker.getName()}</td>
                <td>${speaker.getLogin()}</td>
                <td>${speaker.getPhoneNumber()}</td>
                <td><button onclick="showProposeReports()" class="change-role-button">
                    <span>Propose report</span>
                </button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<div class="pagination-table">
    <table class="table" style="width: fit-content;">
        <tr>
            <c:if test="${currentPage != 1}">
                <td><a href="main?command=display_speaker&page=${currentPage - 1}">&laquo</a></td>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="main?command=display_speaker&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="main?command=display_speaker&page=${currentPage + 1}">&raquo</a></td>
            </c:if>
        </tr>
    </table>
</div>
</body>
</html>
