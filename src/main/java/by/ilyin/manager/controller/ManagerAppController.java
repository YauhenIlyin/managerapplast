package by.ilyin.manager.controller;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.controller.command.project.*;
import by.ilyin.manager.controller.command.task.TaskCreateCommand;
import by.ilyin.manager.controller.command.task.TaskFindAllCommand;
import by.ilyin.manager.entity.Project;
import by.ilyin.manager.entity.Task;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsRequest;
import by.ilyin.manager.util.AppBaseDataCore;
import by.ilyin.manager.util.validator.ProjectEntityValidator;
import by.ilyin.manager.util.validator.impl.ProjectRequestValidator;
import by.ilyin.manager.util.validator.impl.TaskEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ManagerAppController {

    private AppBaseDataCore appBaseDataCore;
    private SessionRequestContent sessionRequestContent;
    private ProjectRequestValidator projectRequestValidator;
    private ProjectEntityValidator projectEntityValidator;
    private TaskEntityValidator taskEntityValidator;
    private ProjectFindAllCommand projectFindAllCommand;
    private ProjectCreateCommand projectCreateCommand;
    private ProjectFindByIdCommand projectFindByIdCommand;
    private ProjectUpdateCommand projectUpdateCommand;
    private ProjectDeleteCommand projectDeleteCommand;
    private TaskFindAllCommand taskFindAllCommand;
    private TaskCreateCommand taskCreateCommand;

    @Autowired
    public ManagerAppController(AppBaseDataCore appBaseDataCore,
                                SessionRequestContent sessionRequestContent,
                                ProjectRequestValidator projectRequestValidator,
                                ProjectEntityValidator projectEntityValidator,
                                TaskEntityValidator taskEntityValidator,
                                ProjectFindAllCommand projectFindAllCommand,
                                ProjectCreateCommand projectCreateCommand,
                                ProjectFindByIdCommand projectFindByIdCommand,
                                ProjectUpdateCommand projectUpdateCommand,
                                ProjectDeleteCommand projectDeleteCommand,
                                TaskFindAllCommand taskFindAllCommand,
                                TaskCreateCommand taskCreateCommand) {
        this.appBaseDataCore = appBaseDataCore;
        this.sessionRequestContent = sessionRequestContent;
        this.projectRequestValidator = projectRequestValidator;
        this.projectEntityValidator = projectEntityValidator;
        this.taskEntityValidator = taskEntityValidator;
        this.projectFindAllCommand = projectFindAllCommand;
        this.projectCreateCommand = projectCreateCommand;
        this.projectFindByIdCommand = projectFindByIdCommand;
        this.projectUpdateCommand = projectUpdateCommand;
        this.projectDeleteCommand = projectDeleteCommand;
        this.taskFindAllCommand = taskFindAllCommand;
        this.taskCreateCommand = taskCreateCommand;
    }

    @GetMapping("")
    public String projectsPage(Model model, HttpServletRequest request) {
        sessionRequestContent.initialize(request);
        sessionRequestContent.initializePage(request, projectRequestValidator);
        projectFindAllCommand.execute(sessionRequestContent);
        List<Project> projects;
        projects = (List) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PROJECTS);
        Page page = (Page) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PAGE_PAGE);
        basicInitializeProjectModel(model);
        model.addAttribute(KeyWordsRequest.PROJECTS, projects);
        model.addAttribute(KeyWordsRequest.PAGE_PAGE, page);
        return "projects";
    }

    @GetMapping("/new")
    public String projectCreationPage(@ModelAttribute("project") Project project,
                                      Model model) {
        basicInitializeProjectModel(model);
        return "project_creation";
    }

    @PostMapping("")
    public ModelAndView createProject(Model model,
                                      @ModelAttribute("project") @Valid Project project,
                                      BindingResult bindingResult) {
        basicInitializeProjectModel(model);
        ModelAndView mav;
        if (bindingResult.hasErrors()) {
            mav = new ModelAndView("projects/new");
//            basicInitializeProjectModel(mav);
            mav.addObject("project", project);
            mav.setViewName("project_creation");
        } else {
            System.out.println(2);
            HashMap<String, Object> attributes = new HashMap<>();
            attributes.put(KeyWordsRequest.PROJECT, project);
            sessionRequestContent.setRequestAttributes(attributes);
            projectCreateCommand.execute(sessionRequestContent);
            mav = new ModelAndView("redirect:/projects");
        }
        basicInitializeProjectModel(mav);
        return mav;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       @ModelAttribute("project") Project project,
                       Model model) {
        sessionRequestContent.setFiltering(true);
        sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        projectFindByIdCommand.execute(sessionRequestContent);
        String resultPage = "redirect:/projects";
        if (sessionRequestContent.isSuccessfulResult()) {
            project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PROJECT);
            model.addAttribute(KeyWordsRequest.PROJECT, project);
            resultPage = "project_by_id";
        }
        return resultPage;
    }

    @GetMapping("/{id}/edit")
    public String editProject(@PathVariable("id") long id,
                              @ModelAttribute("project") Project project,
                              Model model) {
        sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        projectFindByIdCommand.execute(sessionRequestContent);
        String resultPage;
        if (sessionRequestContent.isSuccessfulResult()) {
            project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PROJECT);
            model.addAttribute(KeyWordsRequest.PROJECT, project);
            resultPage = "project_by_id_edit";
        } else {
            resultPage = "projects";
        }
        basicInitializeProjectModel(model);
        return resultPage;
    }

    @PatchMapping("/{id}")
    public String updateProject(@PathVariable("id") long id,
                                @ModelAttribute("project") Project project,
                                Model model,
                                BindingResult bindingResult) {
        projectEntityValidator.validate(project, bindingResult);
        String resultPage = null;
        sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
        if (bindingResult.hasErrors()) {
            projectFindByIdCommand.execute(sessionRequestContent);
            if (sessionRequestContent.isSuccessfulResult()) {
//                Project mainProject = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PROJECT);
                model.addAttribute(KeyWordsRequest.PROJECT, project);
                resultPage = "project_by_id_edit";
            }
        } else {
            project.setId(id);
            sessionRequestContent.getRequestAttributes().put(KeyWordsRequest.PROJECT, project);
            projectUpdateCommand.execute(sessionRequestContent);
            if (sessionRequestContent.isSuccessfulResult()) {
                sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
                projectFindByIdCommand.execute(sessionRequestContent);
                project = (Project) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PROJECT);
                model.addAttribute(KeyWordsRequest.PROJECT, project);
            }
            resultPage = "redirect:/projects/{id}";
        }
        basicInitializeProjectModel(model);
        return resultPage;
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable("id") long id,
                                @ModelAttribute("project") Project project,
                                Model model,
                                BindingResult bindingResult) {
        String pageResult = "redirect:/projects";
        if (project != null) {
            sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + id);
            projectFindByIdCommand.execute(sessionRequestContent);
            if (sessionRequestContent.isSuccessfulResult()) {
                projectDeleteCommand.execute(sessionRequestContent); //todo  проверить тот ли проект внутри
            }
        }
        return pageResult;
    }

    @GetMapping("/{projectId}/tasks")
    public String tasksPage(@PathVariable("projectId") long projectId,
                            Project project,
                            Model model,
                            HttpServletRequest request) {
        String resultPage;
        sessionRequestContent.initialize(request);
        sessionRequestContent.initializePage(request, taskEntityValidator);
        sessionRequestContent.getRequestParameters().put(KeyWordsApp.PROJECT_ID_FIELD_NAME, "" + projectId);
        taskFindAllCommand.execute(sessionRequestContent);
        if (sessionRequestContent.isSuccessfulResult()) {
            List<Task> tasks = (List) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.TASKS);
            Page page = (Page) sessionRequestContent.getRequestAttributes().get(KeyWordsRequest.PAGE_PAGE);
            model.addAttribute(KeyWordsRequest.TASKS, tasks);
            model.addAttribute(KeyWordsRequest.PROJECT_ID, projectId);
            model.addAttribute(KeyWordsRequest.PAGE_PAGE, page);
            resultPage = "tasks/tasks";
        } else {
            resultPage = "redirect:/projects/{projectId}";
        }
        return resultPage;
    }

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


    private void basicInitializeProjectModel(ModelAndView model) {
        model.getModel().put("progLangs", appBaseDataCore.getProgrammingLanguageList());
        model.getModel().put("appServers", appBaseDataCore.getProgrammingLanguageList());
        model.getModel().put("databases", appBaseDataCore.getProgrammingLanguageList());
    }

    private void basicInitializeProjectModel(Model model) {
        model.addAttribute("progLangs", appBaseDataCore.getProgrammingLanguageList());
        model.addAttribute("appServers", appBaseDataCore.getApplicationServerList());
        model.addAttribute("databases", appBaseDataCore.getDatabaseList());
    }

}
