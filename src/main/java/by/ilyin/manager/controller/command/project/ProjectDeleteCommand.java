package by.ilyin.manager.controller.command.project;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.service.PreparatoryProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ProjectDeleteCommand implements Command {

    private static final String SUCCESSFUL_VIEW = "redirect:/projects";
    private static final String UNSUCCESSFUL_VIEW = SUCCESSFUL_VIEW;

    private final PreparatoryProjectService preparatoryProjectService;

    @Autowired
    public ProjectDeleteCommand(PreparatoryProjectService preparatoryProjectService) {
        this.preparatoryProjectService = preparatoryProjectService;
    }

    @Override
    public void execute(SessionRequestContent sessionRequestContent) {
        preparatoryProjectService.deleteProject(sessionRequestContent);
        ModelAndView model = new ModelAndView(SUCCESSFUL_VIEW);
        sessionRequestContent.setModelAndViewResult(model);
    }

}
