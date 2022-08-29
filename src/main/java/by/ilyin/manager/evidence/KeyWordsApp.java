package by.ilyin.manager.evidence;

public class KeyWordsApp {

    private KeyWordsApp() {
    }

    //todo refactoring names
    public static final String USER_TABLE_NAME = "users";
    public static final String USERS_ID = "id";
    public static final String USERS_FIRST_NAME = "first_name";
    public static final String USERS_LAST_NAME = "last_name";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_REGISTRATION_DATE_TIME = "registration_date_time";
    public static final String USERS_ROLE = "role";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_IS_DELETED = "is_deleted";

    public static final String PROJECT_TABLE_NAME = "projects";
    public static final String PROJECTS_ID = "id";
    public static final String PROJECTS_NAME = "project_name";
    public static final String PROJECTS_DESCRIPTION = "project_description";
    public static final String PROJECTS_EMPLOYEE_COUNT = "employee_count";
    public static final String PROJECTS_CREATION_DATE_TIME = "creation_date_time";
    public static final String PROJECTS_FOREIGN_PROGRAMMING_LANGUAGE_ID = "programming_language_id";
    public static final String PROJECTS_FOREIGN_DATABASE_ID = "database_id";
    public static final String PROJECTS_FOREIGN_APPLICATION_SERVER_ID = "application_server_id";
    public static final String PROJECTS_FOREIGN_USER_CREATOR_ID = "user_id";
    public static final String PROJECTS_IS_DELETED = "is_deleted";

    public static final String PROGRAM_LANG_TABLE_NAME = "programming_languages";
    public static final String PROGRAM_LANG_ID = "id";
    public static final String PROGRAM_LANG_NAME = "language_name";

    public static final String APP_SERVER_TABLE_NAME = "application_servers";
    public static final String APP_SERVERS_ID = "id";
    public static final String APP_SERVERS_NAME = "server_name";

    public static final String DATABASE_TABLE_NAME = "databases";
    public static final String DATABASES_ID = "id";
    public static final String DATABASES_NAME = "database_name";

    public static final String TASK_TABLE_NAME = "tasks";
    public static final String TASKS_ID = "id";
    public static final String TASKS_NAME = "task_name";
    public static final String TASKS_DESCRIPTION = "task_description";
    public static final String TASKS_CREATION_DATE_TIME = "creation_date_time";
    public static final String TASKS_FOREIGN_CREATOR_ID = "user_id";
    public static final String TASKS_FOREIGN_PROJECT_ID = "project_id";
    public static final String TASKS_IS_DELETED = "is_deleted";

    public static final String PROJECT_ID_FIELD_NAME = "id";
    public static final String PROJECT_NAME_FIELD_NAME = "projectName";
    public static final String PROJECT_DESCRIPTION_FIELD_NAME = "description";
    public static final String PROJECT_EMPLOYEE_COUNT_FIELD_NAME = "employeeCount";
    public static final String PROJECT_CREATION_DATE_TIME_FIELD_NAME = "creationDateTime";
    public static final String PROJECT_CREATOR_FIELD_NAME = "creator";
    public static final String PROJECT_TASKS_FIELD_NAME = "tasks";
    public static final String PROJECT_PROG_LANG_FIELD_NAME = "programmingLanguage";
    public static final String PROJECT_APP_SERVER_FIELD_NAME = "applicationServer";
    public static final String PROJECT_DATABASE_FIELD_NAME = "database";
    public static final String PROJECTS_IS_DELETED_FIELD_NAME = "isDeleted";

    public static final String TASK_ID_FIELD_NAME = "id";
    public static final String TASK_NAME_FIELD_NAME = "name";
    public static final String TASK_DESCRIPTION_FIELD_NAME = "description";
    public static final String TASK_CREATION_DATE_TIME_FIELD_NAME = "creationDateTime";
    public static final String TASK_CREATOR_FIELD_NAME = "creator";
    public static final String TASK_PROJECT_FIELD_NAME = "project";
    public static final String TASK_IS_DELETED_FIELD_NAME = "isDeleted";

    public static final String ROLE_ADMIN_VALUE = "ROLE_ADMIN";
    public static final String ROLE_USER_VALUE = "ROLE_USER";
}
