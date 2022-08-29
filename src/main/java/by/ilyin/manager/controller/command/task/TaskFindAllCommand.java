package by.ilyin.manager.controller.command.task;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Task;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.service.ProjectService;
import by.ilyin.manager.service.TaskService;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class TaskFindAllCommand implements Command {

    private ProjectService projectService;
    private TaskService taskService;

    public TaskFindAllCommand(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        String projectId = sessionRequestContent.getRequestParameters().get(KeyWordsApp.PROJECT_ID_FIELD_NAME);
        try {
            long projectIdValue = Long.parseLong(projectId);
            String currentRole = sessionRequestContent.getAuthDataManager().getCurrentUserRole();
            if (projectService.existsById(projectIdValue)) {
                List<Task> taskList;
                Pageable pageable = sessionRequestContent.getPageable();
                Page page;
                if (currentRole.equals(KeyWordsApp.ROLE_ADMIN_VALUE)) {
                    page = taskService.findAllByProjectId(projectIdValue, pageable);
                } else {
                    page = taskService.findAllByProjectIdAndIsDeletedEquals(projectIdValue, Boolean.FALSE, pageable);
                }
                sessionRequestContent.getRequestAttributes().put(KeyWordsRequest.TASKS, page.getContent());
                sessionRequestContent.setSuccessfulResult(Boolean.TRUE);
                sessionRequestContent.getRequestAttributes().put(KeyWordsRequest.PAGE_PAGE, page);
            }
        } catch (NumberFormatException e) {
            //todo log
        } catch (ManagerAppAuthException e) {
            //todo log
        }
    }
}
