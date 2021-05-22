package com.pond.project.service.commands;

import com.pond.project.service.models.User;
import com.pond.project.service.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements  Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String path;

        String login = request.getParameter("email");
        String password = request.getParameter("password");

        if (login == null || password == null) {
            path = "/error/user-not-valid.jsp";
            request.setAttribute("error-message", "Fields cannot be empty!");
            return path;
        }


        User user = new UserDao().getUserByLogin(login);
        if (!password.equals(user.getPassword())) {
            path = "/error/user-not-valid.jsp";
            request.setAttribute("error-message", "Incorrect password.");
            return path;
        }
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(-1);
        session.setAttribute("user_id", user.getId());
        session.setAttribute("user_name", user.getName());
        session.setAttribute("user_login", user.getLogin());
        session.setAttribute("role", user.getRole());
        return "/view/start-page.jsp";
    }
}
