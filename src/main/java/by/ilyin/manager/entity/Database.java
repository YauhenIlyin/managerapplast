package by.ilyin.manager.entity;

import by.ilyin.manager.evidence.KeyWordsApp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = KeyWordsApp.DATABASE_TABLE_NAME)
public class Database extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KeyWordsApp.DATABASES_ID)
    private long id;

    @NotEmpty
    @Column(name = KeyWordsApp.DATABASES_NAME)
    private String databaseName;

    @OneToMany(mappedBy = KeyWordsApp.PROJECT_DATABASE_FIELD_NAME, fetch = FetchType.LAZY)
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
    public boolean equals(Object o) {
        if (this == o) {
            return Boolean.TRUE;
        }
        if (o == null || getClass() != o.getClass()) {
            return Boolean.FALSE;
        }
        Database database = (Database) o;
        if (id != database.id) {
            return Boolean.FALSE;
        }
        if (databaseName != null && !databaseName.equals(database.databaseName)) {
            return Boolean.FALSE;
        }
        return projects != null && projects.equals(database.projects);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        if (databaseName != null) {
            result = 31 * result + databaseName.hashCode();
        }
        if (projects != null) {
            result = 31 * result + projects.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Database{");
        sb.append("id=").append(id);
        sb.append(", databaseName='").append(databaseName).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
