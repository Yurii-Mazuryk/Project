package com.pond.project.service.commands;

import com.pond.project.service.dao.EventDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeEventCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int user_id = (int) request.getSession().getAttribute("user_id");
        int event_id = Integer.parseInt(request.getParameter("event_id"));
        new EventDao().subscribeEvent(user_id, event_id);
        return "/view/account.jsp";
    }
}
