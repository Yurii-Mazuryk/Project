package com.pond.project.service.commands;



import com.pond.project.service.dao.ReportDao;
import com.pond.project.service.models.Report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddReportCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Report report = new Report();
        report.setTitle(request.getParameter("reportTitle"));
        report.setEventName(request.getParameter("eventName"));
        if (report.getEventName() != null && report.getTitle() != null)
            new ReportDao().insertReport(report);
        return "/view/display-reports.jsp";
    }
}
