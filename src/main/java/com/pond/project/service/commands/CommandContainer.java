package com.pond.project.service.commands;


import com.pond.project.service.commands.redirect.LoginPageCommand;
import com.pond.project.service.commands.redirect.RegistrationPageCommand;
import com.pond.project.service.commands.redirect.StartPageRedirectCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private final Map<String, Command> commandMap;

    public CommandContainer() {
        commandMap = new HashMap<>();
        commandMap.put("login", new LoginCommand());
        commandMap.put("register", new RegisterCommand());
        commandMap.put("log_out", new LogOutCommand());
        commandMap.put("login_page", new LoginPageCommand());
        commandMap.put("registration_page", new RegistrationPageCommand());
        commandMap.put("display_user", new DisplayUserCommand());
        commandMap.put("display_event", new DisplayEventCommand());
        commandMap.put("account_page", new AccountPageCommand());
        commandMap.put("subscribe_event", new SubscribeEventCommand());
        commandMap.put("change_password", new ChangePasswordCommand());
        commandMap.put("change_name", new ChangeUserNameCommand());
        commandMap.put("add_event", new AddEventCommand());
        commandMap.put("start_page", new StartPageRedirectCommand());
        commandMap.put("change_role", new ChangeRoleCommand());
        commandMap.put("display_reports", new DisplayReportsCommand());
        commandMap.put("add_report", new AddReportCommand());
        commandMap.put("report_content", new DisplayReportsContentCommand());
        commandMap.put("update_report", new UpdateReportCommand());
        commandMap.put("approve_offer", new ApproveOfferCommand());
        commandMap.put("display_speaker", new DisplaySpeakerCommand());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}
