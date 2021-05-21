package com.pond.project.service.commands.redirect;

import com.pond.project.service.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/view/login.jsp";
    }
}
