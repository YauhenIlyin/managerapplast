package by.ilyin.manager.entity;

import by.ilyin.manager.evidence.KeyWordsApp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = KeyWordsApp.PROGRAM_LANG_TABLE_NAME)
public class ProgrammingLanguage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KeyWordsApp.PROGRAM_LANG_ID)
    private long id;

    @NotEmpty
    @Column(name = KeyWordsApp.PROGRAM_LANG_NAME)
    private String languageName;

    @OneToMany(mappedBy = KeyWordsApp.PROJECT_PROG_LANG_FIELD_NAME)
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
    public boolean equals(Object o) {
        if (this == o) {
            return Boolean.TRUE;
        }
        if (o == null || getClass() != o.getClass()) {
            return Boolean.FALSE;
        }
        ProgrammingLanguage that = (ProgrammingLanguage) o;
        if (id != that.id) {
            return Boolean.FALSE;
        }
        if (languageName != null && !languageName.equals(that.languageName)) {
            return Boolean.FALSE;
        }
        return projects != null && projects.equals(that.projects);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        if (languageName != null) {
            result = 31 * result + languageName.hashCode();
        }
        if (projects != null) {
            result = 31 * result + projects.hashCode();
        }
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProgrammingLanguage{");
        sb.append("id=").append(id);
        sb.append(", languageName='").append(languageName).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}

