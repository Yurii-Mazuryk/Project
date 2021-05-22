package com.pond.project.service.commands;

import com.pond.project.service.dao.EventDao;
import com.pond.project.service.models.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddEventCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Event event = new Event();

        event.setName(request.getParameter("eventName"));
        event.setAddress(request.getParameter("address"));
        event.setDate("date");
        if (event.getName() != null || event.getAddress() != null || event.getDate() == null)
            new EventDao().insertEvent(event);
        else {
            request.setAttribute("error-message", "Incorrect input.");
            return "/error/error-page.jsp";
        }

        return new AccountPageCommand().execute(request, response);
    }
}
