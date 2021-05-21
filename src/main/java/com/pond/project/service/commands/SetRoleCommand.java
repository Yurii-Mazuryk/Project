package com.pond.project.service.commands;

import com.pond.project.service.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetRoleCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        new UserDao().updateRole(request.getParameter("user_login"), Integer.parseInt(request.getParameter("role")));
        return "";
    }
}
