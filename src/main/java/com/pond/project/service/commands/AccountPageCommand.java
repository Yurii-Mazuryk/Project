package com.pond.project.service.commands;

import com.pond.project.service.dao.EventDao;
import com.pond.project.service.models.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AccountPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int userId = (int) request.getSession().getAttribute("user_id");
        List<Event> list = new EventDao().getMemberActiveEvents(userId);
        request.setAttribute("actives", list);
        if(request.getSession().getAttribute("role").equals("1"))
            return new DisplayFreeReportCommand().execute(request, response);
        if ("POST".equalsIgnoreCase(request.getMethod()))
            return "main?command=account_page";
        return "/view/account.jsp";
    }
}
