package by.ilyin.manager.repository.specification.impl;

import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.repository.specification.FieldCriteriaTypes;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProjectFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String, Boolean> isAndProjectCriteriaTypes;

    private ProjectFieldCriteriaTypesImpl() {
        HashMap<String, Boolean> criteriaTypes = new HashMap();
        criteriaTypes.put(KeyWordsApp.PROJECT_ID_FIELD_NAME, Boolean.TRUE);
        criteriaTypes.put(KeyWordsApp.PROJECT_EMPLOYEE_COUNT_FIELD_NAME, Boolean.TRUE);
        criteriaTypes.put("creatorName", Boolean.TRUE);
        criteriaTypes.put(KeyWordsApp.PROJECT_PROG_LANG_FIELD_NAME, Boolean.TRUE);
        criteriaTypes.put(KeyWordsApp.PROJECT_APP_SERVER_FIELD_NAME, Boolean.TRUE);
        criteriaTypes.put(KeyWordsApp.PROJECT_DATABASE_FIELD_NAME, Boolean.TRUE);
        isAndProjectCriteriaTypes = criteriaTypes;
    }

    public boolean isAndProjectCriteria(String fieldName) {
        boolean result = Boolean.FALSE;
        if (isAndProjectCriteriaTypes.containsKey(fieldName)) {
            result = isAndProjectCriteriaTypes.get(fieldName);
        }
        return result;
    }
}
