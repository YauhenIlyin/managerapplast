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
public class Project {

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

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

    //(fetch = FetchType.EAGER)
//    @ManyToMany
//    @JoinTable(name = PROJECTS_TO_PROGRAM_LANG_MANY_MANY_TABLE_NAME,
//            joinColumns = {@JoinColumn(name = PROJECTS_TO_PROGRAM_LANG_MANY_MANY_PROJECT_ID)},
//            inverseJoinColumns = {@JoinColumn(name = PROJECTS_TO_PROGRAM_LANG_MANY_MANY_PROGRAM_LANG_ID)})
    @ManyToOne()
    @JoinColumn(name = PROJECTS_FOREIGN_PROGRAMMING_LANGUAGE_ID, referencedColumnName = PROGRAM_LANG_ID)
    private ProgrammingLanguage programmingLanguage;

    //(fetch = FetchType.EAGER)
//    @ManyToMany
//            @JoinTable(name = PROJECTS_TO_APP_SERVERS_MANY_MANY_TABLE_NAME,
//            joinColumns = {@JoinColumn(name = PROJECTS_TO_APP_SERVERS_MANY_MANY_PROJECT_ID)},
//            inverseJoinColumns = {@JoinColumn(name = PROJECTS_TO_APP_SERVERS_MANY_MANY_APP_SERVER_ID)})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROJECTS_FOREIGN_APPLICATION_SERVER_ID, referencedColumnName = APP_SERVERS_ID)
    private ApplicationServer applicationServer;

    //(fetch = FetchType.EAGER)
//    @ManyToMany
//    @JoinTable(name = PROJECTS_TO_DATABASES_MANY_MANY_TABLE_NAME,
//            joinColumns = {@JoinColumn(name = PROJECTS_TO_DATABASES_MANY_MANY_PROJECT_ID)},
//            inverseJoinColumns = {@JoinColumn(name = PROJECTS_TO_DATABASES_MANY_MANY_DATABASE_ID)})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PROJECTS_FOREIGN_DATABASE_ID, referencedColumnName = DATABASES_ID)
    private Database database;

    @Column(name = PROJECTS_IS_DELETED)
    private boolean isDeleted;

    public Project() {
    }

    public Project(String projectName, String description, Integer employeeCount, LocalDateTime creationDateTime, User creator, List<Task> tasks, ProgrammingLanguage programmingLanguage, ApplicationServer applicationServer, Database database, boolean isDeleted) {
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
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", employeeCount=" + employeeCount +
                ", creationDateTime=" + creationDateTime +
                ", creatorName=" + creator +
                ", tasks=" + tasks +
                ", programmingLanguage=" + programmingLanguage +
                ", applicationServer=" + applicationServer +
                ", database=" + database +
                "}\n";
    }
}
