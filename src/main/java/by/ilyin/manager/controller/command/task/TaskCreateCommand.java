package by.ilyin.manager.controller.command.task;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Task;
import by.ilyin.manager.entity.User;
import by.ilyin.manager.evidence.KeyWordsRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.service.CustomUserService;
import by.ilyin.manager.service.TaskService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("prototype")
public class TaskCreateCommand implements Command {

    private CustomUserService customUserService;
    private TaskService taskService;

    public TaskCreateCommand(CustomUserService customUserService, TaskService taskService) {
        this.customUserService = customUserService;
        this.taskService = taskService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        System.out.println("task create command");
        Task task = (Task) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.TASK);
        long creatorId = 0;
        try {
            creatorId = sessionRequestContent.getAuthDataManager().getCurrentUserId();
            Optional<User> optionalUser = customUserService.findById(creatorId);
            if (optionalUser.isPresent()) {
                task.setCreator(optionalUser.get());
                System.out.println("taaask" + task.getId());
                taskService.save(task);
                sessionRequestContent.setSuccessfulResult(true);
            }
        } catch (ManagerAppAuthException e) {
            //todo log
        }
    }
}
