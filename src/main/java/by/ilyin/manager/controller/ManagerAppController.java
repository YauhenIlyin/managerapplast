package by.ilyin.manager.controller;

import by.ilyin.manager.controller.command.Command;
import by.ilyin.manager.controller.command.CommandFactory;
import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.CommandName;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.util.ModelViewDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/projects")
public class ManagerAppController {

    private SessionRequestContent sessionRequestContent;
    private CommandFactory commandFactory;
    private ModelViewDataBuilder projectModelViewBuilder;

    @Autowired
    public ManagerAppController(SessionRequestContent sessionRequestContent,
                                CommandFactory commandFactory,
                                @Qualifier("projectModelViewDataBuilderImpl") ModelViewDataBuilder projectModelViewBuilder) {
        this.sessionRequestContent = sessionRequestContent;
        this.commandFactory = commandFactory;
        this.projectModelViewBuilder = projectModelViewBuilder;
    }

    @GetMapping("")
    public ModelAndView projectsPage(ModelAndView model, HttpServletRequest request) {
        sessionRequestContent.initialize(request);
        sessionRequestContent.setModelAndViewResult(model);
        Command command = commandFactory.getCurrentCommand(CommandName.PROJECT_FIND_ALL);
        command.execute(sessionRequestContent);
        model = sessionRequestContent.getModelAndViewResult();
        return model;
    }

    @GetMapping("/new")
    public ModelAndView projectCreationPage(@ModelAttribute("project") Project project,
                                            ModelAndView model) {
        projectModelViewBuilder
                .addAppServers(model)
                .addDatabases(model)
                .addProgLangs(model);
        model.setViewName("project_creation");
        return model;
    }

    @PostMapping("")
    public ModelAndView createProject(ModelAndView model,
                                      @ModelAttribute("project") @Valid Project project,
                                      BindingResult bindingResult) {
        sessionRequestContent.setBindingResult(bindingResult);
        sessionRequestContent.getRequestAttributes().put(KeyWordsSessionRequest.PROJECT, project);
        sessionRequestContent.setModelAndViewResult(model);
        Command command = commandFactory.getCurrentCommand(CommandName.PROJECT_CREATE);
        command.execute(sessionRequestContent);
        model = sessionRequestContent.getModelAndViewResult();
        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable("id") int id,
                             @ModelAttribute("project") Project project,
                             ModelAndView model) {
        HashMap params = sessionRequestContent.getRequestParameters();
        params.put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        Command command = commandFactory.getCurrentCommand(CommandName.PROJECT_FIND_BY_ID);
        command.execute(sessionRequestContent);
        return sessionRequestContent.getModelAndViewResult();
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editProject(@PathVariable("id") long id,
                                    @ModelAttribute("project") Project project,
                                    ModelAndView model) {
        HashMap params = sessionRequestContent.getRequestParameters();
        params.put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        Command command = commandFactory.getCurrentCommand(CommandName.PROJECT_FIND_BY_ID);
        command.execute(sessionRequestContent);
        if (sessionRequestContent.isSuccessfulResult()) {
            project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsSessionRequest.PROJECT);
            model = new ModelAndView("/projects/" + id + "/edit");
            model.setViewName("project_by_id_edit");
            model.addObject(KeyWordsSessionRequest.PROJECT, project);
            projectModelViewBuilder
                    .addAppServers(model)
                    .addDatabases(model)
                    .addProgLangs(model);
        } else {
            model = new ModelAndView("redirect:/projects");
        }
        return model;
    }


    @PatchMapping("/{id}")
    public String updateProject(@PathVariable("id") long id,
                                @ModelAttribute("project") Project project,
                                Model model,
                                BindingResult bindingResult) {
        HashMap params = sessionRequestContent.getRequestParameters();
        HashMap attributes = sessionRequestContent.getRequestAttributes();
        params.put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        attributes.put(KeyWordsSessionRequest.PROJECT, project);
        attributes.put(KeyWordsSessionRequest.MODEL, model);
        sessionRequestContent.setBindingResult(bindingResult);
        Command command = commandFactory.getCurrentCommand(CommandName.PROJECT_UPDATE);
        command.execute(sessionRequestContent);
        String resultView = (String) attributes.get(KeyWordsSessionRequest.RESULT_VIEW);
        return resultView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteProject(@PathVariable("id") long id,
                                      @ModelAttribute("project") Project project,
                                      ModelAndView model) {
        Command command = commandFactory.getCurrentCommand(CommandName.PROJECT_DELETE);
        HashMap params = sessionRequestContent.getRequestParameters();
        HashMap attributes = sessionRequestContent.getRequestAttributes();
        params.put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        attributes.put(KeyWordsSessionRequest.PROJECT, project);
        command.execute(sessionRequestContent);
        model = sessionRequestContent.getModelAndViewResult();
        return model;
    }

    @GetMapping("/{projectId}/tasks")
    public ModelAndView tasksPage(@PathVariable("projectId") long projectId,
                                  ModelAndView model,
                                  HttpServletRequest request) {
        HashMap params = sessionRequestContent.getRequestParameters();
        sessionRequestContent.initialize(request);
        params.put(KeyWordsSessionRequest.PROJECT_ID, "" + projectId);
        Command command = commandFactory.getCurrentCommand(CommandName.TASK_FIND_ALL);
        command.execute(sessionRequestContent);
        model = sessionRequestContent.getModelAndViewResult();
        return model;
    }

    /*

    @GetMapping("{projectId}/tasks/new")
    public String projectCreationPage(@PathVariable("projectId") long projectId,
                                      @ModelAttribute Task task,
                                      Model model) {
        String resultPage = "redirect:/projects";
        sessionRequestContent.setFiltering(true);
        sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + projectId);
        projectFindByIdCommand.execute(sessionRequestContent);
        if (sessionRequestContent.isSuccessfulResult()) {
            model.addAttribute(KeyWordsRequest.PROJECT_ID, (Long) projectId);
            resultPage = "/tasks/task_creation";
        }
        return resultPage;
    }

    @PostMapping("{projectId}/tasks")
    public ModelAndView projectCreationPage(@PathVariable("projectId") long projectId,
                                            @ModelAttribute("task") @Valid Task task,
                                            Model model,
                                            BindingResult bindingResult) {
        ModelAndView mav = null;
        sessionRequestContent.setFiltering(true);
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("projects/{projectId}/tasks/new");
            mav.addObject("task", task);
            mav.addObject("projectId", projectId);
            mav.setViewName("tasks/task_creation");
            System.out.println("error");
        } else {
            mav = new ModelAndView("redirect:/projects/{projectId}/tasks");
            sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + projectId);
            projectFindByIdCommand.execute(sessionRequestContent);
            if (sessionRequestContent.isSuccessfulResult()) {
                Project project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PROJECT);
                task.setProject(project);
                sessionRequestContent.getRequestAttributes().put(KeyWordsRequest.TASK, task);
                sessionRequestContent.setSuccessfulResult(Boolean.FALSE);
                taskCreateCommand.execute(sessionRequestContent);
            }
        }
        model.addAttribute(KeyWordsRequest.TASK, task);
        return mav;
    }
*/

}
