package com.pond.project.service.commands;

import com.pond.project.service.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeRoleCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("roleId").equals("1"))
            new UserDao().updateRole(request.getParameter("userLogin"), 2);
        else if (request.getParameter("roleId").equals("2"))
            new UserDao().updateRole(request.getParameter("userLogin"), 1);
        return new DisplayUserCommand().execute(request, response);
    }
}
