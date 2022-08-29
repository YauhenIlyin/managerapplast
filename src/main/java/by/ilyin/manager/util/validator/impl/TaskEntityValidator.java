package by.ilyin.manager.util.validator.impl;

import by.ilyin.manager.entity.Task;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.util.validator.RequestValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;

@Component
public class TaskEntityValidator implements RequestValidator, Validator {

    private HashSet<String> taskFieldNamesSet;

    public TaskEntityValidator() {
        taskFieldNamesSet = new HashSet<String>();
        taskFieldNamesSet.add(KeyWordsApp.TASK_ID_FIELD_NAME);
        taskFieldNamesSet.add(KeyWordsApp.TASK_NAME_FIELD_NAME);
        taskFieldNamesSet.add(KeyWordsApp.TASK_DESCRIPTION_FIELD_NAME);
        taskFieldNamesSet.add(KeyWordsApp.TASK_CREATION_DATE_TIME_FIELD_NAME);
        taskFieldNamesSet.add(KeyWordsApp.TASK_CREATOR_FIELD_NAME);
        taskFieldNamesSet.add(KeyWordsApp.TASK_PROJECT_FIELD_NAME);
        taskFieldNamesSet.add(KeyWordsApp.TASK_IS_DELETED_FIELD_NAME);
    }

    @Override
    public boolean isValidFieldName(String fieldName) {
        return taskFieldNamesSet.contains(fieldName);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
