package by.ilyin.manager.service.impl;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.entity.Task;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.service.*;
import by.ilyin.manager.util.validator.BaseValidator;
import by.ilyin.manager.util.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PreparatoryTaskServiceImpl implements PreparatoryTaskService {

    private TaskService taskService;
    private PreparatoryProjectService preparatoryProjectService;
    private PageableFilterService pageableFilterService;
    private BaseValidator baseValidator;
    private RequestValidator requestValidator;

    @Autowired
    public PreparatoryTaskServiceImpl(TaskService taskService,
                                      PreparatoryProjectService preparatoryProjectService,
                                      PageableFilterService pageableFilterService,
                                      BaseValidator baseValidator,
                                      @Qualifier("taskRequestValidator") RequestValidator requestValidator) {
        this.taskService = taskService;
        this.preparatoryProjectService = preparatoryProjectService;
        this.pageableFilterService = pageableFilterService;
        this.baseValidator = baseValidator;
        this.requestValidator = requestValidator;
    }

    @Override
    public void findAllTasks(SessionRequestContent sessionRequestContent) {
        HashMap<String, String> params = sessionRequestContent.getRequestParameters();
        String projectIdStr = params.get(KeyWordsSessionRequest.PROJECT_ID);
        boolean isNumber = baseValidator.isValidStrAsIntegerNumber(projectIdStr);
        boolean isSuccessfulResult = false;
        if (isNumber) {
            params.put(KeyWordsApp.PROJECT_ID_FIELD_NAME, projectIdStr);
            preparatoryProjectService.findProjectById(sessionRequestContent);
            if (sessionRequestContent.isSuccessfulResult()) {
                List<Task> taskList;
                String currentRole = getCurrentRole(sessionRequestContent);
                long projectIdNumber = Long.parseLong(projectIdStr);
                buildPageable(sessionRequestContent);
                Pageable pageable = sessionRequestContent.getPageable();
                Page page;
                if (currentRole.equals(KeyWordsApp.ROLE_ADMIN_VALUE)) {
                    page = taskService.findAllByProjectId(projectIdNumber, pageable);
                } else {
                    page = taskService.findAllByProjectIdAndIsDeletedEquals(projectIdNumber, Boolean.FALSE, pageable);
                }
                taskList = page.getContent();
                sessionRequestContent.getRequestAttributes().put(KeyWordsSessionRequest.TASKS, taskList);
                sessionRequestContent.getRequestAttributes().put(KeyWordsSessionRequest.PAGE_PAGE, page);
                isSuccessfulResult = Boolean.TRUE;
            }
            sessionRequestContent.setSuccessfulResult(isSuccessfulResult);
        }
    }

    @Override
    public void createTask(SessionRequestContent sessionRequestContent) {

    }

    @Override
    public void buildPageable(SessionRequestContent sessionRequestContent) {
        pageableFilterService.initializePageable(sessionRequestContent, requestValidator);
    }

    private String getCurrentRole(SessionRequestContent sessionRequestContent) {
        String currentRole;
        try {
            currentRole = sessionRequestContent.getAuthDataManager().getCurrentUserRole();
        } catch (ManagerAppAuthException e) {
            currentRole = KeyWordsApp.ROLE_USER_VALUE;
            //todo log
        }
        return currentRole;
    }
}
