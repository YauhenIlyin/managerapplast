package by.ilyin.manager.util.validator;

import by.ilyin.manager.controller.command.CommandType;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class CommandValidator {

    private HashSet<String> commandNames;

    private CommandValidator() {
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