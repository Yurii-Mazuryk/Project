<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">

<head>
    <title>Users</title>
</head>

<body>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Login</th>
            <th>Phone Number</th>
        </tr>

        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getName()}</td>
                <td>${user.getLogin()}</td>
                <td>${user.getPhoneNumber()}</td>
            </tr>
        </c:forEach>
    </table>

    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table>
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

</body>
</html>
