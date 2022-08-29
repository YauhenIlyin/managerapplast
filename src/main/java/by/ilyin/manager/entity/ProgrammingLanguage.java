package by.ilyin.manager.entity;

import by.ilyin.manager.evidence.KeyWordsApp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = KeyWordsApp.PROGRAM_LANG_TABLE_NAME)
public class ProgrammingLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KeyWordsApp.PROGRAM_LANG_ID)
    private long id;

    @NotEmpty
    @Column(name = KeyWordsApp.PROGRAM_LANG_NAME)
    private String languageName;

    @OneToMany(mappedBy = "programmingLanguage")
    private List<Project> projects;

    public ProgrammingLanguage() {
    }

    public ProgrammingLanguage(String languageName, List<Project> projects) {
        this.languageName = languageName;
        this.projects = projects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "ProgrammingLanguage{" +
                "id=" + id +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}

