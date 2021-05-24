package com.pond.project.service.commands;

import com.pond.project.service.dao.ReportDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveOfferCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        String speakerLogin = request.getParameter("approvedSpeaker");
        if (reportId != 0 || speakerLogin != null)
            new ReportDao().setSpeaker(reportId, speakerLogin);
        else
            return "/error/error-page.jsp";

        return new DisplayReportsCommand().execute(request, response);
    }
}
