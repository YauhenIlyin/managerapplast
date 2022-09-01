package by.ilyin.manager.service.impl;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.repository.specification.SpecificationBuilder;
import by.ilyin.manager.repository.specification.SearchCriteria;
import by.ilyin.manager.service.PageableFilterService;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.util.AppBaseDataCore;
import by.ilyin.manager.util.validator.BaseValidator;
import by.ilyin.manager.util.validator.impl.ProjectRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PreparatoryProjectServiceImpl implements PreparatoryProjectService {

    private final AppBaseDataCore appBaseDataCore;
    private final PageableFilterService pageableFilterService;
    private final BaseValidator baseValidator;
    private final ProjectRequestValidator projectRequestValidator;

    @Autowired
    public PreparatoryProjectServiceImpl(AppBaseDataCore appBaseDataCore, PageableFilterService pageableFilterService, BaseValidator baseValidator, ProjectRequestValidator projectRequestValidator) {
        this.appBaseDataCore = appBaseDataCore;
        this.pageableFilterService = pageableFilterService;
        this.baseValidator = baseValidator;
        this.projectRequestValidator = projectRequestValidator;
    }

    @Override
    public void buildFindAllCriteriaSpecification(SessionRequestContent sessionRequestContent) throws ManagerAppAuthException {
        SpecificationBuilder builder = sessionRequestContent.getSpecificationBuilder();
        HashMap<String, String> params = sessionRequestContent.getRequestParameters();

        String langIndex = params.get(KeyWordsSessionRequest.FILTER_PROJECT_PROG_LANG_FILTER_NAME);
        String appServerIndex = params.get(KeyWordsSessionRequest.FILTER_PROJECT_APP_SERVER_FILTER_NAME);
        String databaseIndex = params.get(KeyWordsSessionRequest.FILTER_PROJECT_DATABASE_FILTER_NAME);
        String employeeCount = params.get(KeyWordsSessionRequest.FILTER_PROJECT_EMPLOYEE_COUNT_FILTER_NAME);
        String employeeCountOperation = params.get(KeyWordsSessionRequest.FILTER_PROJECT_EMPLOYEE_OPERATION_FILTER_NAME);

        boolean progLangCondition = langIndex != null && !langIndex.equals(KeyWordsSessionRequest.DEFAULT_EMPTY_VALUE);
        boolean appServerCondition = appServerIndex != null && !appServerIndex.equals(KeyWordsSessionRequest.DEFAULT_EMPTY_VALUE);
        boolean databaseCondition = databaseIndex != null && !databaseIndex.equals(KeyWordsSessionRequest.DEFAULT_EMPTY_VALUE);
        boolean employeeCountCondition = baseValidator.isValidStrAsIntegerNumber(employeeCount);

        pageableFilterService
                .addCriteria(
                        builder,
                        progLangCondition,
                        KeyWordsApp.PROJECT_PROG_LANG_FIELD_NAME,
                        KeyWordsSessionRequest.FILTER_OPERATION_EQUALS,
                        langIndex,
                        appBaseDataCore.getProgrammingLanguageList()
                )
                .addCriteria(
                        builder,
                        appServerCondition,
                        KeyWordsApp.PROJECT_APP_SERVER_FIELD_NAME,
                        KeyWordsSessionRequest.FILTER_OPERATION_EQUALS,
                        appServerIndex,
                        appBaseDataCore.getApplicationServerList()
                )
                .addCriteria(
                        builder,
                        databaseCondition,
                        KeyWordsApp.PROJECT_DATABASE_FIELD_NAME,
                        KeyWordsSessionRequest.FILTER_OPERATION_EQUALS,
                        databaseIndex,
                        appBaseDataCore.getDatabaseList()
                )
                .addCriteria(
                        builder,
                        employeeCountCondition,
                        KeyWordsApp.PROJECT_EMPLOYEE_COUNT_FIELD_NAME,
                        employeeCountOperation,
                        employeeCount
                )
                .addSoftDeleteCriteriaSpecification(
                        builder,
                        sessionRequestContent.getAuthDataManager().getCurrentUserRole()
                );
    }

    @Override
    public void buildPageable(SessionRequestContent sessionRequestContent) {
        pageableFilterService.initializePageable(sessionRequestContent, projectRequestValidator);
    }

}
