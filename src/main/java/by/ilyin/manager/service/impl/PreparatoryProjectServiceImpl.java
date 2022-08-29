package by.ilyin.manager.service.impl;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordFilterProcess;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.repository.specification.ProjectSpecificationBuilder;
import by.ilyin.manager.repository.specification.SearchCriteria;
import by.ilyin.manager.service.PreparatoryProjectService;
import by.ilyin.manager.util.AppBaseDataCore;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Scope("prototype")
public class PreparatoryProjectServiceImpl implements PreparatoryProjectService {

    public PreparatoryProjectServiceImpl() {
    }

    @Override
    public void buildFindAllCriteriaSpecification(SessionRequestContent sessionRequestContent) {
        ProjectSpecificationBuilder builder = sessionRequestContent.getProjectSpecificationBuilder();
        if (sessionRequestContent.isFiltering()) {
            HashMap<String, String> params = sessionRequestContent.getRequestParameters();
            AppBaseDataCore appBaseDataCore = sessionRequestContent.getAppBaseDataCore();
            String langIndex = params.get(KeyWordFilterProcess.PROJECT_PROG_LANG_FILTER_NAME);
            String appServerIndex = params.get(KeyWordFilterProcess.PROJECT_APP_SERVER_FILTER_NAME);
            String databaseIndex = params.get(KeyWordFilterProcess.PROJECT_DATABASE_FILTER_NAME);
            String employeeCount = params.get(KeyWordFilterProcess.PROJECT_EMPLOYEE_COUNT_FILTER_NAME);
            String employeeCountOperation = params.get(KeyWordFilterProcess.PROJECT_EMPLOYEE_OPERATION_FILTER_NAME);
            boolean conditionContainer = langIndex != null && !langIndex.equals(KeyWordFilterProcess.DEFAULT_EMPTY_VALUE);
            builder = checkAddCriteria(builder, conditionContainer, KeyWordsApp.PROJECT_PROG_LANG_FIELD_NAME, KeyWordFilterProcess.OPERATION_EQUALS,
                    langIndex, appBaseDataCore.getProgrammingLanguageList());

            conditionContainer = appServerIndex != null && !appServerIndex.equals(KeyWordFilterProcess.DEFAULT_EMPTY_VALUE);
            builder = checkAddCriteria(builder, conditionContainer, KeyWordsApp.PROJECT_APP_SERVER_FIELD_NAME, KeyWordFilterProcess.OPERATION_EQUALS,
                    appServerIndex, appBaseDataCore.getApplicationServerList());

            conditionContainer = databaseIndex != null && !databaseIndex.equals(KeyWordFilterProcess.DEFAULT_EMPTY_VALUE);
            builder = checkAddCriteria(builder, conditionContainer, KeyWordsApp.PROJECT_DATABASE_FIELD_NAME, KeyWordFilterProcess.OPERATION_EQUALS,
                    databaseIndex, appBaseDataCore.getDatabaseList());

            boolean isNumber = false;
            if (employeeCount != null && !employeeCount.equals(KeyWordFilterProcess.DEFAULT_EMPTY_VALUE)) {
                try {
                    Integer.parseInt(employeeCount);
                    isNumber = true;
                } catch (NumberFormatException e) {

                }
            }
            conditionContainer = employeeCountOperation != null && !employeeCountOperation.equals(KeyWordFilterProcess.DEFAULT_EMPTY_VALUE) && isNumber;
            builder = checkAddCriteria(builder, conditionContainer, KeyWordsApp.PROJECT_EMPLOYEE_COUNT_FIELD_NAME, employeeCountOperation,
                    employeeCount);

            builder = softDeleteCriteria(builder, sessionRequestContent);
            completeBuilding(builder, sessionRequestContent);
        }
    }

    public void buildSoftDeleteCriteriaSpecification(SessionRequestContent sessionRequestContent) {
        ProjectSpecificationBuilder builder = sessionRequestContent.getProjectSpecificationBuilder();
        softDeleteCriteria(builder, sessionRequestContent);
        completeBuilding(builder, sessionRequestContent);
    }

    public void buildFindByIdCriteriaSpecification(SessionRequestContent sessionRequestContent, Long id) {
        ProjectSpecificationBuilder builder = sessionRequestContent.getProjectSpecificationBuilder();
        checkAddCriteria(builder, id >= 0, KeyWordsApp.PROJECT_ID_FIELD_NAME, KeyWordFilterProcess.OPERATION_EQUALS, id);
        softDeleteCriteria(builder, sessionRequestContent);
        completeBuilding(builder, sessionRequestContent);
    }

    private ProjectSpecificationBuilder checkAddCriteria(ProjectSpecificationBuilder builder, boolean isCorrectConditions, String fieldName, String operation, Object value) {
        if (isCorrectConditions) {
            if (builder == null) {
                builder = new ProjectSpecificationBuilder();
            }
            addCriteria(builder, fieldName, operation, value);
        }
        return builder;
    }

    private ProjectSpecificationBuilder checkAddCriteria(ProjectSpecificationBuilder builder, boolean isCorrectConditions, String fieldName, String operation, String index, List list) {
        if (isCorrectConditions) {
            if (builder == null) {
                builder = new ProjectSpecificationBuilder();
            }
            Object value = list.get(Integer.parseInt(index));
            addCriteria(builder, fieldName, operation, value);
        }
        return builder;
    }

    private ProjectSpecificationBuilder addCriteria(ProjectSpecificationBuilder builder, String fieldName, String operation, Object value) {
        SearchCriteria searchCriteria = new SearchCriteria(fieldName, operation, value);
        builder.with(searchCriteria);
        return builder;
    }

    private ProjectSpecificationBuilder softDeleteCriteria(ProjectSpecificationBuilder builder, SessionRequestContent sessionRequestContent) {
        if (builder == null) {
            builder = sessionRequestContent.getProjectSpecificationBuilder();
        }
        String currentUserRole = null;
        try {
            currentUserRole = sessionRequestContent.getAuthDataManager().getCurrentUserRole();
        } catch (ManagerAppAuthException e) {
            e.printStackTrace(); //todo log
        }
        if (currentUserRole.equals(KeyWordsApp.ROLE_USER_VALUE)) {
            SearchCriteria searchCriteria = new SearchCriteria("isDeleted", KeyWordFilterProcess.OPERATION_EQUALS, false);
            builder.with(searchCriteria);
        }
        return builder;
    }

    private void completeBuilding(ProjectSpecificationBuilder builder, SessionRequestContent sessionRequestContent) {
        if (builder == null) {
            sessionRequestContent.setFiltering(false);
        } else {
            sessionRequestContent.setFiltering(true);
        }
        //sessionRequestContent.setProjectSpecificationBuilder(builder); //todo
    }
}
