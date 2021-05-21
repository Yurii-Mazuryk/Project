package com.pond.project.service.commands.redirect;

import com.pond.project.service.commands.Command;
import com.pond.project.service.commands.DisplayFreeReportCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("role").equals("1"))
            return new DisplayFreeReportCommand().execute(request, response);
        return "/view/account.jsp";
    }
}
