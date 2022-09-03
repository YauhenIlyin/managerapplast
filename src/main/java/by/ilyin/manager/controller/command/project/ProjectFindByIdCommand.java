package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
public class ProjectFindByIdCommand implements Command {

    private static final String SUCCESSFUL_PATH = "projects/";
    private static final String SUCCESSFUL_VIEW = "project_by_id";
    private static final String UNSUCCESSFUL_PATH = "redirect:/projects";
    private static final String UNSUCCESSFUL_VIEW = null;

    private ProjectService projectService;
    private PreparatoryProjectService preparatoryProjectService;

    @Autowired
    public ProjectFindByIdCommand(ProjectService projectService, PreparatoryProjectService preparatoryProjectService) {
        this.projectService = projectService;
        this.preparatoryProjectService = preparatoryProjectService;
    }


    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        preparatoryProjectService.findProjectById(sessionRequestContent);
        ModelAndView model;
        if (sessionRequestContent.isSuccessfulResult()) {
            HashMap attributes = sessionRequestContent.getRequestAttributes();
            HashMap params = sessionRequestContent.getRequestParameters();
            String projectId = (String) params.get(KeyWordsApp.PROJECT_ID_FIELD_NAME);
            Object project = attributes.get(KeyWordsSessionRequest.PROJECT);
            model = new ModelAndView(SUCCESSFUL_PATH + projectId);
            model.setViewName(SUCCESSFUL_VIEW);
            model.addObject(KeyWordsSessionRequest.PROJECT, project);
        } else {
            model = new ModelAndView(UNSUCCESSFUL_PATH);
        }
        sessionRequestContent.setModelAndViewResult(model);
    }

}
