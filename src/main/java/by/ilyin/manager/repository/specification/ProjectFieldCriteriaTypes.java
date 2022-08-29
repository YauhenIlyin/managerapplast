package by.ilyin.manager.repository.specification;

import java.util.HashMap;

public class ProjectFieldCriteriaTypes {

    static {
        HashMap<String, Boolean> criteriaTypes = new HashMap();
        criteriaTypes.put("id", true);
        criteriaTypes.put("employeeCount", true);
        criteriaTypes.put("creatorName", true);
        criteriaTypes.put("programmingLanguage", true);
        criteriaTypes.put("applicationServer", true);
        criteriaTypes.put("database", true);
        isAndProjectCriteriaTypes = criteriaTypes;
    }

    private static final HashMap<String,Boolean> isAndProjectCriteriaTypes;

    private ProjectFieldCriteriaTypes() {
    }

    public static boolean isAndProjectCriteria(String fieldName) {
        boolean result = false;
        if (isAndProjectCriteriaTypes.containsKey(fieldName)) {
            result = isAndProjectCriteriaTypes.get(fieldName);
        }
        return result;
    }
}
