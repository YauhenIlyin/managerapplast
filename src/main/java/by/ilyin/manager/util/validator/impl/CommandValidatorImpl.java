package by.ilyin.manager.util.validator.impl;

import by.ilyin.manager.controller.command.CommandType;
import by.ilyin.manager.util.validator.CommandValidator;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CommandValidatorImpl implements CommandValidator {

    private HashSet<String> commandNames;

    private CommandValidatorImpl() {
        CommandType[] commandTypeArr = CommandType.values();
        commandNames = new HashSet<>(commandTypeArr.length);
        for (CommandType container : commandTypeArr) {
            commandNames.add(container.name());
        }
    }

    public boolean validateCommandName(String commandName) {
        return commandName != null && commandNames.contains(commandName.toUpperCase());
    }

}