package by.ilyin.manager.controller.command.task;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.service.PreparatoryTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
public class TaskCreateCommand implements Command {

    private static final String SUCCESSFUL_VIEW = "redirect:/projects/";
    private static final String UNSUCCESSFUL_VIEW = "redirect:/projects";

    private PreparatoryTaskService preparatoryTaskService;

    @Autowired
    public TaskCreateCommand(PreparatoryTaskService preparatoryTaskService) {
        this.preparatoryTaskService = preparatoryTaskService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        preparatoryTaskService.createTask(sessionRequestContent);
        HashMap<String, String> params = sessionRequestContent.getRequestParameters();
        String resultView;
        ModelAndView model;
        if (sessionRequestContent.isSuccessfulResult()) {
            String currentProjectId = params.get(KeyWordsApp.PROJECT_ID_FIELD_NAME);
            resultView = SUCCESSFUL_VIEW + currentProjectId + "/tasks";
        } else {
            resultView = UNSUCCESSFUL_VIEW;
        }
        model = new ModelAndView(resultView);
        sessionRequestContent.setModelAndViewResult(model);
    }
}
