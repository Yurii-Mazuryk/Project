package com.pond.project.service.commands;

import com.pond.project.service.commands.Command;
import com.pond.project.service.dao.EventDao;
import com.pond.project.service.dao.UserDao;
import com.pond.project.service.models.Event;
import com.pond.project.service.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayEventCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<Event> list = null;
        int noOfRecords = 0;
        if (request.getParameter("events_type").equals("all")) {
            list = new EventDao().getEvents((page - 1) * recordsPerPage,
                    recordsPerPage);
            noOfRecords = new EventDao().getCountOfEvents();
        } else if (request.getParameter("events_type").equals("future")) {
            list = new EventDao().getFutureEvents((page - 1) * recordsPerPage,
                    recordsPerPage);
            noOfRecords = new EventDao().getCountOfFutureEvents();
        } else if (request.getParameter("events_type").equals("completed")) {
            int user_id = (int) request.getSession().getAttribute("user_id");
            list = new EventDao().getMemberCompletedEvents(user_id);
            noOfRecords = 10;
            recordsPerPage = 10;
//            noOfRecords = new EventDao().getCountOfMemberCompletedEvents(user_id);
        }

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("events", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        return "/view/display-events.jsp";
    }
}