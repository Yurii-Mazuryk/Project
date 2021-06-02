package com.pond.project.service.commands;

import com.pond.project.service.dao.ReportDao;
import com.pond.project.service.models.Report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayReportsContentCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<Report> list = null;
        int noOfRecords = 0;

        if (request.getSession().getAttribute("role").equals(1)) {
            list = new ReportDao().getFreeReports((page - 1) * recordsPerPage,
                    recordsPerPage);
            noOfRecords = new ReportDao().getCountOfFreeReports();
        } else if (request.getSession().getAttribute("role").equals(0)) {
            list = new ReportDao().getReports((page - 1) * recordsPerPage,
                    recordsPerPage);
            noOfRecords = new ReportDao().getCountOfAllReports();
        }

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("reports", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        if ("POST".equalsIgnoreCase(request.getMethod()))
            return "main?command=report_content";
        return "/view/reports-content.jsp";
    }
}
