package by.ilyin.manager.controller.command.task;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.entity.Task;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.service.PreparatoryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Component
public class TaskFindAllCommand implements Command {

    private static final String SUCCESSFUL_VIEW = "/tasks/tasks";
    private static final String UNSUCCESSFUL_VIEW = "redirect:/projects/{projectId}";

    private PreparatoryTaskService preparatoryTaskService;

    @Autowired
    public TaskFindAllCommand(PreparatoryTaskService preparatoryTaskService) {
        this.preparatoryTaskService = preparatoryTaskService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        HashMap<String, String> params = sessionRequestContent.getRequestParameters();
        HashMap<String, Object> attributes = sessionRequestContent.getRequestAttributes();
        preparatoryTaskService.findAllTasks(sessionRequestContent);
        String resultView;
        ModelAndView model;
        if (sessionRequestContent.isSuccessfulResult()) {
            List tasks = (List<Task>) attributes.get(KeyWordsSessionRequest.TASKS);
            Page page = (Page) attributes.get(KeyWordsSessionRequest.PAGE_PAGE);
            String projectId = params.get(KeyWordsSessionRequest.PROJECT_ID);
            Project project = (Project) attributes.get(KeyWordsSessionRequest.PROJECT);
            resultView = SUCCESSFUL_VIEW;
            model = new ModelAndView(resultView);
            model.addObject(KeyWordsSessionRequest.TASKS, tasks);
            model.addObject(KeyWordsSessionRequest.PAGE_PAGE, page);
            model.addObject(KeyWordsSessionRequest.PROJECT_ID, projectId);
            model.addObject(KeyWordsSessionRequest.PROJECT, project);
        } else {
            resultView = UNSUCCESSFUL_VIEW;
            model = new ModelAndView(resultView);
        }
        sessionRequestContent.setModelAndViewResult(model);
    }
}
