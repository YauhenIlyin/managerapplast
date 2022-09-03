package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.util.ModelViewDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
public class ProjectFindAllCommand implements Command {

    private static final String SUCCESSFUL_VIEW = "projects";
    private static final String UNSUCCESSFUL_VIEW = SUCCESSFUL_VIEW;

    private PreparatoryProjectService preparatoryProjectService;
    private ModelViewDataBuilder modelViewDataBuilder;

    @Autowired
    public ProjectFindAllCommand(PreparatoryProjectService preparatoryProjectService,
                                 @Qualifier("projectModelViewDataBuilderImpl") ModelViewDataBuilder modelViewDataBuilder) {
        this.preparatoryProjectService = preparatoryProjectService;
        this.modelViewDataBuilder = modelViewDataBuilder;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        preparatoryProjectService.findAllProjects(sessionRequestContent);
        ModelAndView modelAndView = new ModelAndView(SUCCESSFUL_VIEW);
        HashMap requestAttributes = sessionRequestContent.getRequestAttributes();
        Object projects = requestAttributes.get(KeyWordsSessionRequest.PROJECTS);
        Object page = requestAttributes.get(KeyWordsSessionRequest.PAGE_PAGE);
        modelAndView.addObject(KeyWordsSessionRequest.PROJECTS, projects);
        modelAndView.addObject(KeyWordsSessionRequest.PAGE_PAGE, page);
        basicInitializeProjectModel(modelAndView);
        sessionRequestContent.setModelAndViewResult(modelAndView);
    }

    private void basicInitializeProjectModel(ModelAndView model) {
        modelViewDataBuilder
                .addProgLangs(model)
                .addAppServers(model)
                .addDatabases(model);
    }
}
