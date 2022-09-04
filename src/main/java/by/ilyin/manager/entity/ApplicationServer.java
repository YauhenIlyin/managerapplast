package by.ilyin.manager.entity;

import by.ilyin.manager.evidence.KeyWordsApp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = KeyWordsApp.APP_SERVER_TABLE_NAME)
public class ApplicationServer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KeyWordsApp.APP_SERVERS_ID)
    private long id;

    @NotEmpty
    @Column(name = KeyWordsApp.APP_SERVERS_NAME)
    private String serverName;

    @OneToMany(mappedBy = KeyWordsApp.PROJECT_APP_SERVER_FIELD_NAME, fetch = FetchType.LAZY)
    private List<Project> projects;

    public ApplicationServer() {
    }

    public ApplicationServer(String serverName, List<Project> projects) {
        this.serverName = serverName;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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
        ApplicationServer that = (ApplicationServer) o;
        if (id != that.id) {
            return Boolean.FALSE;
        }
        if (serverName != null && !serverName.equals(that.serverName)) {
            return Boolean.FALSE;
        }
        return projects != null && projects.equals(that.projects);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        if (serverName != null) {
            result = 31 * result + serverName.hashCode();
        }
        if (projects != null) {
            result = 31 * result + projects.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApplicationServer{");
        sb.append("id=").append(id);
        sb.append(", serverName='").append(serverName).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
