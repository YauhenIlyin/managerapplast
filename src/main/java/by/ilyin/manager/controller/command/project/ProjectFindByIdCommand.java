package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("prototype")
public class ProjectFindByIdCommand implements Command {

    private ProjectService projectService;
    private PreparatoryProjectService preparatoryProjectService;

    @Autowired
    public ProjectFindByIdCommand(ProjectService projectService, PreparatoryProjectService preparatoryProjectService) {
        this.projectService = projectService;
        this.preparatoryProjectService = preparatoryProjectService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        String currentId = sessionRequestContent.getRequestParameters().get(KeyWordsApp.PROJECT_ID_FIELD_NAME);
        boolean result = false;
        try {
            Long currentIdValue = Long.parseLong(currentId);
//            preparatoryProjectService.buildFindByIdCriteriaSpecification(sessionRequestContent, currentIdValue);
//            preparatoryProjectService.buildSoftDeleteCriteriaSpecification(sessionRequestContent);
//            Specification spec = sessionRequestContent.getProjectSpecificationBuilder().build();
//            Optional<Project> project = projectService.findById(spec);
            String currentRole = sessionRequestContent.getAuthDataManager().getCurrentUserRole();
            Optional<Project> optionalProject = null;
            if (currentRole.equals(KeyWordsApp.ROLE_ADMIN_VALUE)) {
                optionalProject = projectService.findById(currentIdValue);
            } else {
                optionalProject = projectService.findByIdAndIsDeletedEquals(currentIdValue, Boolean.FALSE);
            }
            if (optionalProject.isPresent()) {
                result = true;
                Project currentProject = optionalProject.get();
                sessionRequestContent.getRequestAttributes().put(KeyWordsRequest.PROJECT, currentProject);
            }
        } catch (NumberFormatException e) {
            //todo log
        } catch (ManagerAppAuthException e) {
            //todo log
        }
        sessionRequestContent.setSuccessfulResult(result);
    }
}
