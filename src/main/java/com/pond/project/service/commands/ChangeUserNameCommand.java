package com.pond.project.service.commands;

import com.pond.project.service.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeUserNameCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("newName");
        Pattern p = Pattern.compile("[A-Za-z]");
        Matcher m = p.matcher(name);
        if (m.matches()) {
            new UserDao().changeUserName((int) request.getSession().getAttribute("user_id"), name);
        } else {
            request.setAttribute("error-message", "Incorrect name.");
            return "/error/error-page.jsp";
        }
        return new AccountPageCommand().execute(request, response);
    }
}
