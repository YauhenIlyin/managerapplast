package by.ilyin.manager.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static by.ilyin.manager.evidence.KeyWordsApp.*;
import static by.ilyin.manager.evidence.KeyWordsApp.TASKS_IS_DELETED;

@Entity
@Table(name = TASK_TABLE_NAME)
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TASKS_ID)
    private long id;

    @NotEmpty(message = "Task name must not be empty")
    @Size(min = 4, max = 30, message = "Task name must be between 4 and 30 characters")
    @Column(name = TASKS_NAME)
    private String name;

    @NotEmpty(message = "Task description must not be empty")
    @Size(min = 1, max = 400, message = "Task description must be between 1 and 400 characters")
    @Column(name = TASKS_DESCRIPTION)
    private String description;

    @Generated(GenerationTime.INSERT)
    @Column(name = TASKS_CREATION_DATE_TIME)
    private LocalDateTime creationDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = TASKS_FOREIGN_CREATOR_ID, referencedColumnName = USERS_ID)
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TASKS_FOREIGN_PROJECT_ID, referencedColumnName = PROJECTS_ID)
    private Project project;

    @Column(name = TASKS_IS_DELETED)
    private boolean isDeleted;

    public Task() {
    }

    public Task(String name,
                String description,
                LocalDateTime creationDateTime,
                User creator,
                Project project,
                boolean isDeleted) {
        this.name = name;
        this.description = description;
        this.creationDateTime = creationDateTime;
        this.creator = creator;
        this.project = project;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
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
        Task task = (Task) o;
        if (id != task.id) {
            return Boolean.FALSE;
        }
        if (isDeleted != task.isDeleted) {
            return Boolean.FALSE;
        }
        if (name != null && !name.equals(task.name)) {
            return Boolean.FALSE;
        }
        if (description != null && !description.equals(task.description)) {
            return Boolean.FALSE;
        }
        if (creationDateTime != null && !creationDateTime.equals(task.creationDateTime)) {
            return Boolean.FALSE;
        }
        if (creator != null && !creator.equals(task.creator)) {
            return Boolean.FALSE;
        }
        return project != null && project.equals(task.project);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        if (name != null) {
            result = 3 * result + name.hashCode();
        }
        if (description != null) {
            result = 5 * result + description.hashCode();
        }
        if (creationDateTime != null) {
            result = 7 * result + creationDateTime.hashCode();
        }
        if (creator != null) {
            result = 13 * result + creator.hashCode();
        }
        if (project != null) {
            result = 17 * result + project.hashCode();
        }
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", creationDateTime=").append(creationDateTime);
        sb.append(", creator=").append(creator);
        sb.append(", project=").append(project);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append('}');
        return sb.toString();
    }
}
