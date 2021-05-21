package com.pond.project.service.commands;

import com.pond.project.service.commands.Command;
import com.pond.project.service.dao.UserDao;
import com.pond.project.service.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayUserCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<User> list = new UserDao().getUsers((page-1)*recordsPerPage, recordsPerPage);
        int noOfRecords = new UserDao().getCountOfUsers();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("users", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return "/view/display-users.jsp";
    }
}
