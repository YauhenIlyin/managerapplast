package by.ilyin.manager.util.validator.impl;

import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.util.validator.RequestValidator;
import org.springframework.stereotype.Component;
import java.util.HashSet;

@Component
public class ProjectRequestValidator implements RequestValidator {

    private final HashSet<String> projectFieldNamesSet;

    public ProjectRequestValidator() {
        projectFieldNamesSet = new HashSet<>();
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_ID_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_NAME_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_DESCRIPTION_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_EMPLOYEE_COUNT_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_CREATION_DATE_TIME_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_CREATOR_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_TASKS_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_PROG_LANG_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_APP_SERVER_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECT_DATABASE_FIELD_NAME);
        projectFieldNamesSet.add(KeyWordsApp.PROJECTS_IS_DELETED_FIELD_NAME);
    }

    public boolean isValidFieldName(String fieldName) {
        return projectFieldNamesSet.contains(fieldName);
    }

}
