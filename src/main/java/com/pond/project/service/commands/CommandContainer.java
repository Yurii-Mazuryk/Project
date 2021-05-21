package com.pond.project.service.commands;


import com.pond.project.service.commands.redirect.AccountPageCommand;
import com.pond.project.service.commands.redirect.LoginPageCommand;
import com.pond.project.service.commands.redirect.RegistrationPageCommand;

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
        commandMap.put("free_reports", new DisplayFreeReportCommand());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }
}