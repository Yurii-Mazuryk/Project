package com.pond.project.service.commands;

import com.pond.project.service.dao.ReportDao;
import com.pond.project.service.models.Report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayReportsCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<Report> list = new ReportDao().getReports((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = new ReportDao().getCountOfAllReports();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        List<List<String>> lists = new ReportDao().getOffers((page - 1) * recordsPerPage,
                recordsPerPage);
        request.setAttribute("offers", lists);
        request.setAttribute("reports", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        if ("POST".equalsIgnoreCase(request.getMethod()))
            return "main?command=display_reports";
        return "/view/display-reports.jsp";
    }
}
