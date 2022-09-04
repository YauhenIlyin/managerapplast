package by.ilyin.manager.controller.command;

import by.ilyin.manager.evidence.CommandName;
import by.ilyin.manager.util.validator.CommandValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CommandFactory {

    private static final String REQUEST_PARAMETER_COMMAND_NAME = "command";
    private final CommandValidator commandValidator;
    private final ApplicationContext context;

    @Autowired
    private CommandFactory(CommandValidator commandValidator, ApplicationContext applicationContext) {
        this.commandValidator = commandValidator;
        this.context = applicationContext;
    }

    public Command getCurrentCommand(HttpServletRequest request) {
        String currentCommandName;
        Command currentCommand;
        currentCommandName = request.getParameter(REQUEST_PARAMETER_COMMAND_NAME);
        currentCommand = getCurrentCommand(currentCommandName);
        return currentCommand;
    }

    public Command getCurrentCommand(String currentCommandName) {
        Class commandBeanType;
        Command currentCommand;
        if (commandValidator.validateCommandName(currentCommandName)) {
            commandBeanType = CommandType.valueOf(currentCommandName.toUpperCase()).getCommandBeanType();
        } else {
            commandBeanType = CommandType.valueOf(CommandName.DEFAULT.toUpperCase()).getCommandBeanType();
        }
        currentCommand = (Command) context.getBean(commandBeanType);
        return currentCommand;
    }

}
