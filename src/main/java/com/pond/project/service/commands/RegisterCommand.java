package com.pond.project.service.commands;

import com.pond.project.service.dao.UserDao;
import com.pond.project.service.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();
        String path = "/view/login.jsp";

        user.setLogin(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNum"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        if (user.getPassword().length() < 6) {
            path = "/error/error-page.jsp";
            request.setAttribute("error-message", "Password length must be more then 5 symbols.");
            return path;
        } else if (user.getPhoneNumber().length() != 10) {
            path = "/error/error-page.jsp";
            request.setAttribute("error-message", "Incorrect phone number.");
            return path;
        } else if (new UserDao().isValidLogin(user.getLogin())) {
            path = "/error/error-page.jsp";
            request.setAttribute("error-message", "This email is already used.");
            return path;
        }
        new UserDao().insertUser(user);
        return path;
    }
}
