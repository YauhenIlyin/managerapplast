package by.ilyin.manager.evidence;

public class KeyWordsSessionRequest {

    public static final String DEFAULT_EMPTY_VALUE = "none";

    public static final String FILTER_OPERATION_MORE = "more";
    public static final String FILTER_OPERATION_LESS = "less";
    public static final String FILTER_OPERATION_EQUALS = "equal";
    public static final String FILTER_OPERATION_NUMBER_EQUALS = "num_equal";

    public static final String FILTER_PROJECT_PROG_LANG_FILTER_NAME = "programmingLanguage";
    public static final String FILTER_PROJECT_APP_SERVER_FILTER_NAME = "applicationServer";
    public static final String FILTER_PROJECT_DATABASE_FILTER_NAME = "database";
    public static final String FILTER_PROJECT_EMPLOYEE_OPERATION_FILTER_NAME = "employeeOperation";
    public static final String FILTER_PROJECT_EMPLOYEE_COUNT_FILTER_NAME = "employeeCount";

    public static final String RESULT = "result";

    public static final String PROJECTS = "projects";
    public static final String PROJECT = "project";
    public static final String PROJECT_ID = "projectId";

    public static final String TASK_ID = "taskId";
    public static final String TASKS = "tasks";
    public static final String TASK = "task";

    public static final String SORT_TYPE = "sortType";
    public static final String SORT_SORT_FIELD = "sortField";
    public static final String ASC_SORT_VALUE = "asc";
    public static final String DESC_SORT_VALUE = "desc";

    public static final String PAGE_PAGE = "page";
    public static final String PAGE_TOTAL_COUNT = "pageCount";
    public static final String PAGE_CURRENT_NUMBER = "pageNumber";
    public static final String PAGE_ITEMS_COUNT_ON_ONE = "pageItemsCount";
    public static final long PAGE_DEFAULT_COUNT_ITEMS_ON_ONE = 3;
    public static final long PAGE_DEFAULT_CURRENT_NUMBER = 0;

    private KeyWordsSessionRequest() {
    }

}
