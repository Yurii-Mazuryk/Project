package com.pond.project.service.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProposeReportCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        return new DisplaySpeakerCommand().execute(request, response);
    }
}
