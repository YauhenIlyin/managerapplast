package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class ProjectFindAllCommand implements Command {

    private PreparatoryProjectService preparatoryProjectService;
    private ProjectService projectService;

    @Autowired
    public ProjectFindAllCommand(PreparatoryProjectService preparatoryProjectService,
                                 ProjectService projectService) {
        this.preparatoryProjectService = preparatoryProjectService;
        this.projectService = projectService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        preparatoryProjectService.buildFindAllCriteriaSpecification(sessionRequestContent);
        preparatoryProjectService.buildSoftDeleteCriteriaSpecification(sessionRequestContent);
        List<Project> projects;
        Specification<Project> spec = sessionRequestContent.getSpecificationBuilder().build();
        Pageable pageable = sessionRequestContent.getPageable();
        Page page = projectService.findAll(spec, pageable);
        projects = page.getContent();
        sessionRequestContent.getRequestAttributes().put(KeyWordsSessionRequest.PROJECTS, projects);
        sessionRequestContent.getRequestAttributes().put(KeyWordsSessionRequest.PAGE_PAGE, page);
    }
    /*
    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        preparatoryProjectService.buildFindAllCriteriaSpecification(sessionRequestContent);
        preparatoryProjectService.buildSoftDeleteCriteriaSpecification(sessionRequestContent);
        List<Project> projects;
        Specification<Project> spec = sessionRequestContent.getProjectSpecificationBuilder().build();
        Pageable pageable = sessionRequestContent.getPageable();
        Page page = projectService.findAll(spec, pageable);
        projects = page.getContent();
        sessionRequestContent.getRequestAttributes().put(KeyWordsSessionRequest.PROJECTS, projects);
        sessionRequestContent.getRequestAttributes().put(KeyWordsSessionRequest.PAGE_PAGE, page);
    }
     */

//    List<Project> projects;
//    projects = (List) sessionRequestContent.getRequestAttributes().get(KeyWordsSessionRequest.PROJECTS);
//    Page page = (Page) sessionRequestContent.getRequestAttributes().get(KeyWordsSessionRequest.PAGE_PAGE);
//    basicInitializeProjectModel(model);
//        model.addAttribute(KeyWordsSessionRequest.PROJECTS, projects);
//        model.addAttribute(KeyWordsSessionRequest.PAGE_PAGE, page);
//        return "projects";

}
