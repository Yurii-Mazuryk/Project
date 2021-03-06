<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Account</title>
    <link rel="stylesheet" href="resources/styles/test.css">
    <script src="https://kit.fontawesome.com/70d19259f2.js" crossorigin="anonymous">
    </script>
    <script>
    function showChangePassword() {
        document.getElementById("change-password").showModal();
    }
    function closeChangePassword() {
        document.getElementById("change-password").close();
    }

    function showChangeName() {
        document.getElementById("change-name").showModal();
    }
    function closeChangeName() {
        document.getElementById("change-name").close();
    }

    function showAddEvents() {
        document.getElementById("add-event").showModal();
    }
    function closeAddEvents() {
        document.getElementById("add-event").close();
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

<h1 class="header-account">
    <c:if test="${role == 0}"> Admin's account</c:if>
    <c:if test="${role == 1}"> Speaker's account</c:if>
    <c:if test="${role == 3}"> Member's account</c:if>
</h1>

<button onclick="showChangePassword()" class="gradient-button">
    <span>Change password</span>
</button>

<button onclick="showChangeName()" class="gradient-button">
    <span>Change name</span>
</button>

<c:if test="${role == 0}">
    <button onclick="showAddEvents()" class="gradient-button">
        <span>Attach event</span>
    </button>
    <a href="main?command=report_content">
        <button class="gradient-button">
            <span>Reports content</span>
        </button>
    </a>
    <a href="main?command=display_reports">
        <button class="gradient-button">
            <span>Reports offers</span>
        </button>
    </a>
    <a href="main?command=display_user">
        <button class="gradient-button">
            <span>Users</span>
        </button>
    </a>
</c:if>
<!---->

<c:if test="${role == 1}">
    <a href="main?command=report_content">
        <button class="gradient-button">
            <span>Reports</span>
        </button>
    </a>
</c:if>

<a href="main?command=display_event&events_type=all">
    <button class="gradient-button">
        <span>All events</span>
    </button>
</a>

<a href="main?command=display_event&events_type=completed">
    <button class="gradient-button">
        <span>Completed events</span>
    </button>
</a>

<!---->

<dialog id="change-password">
    <div class="registration_form">
        <div class="close-icon">
            <i onclick="closeChangePassword()" class="far fa-times-circle"></i>
        </div>
        <form method="post" class="form" action="/PetProject_war/main">
            <input type="hidden" name="command" value="change_password">
            <div class="input-change-password">
                <input
                        type="text"
                        name="oldPassword"
                        size="36"
                        placeholder="Old Password"
                        class="input"
                        required
                />
            </div>
            <div class="input-change-password">
                <input
                        type="password"
                        name="newPassword"
                        size="36"
                        placeholder="New password"
                        class="input"
                        required
                />
            </div>
            <div class="input-change-password">
                <input
                        type="password"
                        name="repeatPassword"
                        size="36"
                        placeholder="Repeat password"
                        class="input"
                        required
                />
            </div>

            <button type="submit" onclick="closeChangePassword()" class="gradient-button is-small">
                Change password
            </button>
        </form>
    </div>
</dialog>

<dialog id="change-name">
    <div class="registration_form">
        <div class="close-icon">
            <i onclick="closeChangeName()" class="far fa-times-circle"></i>
        </div>
        <form method="post" class="form" action="/PetProject_war/main">
            <input type="hidden" name="command" value="change_name">
            <div class="input-change-password">
                <input
                        type="text"
                        name="newName"
                        size="36"
                        placeholder="New name"
                        class="input"
                        required
                >
            </div>

            <button type="submit" onclick="closeChangeName()" class="gradient-button is-small">
                Change name
            </button>
        </form>
    </div>
</dialog>

<dialog id="add-event">
    <div class="registration_form">
        <div class="close-icon">
            <i onclick="closeAddEvents()" class="far fa-times-circle"></i>
        </div>
        <form method="post" class="form" action="/PetProject_war/main">
            <div class="input-change-password">
                <input type="hidden" name="command" value="add_event"/>
                <input
                        type="text"
                        name="eventName"
                        size="36"
                        placeholder="Event name"
                        class="input"
                        required
                />
            </div>
            <div class="input-change-password">
                <input
                        type="text"
                        name="address"
                        size="36"
                        placeholder="Address"
                        class="input"
                        required
                />
            </div>
            <div class="input-change-password">
                <input
                        style="width: 94%"
                        type="datetime-local"
                        name="date"
                        class="input"
                        required
                />
            </div>

            <button type="submit" onclick="closeAddEvents()" class="gradient-button is-small">
                Add event
            </button>
        </form>
    </div>
</dialog>

<h1 class="header-account">Active events</h1>
    <div class="table-wrapper">
        <table class="table">
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>Date</th>
                <th>Members</th>
                <th>Reports</th>
            </tr>

            <c:forEach var="active" items="${actives}">
                <tr>
                    <td>${active.getName()}</td>
                    <td>${active.getAddress()}</td>
                    <td>${active.getDate()}</td>
                    <td>${active.getCountOfParticipant()}</td>
                    <td>${active.getCountOfReports()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>



</body>
</html>