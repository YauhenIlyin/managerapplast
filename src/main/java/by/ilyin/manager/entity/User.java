package by.ilyin.manager.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


import static by.ilyin.manager.evidence.KeyWordsApp.*;

@Entity
@Table(name = USER_TABLE_NAME)
public class User {

    @Id
    @Column(name = USERS_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name must not be empty")
    @Size(min = 1, max = 36, message = "First name length no more than 36 characters")
    @Column(name = USERS_FIRST_NAME)
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 1, max = 36, message = "Last name length no more than 36 characters")
    @Column(name = USERS_LAST_NAME)
    private String lastName;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email entered incorrectly")
    @Column(name = USERS_EMAIL)
    private String email;

    @Generated(GenerationTime.INSERT)
    @Column(name = USERS_REGISTRATION_DATE_TIME)
    private LocalDateTime registrationDateTime;

    @Column(name = USERS_ROLE)
    private String role;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    @Column(name = USERS_USERNAME)
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 4, max = 110)
    @Column(name = USERS_PASSWORD)
    private String password;

    @Transient
    private String repeatPassword;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Project> createdProjects;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Task> createdTasks;

    @Column(name = USERS_IS_DELETED)
    private boolean isDeleted;

    public User() {
    }

    public User(String firstName, String lastName, String email, LocalDateTime registrationDateTime, String role, String username, String password, String repeatPassword, List<Project> createdProjects, List<Task> createdTasks, boolean isDeleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDateTime = registrationDateTime;
        this.role = role;
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.createdProjects = createdProjects;
        this.createdTasks = createdTasks;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public List<Project> getCreatedProjects() {
        return createdProjects;
    }

    public void setCreatedProjects(List<Project> createdProjects) {
        this.createdProjects = createdProjects;
    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(List<Task> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
