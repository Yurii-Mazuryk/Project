<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Account</title>
    <link rel="stylesheet" href="resources/styles/test.css">
    <script src="https://kit.fontawesome.com/70d19259f2.js" crossorigin="anonymous"></script>
    <script>
        function showReportOffers(reportId) {
            document.getElementById("report").value = reportId;
            document.getElementById("approve-offers").showModal();
        }
        function closeReportOffers() {
            document.getElementById("approve-offers").close();
        }

        function showAddReport() {
            document.getElementById("add-report").showModal();
        }
        function closeAddReport() {
            document.getElementById("add-report").close();
        }

        function showUpdateReport() {
            document.getElementById("change-report-title").showModal();
        }
        function closeUpdateReport() {
            document.getElementById("change-report-title").close();
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

<button onclick="showAddReport()" class="gradient-button">
    <span>Add report</span>
</button>

<button onclick="showUpdateReport()" class="gradient-button">
    <span>Update report</span>
</button>

<dialog id="add-report">
    <div class="registration_form">
        <div class="close-icon">
            <i onclick="closeAddReport()" class="far fa-times-circle"></i>
        </div>
        <form method="post" class="form" action="/PetProject_war/main">
            <input type="hidden" name="command" value="add_report">
            <div class="input-change-password">
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
                        name="reportTitle"
                        size="36"
                        placeholder="Title"
                        class="input"
                        required
                />
            </div>

            <button type="submit" onclick="closeAddReport()" class="gradient-button is-small">
                Add report
            </button>
        </form>
    </div>
</dialog>
<dialog id="change-report-title">
    <div class="registration_form">
        <div class="close-icon">
            <i onclick="closeUpdateReport()" class="far fa-times-circle"></i>
        </div>
        <form method="post" class="form" action="/PetProject_war/main">
            <input type="hidden" name="command" value="update_report">

            <div class="input-change-password">
                <input
                        type="text"
                        name="reportTitle"
                        size="36"
                        placeholder="Title"
                        class="input"
                        required
                />
            </div>
            <div class="input-change-password">
                <input
                        type="text"
                        name="newReportTitle"
                        size="36"
                        placeholder="New title"
                        class="input"
                        required
                />
            </div>
            <button type="submit" onclick="closeUpdateReport()" class="gradient-button is-small">
                Add report
            </button>
        </form>
    </div>
</dialog>
<dialog id="approve-offers">
    <div class="registration_form">
        <div class="close-icon">
            <i onclick="closeReportOffers()" class="far fa-times-circle"></i>
        </div>
        <form class="form" method="post" action="/PetProject_war/main">
            <input type="hidden" id="report" data-index="1" name="reportId" />
            <input type="hidden" name="command" value="approve_offer">
            <div class="table-wrapper">
                <table class="reports-table">
                    <thead>
                    <tr>
                        <th>Speaker login</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="speakers" items="${offers}">
                        <c:forEach var="speaker" items="${speakers}">
                    <tr>
                        <td>${speaker}</td>
                        <td><input type="radio" value="${speaker}" name="approvedSpeaker"></td>
                    </tr>
                        </c:forEach>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
            <button type="submit" onclick="closeReportOffers()" class="gradient-button is-small">
                Approve
            </button>
        </form>
    </div>
</dialog>


<h2 class="header-account">All reports</h2>
<div class="table-wrapper">
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Event Name</th>
            <th>Speaker login</th>
            <th>Confirmed</th>
            <th>Offers</th>
        </tr>

        <c:forEach var="report" items="${reports}">
            <tr>
                <td>${report.getId()}</td>
                <td>${report.getTitle()}</td>
                <td>${report.getEventName()}</td>
                <td>${report.getSpeakerLogin()}</td>
                <td>${report.isReportConfirmed()}</td>
                <c:if test="${report.isReportConfirmed() eq false}">
                <td><button class="show-offers-button" onclick="showReportOffers(${report.getId()})">
                    <span>${report.getOffersCount()}</span></button>
                </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="pagination-table">
    <table class="table" style="width: fit-content;">
        <tr>
            <c:if test="${currentPage != 1}">
                <td><a href="main?command=display_reports&page=${currentPage - 1}">&laquo</a></td>
            </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="main?command=display_reports&page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="main?command=display_reports&page=${currentPage + 1}">&raquo</a></td>
            </c:if>
        </tr>
    </table>
</div>