<%--
  Created by IntelliJ IDEA.
  User: ipeople
  Date: 02.05.21
  Time: 08:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="resources/styles/test.css">
</head>
<body style="background: rgb(238,174,202);
background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);">
<div class="login-header">
    <p>Welcome to  Conference Project</p>
</div>

<div class="login-form">
    <div class="inputs">
        <form method="post" name="login" action="/PetProject_war/main">
            <input type="hidden" name="command" value="login">
            <div><input required type="email" name="email" placeholder="Email" class="form-input"></div>
            <div><input required type="password" name="password" placeholder="password" class="form-input"></div>
            <div class="button">
                <button type="submit">Log In</button>
                <a href="main?command=registration_page">
                    <button type="button">Sign In</button>
                </a>
            </div>
        </form>
    </div>
</div>

<div class="login-footer">
    <footer>
        <p>Created by Yurii Mazyruk © 2021</p>
    </footer>
</div>
</body>
</html>