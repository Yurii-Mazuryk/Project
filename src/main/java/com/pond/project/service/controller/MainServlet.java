package com.pond.project.service.controller;

import com.pond.project.service.commands.Command;
import com.pond.project.service.commands.CommandContainer;
import com.pond.project.service.commands.LoginCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        Command executeCommand = new CommandContainer().getCommand(command);
        String path = executeCommand.execute(request, response);
        response.sendRedirect(path);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        Command executeCommand = new CommandContainer().getCommand(command);
        String path = executeCommand.execute(request, response);
        request.getRequestDispatcher(path).forward(request, response);
    }
}
