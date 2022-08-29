package by.ilyin.manager.util.validator.impl;

import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.util.validator.ProjectEntityValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ProjectEntityValidatorImpl implements ProjectEntityValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println(target.getClass());
        if (target != null && supports(target.getClass())) {
            System.out.println("into validator"); //todo
            Project project = (Project) target;
            if (project.getProjectName() == null || project.getProjectName().length() == 0) {
                errors.rejectValue(KeyWordsApp.PROJECT_NAME_FIELD_NAME, "", "Project name must not be empty");
            } else if (project.getProjectName().length() > 30) {
                errors.rejectValue(KeyWordsApp.PROJECT_NAME_FIELD_NAME, "", "Project name length no more than 30 characters");
            }

            if (project.getDescription() == null || project.getDescription().length() == 0) {
                errors.rejectValue(KeyWordsApp.PROJECT_DESCRIPTION_FIELD_NAME, "", "Project description must not be empty");
            } else if (project.getDescription().length() > 400) {
                errors.rejectValue(KeyWordsApp.PROJECT_DESCRIPTION_FIELD_NAME, "", "Project description length no more than 400 characters");
            }

            if (project.getEmployeeCount() == null || project.getEmployeeCount() <= 0) {
                errors.rejectValue(KeyWordsApp.PROJECT_EMPLOYEE_COUNT_FIELD_NAME, "", "Employee count must not be empty");
            } else if (project.getEmployeeCount() > 1000000) {
                errors.rejectValue(KeyWordsApp.PROJECT_EMPLOYEE_COUNT_FIELD_NAME, "", "Too big number of employees");
            }
        }
    }
}
