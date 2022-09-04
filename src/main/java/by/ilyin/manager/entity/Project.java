package by.ilyin.manager.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

import static by.ilyin.manager.evidence.KeyWordsApp.*;

@Entity
@Table(name = PROJECT_TABLE_NAME)
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PROJECTS_ID)
    private Long id;

    @NotEmpty(message = "Project name must not be empty")
    @Size(min = 1, max = 30, message = "Project name length no more than 30 characters")
    @Column(name = PROJECTS_NAME)
    private String projectName;

    @NotEmpty(message = "Project description must not be empty")
    @Size(min = 1, max = 400, message = "Project description length no more than 400 characters")
    @Column(name = PROJECTS_DESCRIPTION)
    private String description;

    @NotNull(message = "Employee count must not be empty")
    @Min(value = 1, message = "The number of employees cannot be less than 1")
    @Max(value = 1000000, message = "Too big number of employees")
    @Column(name = PROJECTS_EMPLOYEE_COUNT)
    private Integer employeeCount;

    @Generated(GenerationTime.INSERT)
    @Column(name = PROJECTS_CREATION_DATE_TIME)
    private LocalDateTime creationDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = PROJECTS_FOREIGN_USER_CREATOR_ID, referencedColumnName = USERS_ID)
    private User creator;

    @OneToMany(mappedBy = TASK_PROJECT_FIELD_NAME, fetch = FetchType.LAZY)
    private List<Task> tasks;

    @ManyToOne()
    @JoinColumn(name = PROJECTS_FOREIGN_PROGRAMMING_LANGUAGE_ID, referencedColumnName = PROGRAM_LANG_ID)
    private ProgrammingLanguage programmingLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROJECTS_FOREIGN_APPLICATION_SERVER_ID, referencedColumnName = APP_SERVERS_ID)
    private ApplicationServer applicationServer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROJECTS_FOREIGN_DATABASE_ID, referencedColumnName = DATABASES_ID)
    private Database database;

    @Column(name = PROJECTS_IS_DELETED)
    private boolean isDeleted;

    public Project() {
    }

    public Project(String projectName,
                   String description,
                   Integer employeeCount,
                   LocalDateTime creationDateTime,
                   User creator,
                   List<Task> tasks,
                   ProgrammingLanguage programmingLanguage,
                   ApplicationServer applicationServer,
                   Database database,
                   boolean isDeleted) {
        this.projectName = projectName;
        this.description = description;
        this.employeeCount = employeeCount;
        this.creationDateTime = creationDateTime;
        this.creator = creator;
        this.tasks = tasks;
        this.programmingLanguage = programmingLanguage;
        this.applicationServer = applicationServer;
        this.database = database;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public ApplicationServer getApplicationServer() {
        return applicationServer;
    }

    public void setApplicationServer(ApplicationServer applicationServer) {
        this.applicationServer = applicationServer;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return Boolean.TRUE;
        }
        if (o == null || getClass() != o.getClass()) {
            return Boolean.FALSE;
        }
        Project project = (Project) o;
        if (isDeleted != project.isDeleted) {
            return Boolean.FALSE;
        }
        if (!id.equals(project.id)) {
            return Boolean.FALSE;
        }
        if (!projectName.equals(project.projectName)) {
            return Boolean.FALSE;
        }
        if (!description.equals(project.description)) {
            return Boolean.FALSE;
        }
        if (!employeeCount.equals(project.employeeCount)) {
            return Boolean.FALSE;
        }
        if (!creationDateTime.equals(project.creationDateTime)) {
            return Boolean.FALSE;
        }
        if (!creator.equals(project.creator)) {
            return Boolean.FALSE;
        }
        if (!tasks.equals(project.tasks)) {
            return Boolean.FALSE;
        }
        if (!programmingLanguage.equals(project.programmingLanguage)) {
            return Boolean.FALSE;
        }
        if (!applicationServer.equals(project.applicationServer)) {
            return Boolean.FALSE;
        }
        return database != null && database.equals(project.database);
    }

    @Override
    public int hashCode() {
        int result = 0;
        if (id != null) {
            result = result + id.hashCode();
        }
        if (projectName != null) {
            result = 3 * result + projectName.hashCode();
        }
        if (description != null) {
            result = 5 * result + description.hashCode();
        }
        if (employeeCount != null) {
            result = 7 * result + employeeCount.hashCode();
        }
        if (creationDateTime != null) {
            result = 3 * result + creationDateTime.hashCode();
        }
        if (creator != null) {
            result = 5 * result + creator.hashCode();
        }
        if (tasks != null) {
            result = 7 * result + tasks.hashCode();
        }
        if (programmingLanguage != null) {
            result = 13 * result + programmingLanguage.hashCode();
        }
        if (applicationServer != null) {
            result = 13 * result + applicationServer.hashCode();
        }
        if (database != null) {
            result = 17 * result + database.hashCode();
        }
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Project{");
        sb.append("id=").append(id);
        sb.append(", projectName='").append(projectName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", employeeCount=").append(employeeCount);
        sb.append(", creationDateTime=").append(creationDateTime);
        sb.append(", creator=").append(creator);
        sb.append(", tasks=").append(tasks);
        sb.append(", programmingLanguage=").append(programmingLanguage);
        sb.append(", applicationServer=").append(applicationServer);
        sb.append(", database=").append(database);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append('}');
        return sb.toString();
    }
}
