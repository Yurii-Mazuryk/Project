<%--
  Created by IntelliJ IDEA.
  User: ipeople
  Date: 13.05.21
  Time: 07:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="resources/styles/test.css">
</head>
<body style="background: rgb(238,174,202);
background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(148,187,233,1) 100%);">
<div class="login-header">
    <p>Registration in conference project</p>
</div>

<div class="login-form">
    <form method="post" name="registration">
        <div class="registration-form">
            <input type="hidden" name="command" value="register">
            <div><input type="email" name="email" placeholder="Email" required class="form-input"></div>
            <div><input required type="text" name="name" placeholder="Name" minlength="4" maxlength="15" class="form-input"></div>
            <div><input type="tel" name="phone" placeholder="phone" pattern="+380-[0-9]{2}-[0-9]{3}" maxlength="13" minlength="10" class="form-input"></div>
            <div>
                <input required type="password" name="password" placeholder="password" class="form-input">
                <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
            </div>


            <div class="button"><button type="submit">Registration</button></div>
        </div>
    </form>
</div>

<div class="login-footer">
    <footer>
        <p>Created by Yurii Mazuryk © 2021</p>
    </footer>
</div>
</body>
</html>