package by.ilyin.manager.entity;

import by.ilyin.manager.evidence.KeyWordsApp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = KeyWordsApp.APP_SERVER_TABLE_NAME)
public class ApplicationServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KeyWordsApp.APP_SERVERS_ID)
    private long id;

    @NotEmpty
    @Column(name = KeyWordsApp.APP_SERVERS_NAME)
    private String serverName;

    @OneToMany(mappedBy = "applicationServer", fetch = FetchType.LAZY)
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
    public String toString() {
        return "ApplicationServer{" +
                "id=" + id +
                ", serverName='" + serverName + '\'' +
                '}';
    }
}
