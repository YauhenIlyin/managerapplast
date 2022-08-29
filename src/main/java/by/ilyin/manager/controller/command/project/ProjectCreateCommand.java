package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.entity.User;
import by.ilyin.manager.evidence.KeyWordsRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.service.CustomUserService;
import by.ilyin.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope("prototype")
public class ProjectCreateCommand implements Command {

    private ProjectService projectService;
    private CustomUserService customUserService;

    @Autowired
    public ProjectCreateCommand(ProjectService projectService, CustomUserService customUserService) {
        this.projectService = projectService;
        this.customUserService = customUserService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        Project project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PROJECT);
        long id = 0;
        try {
            id = sessionRequestContent.getAuthDataManager().getCurrentUserId();
        } catch (ManagerAppAuthException e) {
            //todo log
        }
        Optional<User> user = customUserService.findById(id); //todo exception
        project.setCreator(user.get());
        projectService.save(project);
        sessionRequestContent.setSuccessfulResult(true);
    }
}
