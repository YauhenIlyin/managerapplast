package by.ilyin.manager.service.impl;

import by.ilyin.manager.entity.Project;
import by.ilyin.manager.repository.ProjectRepository;
import by.ilyin.manager.security.AuthDataManager;
import by.ilyin.manager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final AuthDataManager authDataManager;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, AuthDataManager authDataManager) {
        this.projectRepository = projectRepository;
        this.authDataManager = authDataManager;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    @Override
    public List<Project> findAll(Specification<Project> specification) {
        return projectRepository.findAll(specification);
    }

    @Override
    public Page<Project> findAll(Specification<Project> specification, Pageable pageable) {
        return projectRepository.findAll(specification, pageable);
    }


    @Override
    public Optional<Project> findById(long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Optional<Project> findById(Specification specification) {
        return projectRepository.findById(specification);
    }

    @Override
    public Optional<Project> findByIdAndIsDeletedEquals(long id, boolean isDeleted) {
        return projectRepository.findByIdAndIsDeletedEquals(id, isDeleted);
    }

    @Transactional
    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Transactional
    @Override
    public boolean update(Project project) {
        boolean result = false;
        if (project != null) {
            Optional<Project> optionalProject = findById(project.getId());
            if (optionalProject.isPresent()) {
                Project mainProject = optionalProject.get();
                mainProject.setProjectName(project.getProjectName());
                mainProject.setEmployeeCount(project.getEmployeeCount());
                mainProject.setDescription(project.getDescription());
                mainProject.setApplicationServer(project.getApplicationServer());
                mainProject.setDatabase(project.getDatabase());
                mainProject.setProgrammingLanguage(project.getProgrammingLanguage());
                project = mainProject;
                projectRepository.save(mainProject);
                result = true;
            }
        }
        return result;
    }

    @Transactional
    @Override
    public boolean softDelete(Project project) {
        boolean result = false;
        if (project != null) {
            Optional<Project> optionalProject = findById(project.getId());
            if (optionalProject.isPresent()) {
                Project mainProject = optionalProject.get();
                mainProject.setIsDeleted(true);
                projectRepository.save(mainProject);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }
}
