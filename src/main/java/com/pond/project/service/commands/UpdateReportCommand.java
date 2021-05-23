package com.pond.project.service.commands;

import com.pond.project.service.dao.ReportDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateReportCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String oldTitle = request.getParameter("reportTitle");
        String newTitle = request.getParameter("newReportTitle");
        new ReportDao().updateReportTitle(oldTitle, newTitle);
        return new DisplayReportsCommand().execute(request, response);
    }
}
