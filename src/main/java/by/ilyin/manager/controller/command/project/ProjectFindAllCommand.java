package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.util.AppBaseDataCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
public class ProjectFindAllCommand implements Command {


    private static final String SUCCESSFUL_PATH = "/projects";
    private static final String SUCCESSFUL_VIEW = "projects";
    private static final String UNSUCCESSFUL_PATH = "/projects";
    private static final String UNSUCCESSFUL_VIEW = "projects";

    private PreparatoryProjectService preparatoryProjectService;
    private final AppBaseDataCore appBaseDataCore;

    @Autowired
    public ProjectFindAllCommand(PreparatoryProjectService preparatoryProjectService,
                                 AppBaseDataCore appBaseDataCore) {
        this.preparatoryProjectService = preparatoryProjectService;
        this.appBaseDataCore = appBaseDataCore;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        preparatoryProjectService.findAllProjects(sessionRequestContent);
        ModelAndView modelAndView = new ModelAndView(SUCCESSFUL_PATH);
        modelAndView.setViewName(SUCCESSFUL_VIEW);
        HashMap requestAttributes = sessionRequestContent.getRequestAttributes();
        Object projects = requestAttributes.get(KeyWordsSessionRequest.PROJECTS);
        Object page = requestAttributes.get(KeyWordsSessionRequest.PAGE_PAGE);
        modelAndView.addObject(KeyWordsSessionRequest.PROJECTS, projects);
        modelAndView.addObject(KeyWordsSessionRequest.PAGE_PAGE, page);
        basicInitializeProjectModel(modelAndView);
        sessionRequestContent.setModelAndViewResult(modelAndView);
    }

    private void basicInitializeProjectModel(ModelAndView model) {
        model.addObject("progLangs", appBaseDataCore.getProgrammingLanguageList());
        model.addObject("appServers", appBaseDataCore.getApplicationServerList());
        model.addObject("databases", appBaseDataCore.getDatabaseList());
    }
}
