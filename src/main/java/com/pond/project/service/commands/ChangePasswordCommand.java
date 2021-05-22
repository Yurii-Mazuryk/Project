package com.pond.project.service.commands;

import com.pond.project.service.dao.UserDao;
import com.pond.project.service.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = (int) request.getSession().getAttribute("user_id");
        User user = new UserDao().getUserById(userId);
        String password = request.getParameter("newPassword");
        String oldPass = request.getParameter("oldPassword");
        if (user.getPassword().equals(oldPass))
        {
            if (password.equals(request.getParameter("repeatPassword"))) {
                if (password.length() > 5)
                    new UserDao().changePassword(userId, request.getParameter("newPassword"));
                else {
                    request.setAttribute("error-message", "Password length must be more then 5 symbols.");
                    return "/error/error-page.jsp";
                }
            }else {
                    request.setAttribute("error-message", "The passwords do not and then retype the password.");
                    return "/error/error-page.jsp";
            }
        } else {
            request.setAttribute("error-message", "Incorrect password.");
            return "/error/error-page.jsp";
        }
        return new AccountPageCommand().execute(request, response);
    }
}
