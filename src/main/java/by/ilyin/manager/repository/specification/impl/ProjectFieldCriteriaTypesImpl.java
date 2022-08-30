package by.ilyin.manager.repository.specification.impl;

import by.ilyin.manager.repository.specification.FieldCriteriaTypes;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ProjectFieldCriteriaTypesImpl implements FieldCriteriaTypes {

    private final HashMap<String,Boolean> isAndProjectCriteriaTypes;

    private ProjectFieldCriteriaTypesImpl() {
        HashMap<String, Boolean> criteriaTypes = new HashMap();
        criteriaTypes.put("id", true);
        criteriaTypes.put("employeeCount", true);
        criteriaTypes.put("creatorName", true);
        criteriaTypes.put("programmingLanguage", true);
        criteriaTypes.put("applicationServer", true);
        criteriaTypes.put("database", true);
        isAndProjectCriteriaTypes = criteriaTypes;
    }

    public boolean isAndProjectCriteria(String fieldName) {
        boolean result = false;
        if (isAndProjectCriteriaTypes.containsKey(fieldName)) {
            result = isAndProjectCriteriaTypes.get(fieldName);
        }
        return result;
    }
}
