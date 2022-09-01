package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("prototype")
public class ProjectUpdateCommand implements Command {

    private ProjectService projectService;
    private PreparatoryProjectService preparatoryProjectService;

    @Autowired
    public ProjectUpdateCommand(ProjectService projectService, PreparatoryProjectService preparatoryProjectService) {
        this.projectService = projectService;
        this.preparatoryProjectService = preparatoryProjectService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        Project project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsSessionRequest.PROJECT);
        long currentId = project.getId();
        boolean result = false;
        try {
            String currentRole = sessionRequestContent.getAuthDataManager().getCurrentUserRole();
            Optional<Project> optionalProject = null;
            if (currentRole.equals(KeyWordsApp.ROLE_ADMIN_VALUE)) {
                optionalProject = projectService.findById(currentId);
            } else {
                optionalProject = projectService.findByIdAndIsDeletedEquals(currentId, Boolean.FALSE);
            }
            if (optionalProject.isPresent()) {
                result = projectService.update(project);
            }
        } catch (ManagerAppAuthException e) {
            //todo log
        }
        sessionRequestContent.setSuccessfulResult(result);
    }
}
