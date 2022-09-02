package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.service.CustomUserService;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.service.ProjectService;
import by.ilyin.manager.util.ModelViewDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Component
@Scope("prototype")
public class ProjectCreateCommand implements Command {

    private static final String SUCCESSFUL_PATH = "redirect:/projects";
    private static final String SUCCESSFUL_VIEW = "projects";
    private static final String UNSUCCESSFUL_PATH = "projects/new";
    private static final String UNSUCCESSFUL_VIEW = "project_creation";

    private PreparatoryProjectService preparatoryProjectService;
    private ProjectService projectService;
    private CustomUserService customUserService;
    private ModelViewDataBuilder modelViewDataBuilder;

    @Autowired
    public ProjectCreateCommand(PreparatoryProjectService preparatoryProjectService, ProjectService projectService, CustomUserService customUserService, ModelViewDataBuilder modelViewDataBuilder) {
        this.preparatoryProjectService = preparatoryProjectService;
        this.projectService = projectService;
        this.customUserService = customUserService;
        this.modelViewDataBuilder = modelViewDataBuilder;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        BindingResult bindingResult = sessionRequestContent.getBindingResult();
        ModelAndView model;
        String resultPath;
        String resultView;
        Project project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsSessionRequest.PROJECT);
        if (bindingResult != null && bindingResult.hasErrors()) {
            resultPath = UNSUCCESSFUL_PATH;
            resultView = UNSUCCESSFUL_VIEW;
            model = new ModelAndView(resultPath);
            model.setViewName(resultView);
        } else {
            preparatoryProjectService.createProject(sessionRequestContent);
            resultPath = SUCCESSFUL_PATH;
            model = new ModelAndView(resultPath);
        }
        model.addObject(KeyWordsSessionRequest.PROJECT, project);
        basicInitializeProjectModel(model);
        sessionRequestContent.setModelAndViewResult(model);
    }

    private void basicInitializeProjectModel(ModelAndView model) {
        modelViewDataBuilder
                .addProgLangs(model)
                .addAppServers(model)
                .addDatabases(model);
    }
}
