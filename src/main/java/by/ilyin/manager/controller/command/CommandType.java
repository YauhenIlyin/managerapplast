package by.ilyin.manager.controller.command;


import by.ilyin.manager.controller.command.project.*;
import by.ilyin.manager.controller.command.task.TaskCreateCommand;
import by.ilyin.manager.controller.command.task.TaskFindAllCommand;

public enum CommandType {

    DEFAULT(DefaultCommand.class),

    PROJECT_CREATE(ProjectCreateCommand.class),
    PROJECT_FIND_ALL(ProjectFindAllCommand.class),
    PROJECT_FIND_BY_ID(ProjectFindByIdCommand.class),
    PROJECT_UPDATE(ProjectUpdateCommand.class),
    PROJECT_DELETE(ProjectDeleteCommand.class),

    TASK_CREATE(TaskCreateCommand.class),
    TASK_FIND_ALL(TaskFindAllCommand.class);

    private Class commandBeanType;

    private CommandType(Class commandBeanType) {
        this.commandBeanType = commandBeanType;
    }

    public Class getCommandBeanType() {
        return this.commandBeanType;
    }

}
