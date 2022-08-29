package by.ilyin.manager.entity;

import by.ilyin.manager.evidence.KeyWordsApp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = KeyWordsApp.DATABASE_TABLE_NAME)
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KeyWordsApp.DATABASES_ID)
    private long id;

    @NotEmpty
    @Column(name = KeyWordsApp.DATABASES_NAME)
    private String databaseName;

    @OneToMany(mappedBy = "database", fetch = FetchType.LAZY)
    private List<Project> projects;

    public Database() {
    }

    public Database(String databaseName, List<Project> projects) {
        this.databaseName = databaseName;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Database{" +
                "id=" + id +
                ", databaseName='" + databaseName + '\'' +
                '}';
    }
}
