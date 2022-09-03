package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.service.ProjectService;
import by.ilyin.manager.util.ModelViewDataBuilder;
import by.ilyin.manager.util.validator.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.HashMap;

@Component
public class ProjectUpdateCommand implements Command {

    private static final String SUCCESSFUL_VIEW = "project_by_id";
    private static final String UNSUCCESSFUL_VIEW = "project_by_id_edit";

    private ProjectService projectService;
    private PreparatoryProjectService preparatoryProjectService;
    private EntityValidator entityValidator;
    private ModelViewDataBuilder modelViewDataBuilder;

    @Autowired
    public ProjectUpdateCommand(ProjectService projectService,
                                PreparatoryProjectService preparatoryProjectService,
                                ModelViewDataBuilder modelViewDataBuilder,
                                @Qualifier("projectEntityValidatorImpl") EntityValidator entityValidator) {
        this.projectService = projectService;
        this.preparatoryProjectService = preparatoryProjectService;
        this.modelViewDataBuilder = modelViewDataBuilder;
        this.entityValidator = entityValidator;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        HashMap attributes = sessionRequestContent.getRequestAttributes();
        BindingResult bindingResult = sessionRequestContent.getBindingResult();
        Project project = (Project) attributes.get(KeyWordsSessionRequest.PROJECT);
        String resultView;
        Model model = (Model) sessionRequestContent.getRequestAttributes().get(KeyWordsSessionRequest.MODEL);
        entityValidator.validate(project, bindingResult);
        if (bindingResult.hasErrors()) {
            resultView = UNSUCCESSFUL_VIEW;
            project = (Project) attributes.get(KeyWordsSessionRequest.PROJECT);
            model.addAttribute(KeyWordsSessionRequest.PROJECT, project);
        } else {
            preparatoryProjectService.updateProject(sessionRequestContent);
            if (sessionRequestContent.isSuccessfulResult()) {
                resultView = SUCCESSFUL_VIEW;
                model.addAttribute(KeyWordsSessionRequest.PROJECT, project);
            } else {
                resultView = "redirect:/projects";
            }
        }
        basicInitializeProjectModel(model);
        attributes.put(KeyWordsSessionRequest.RESULT_VIEW, resultView);
    }

    private void basicInitializeProjectModel(Model model) {
        modelViewDataBuilder
                .addProgLangs(model)
                .addAppServers(model)
                .addDatabases(model);
    }
}
