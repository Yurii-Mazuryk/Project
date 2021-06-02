package com.pond.project.service.commands;

import com.pond.project.service.dao.UserDao;
import com.pond.project.service.models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplaySpeakerCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<User> list = new UserDao().getSpeakers((page-1)*recordsPerPage, recordsPerPage);
        int noOfRecords = new UserDao().getSpeakersCount();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("speakers", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        if ("post".equalsIgnoreCase(request.getMethod()))
            return "main?command=display_speaker";
        return "/view/display-speakers.jsp";
    }
}
