package by.ilyin.manager.service;

import by.ilyin.manager.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<Project> findAll();

    List<Project> findAll(Specification<Project> specification);

    Page<Project> findAll(Specification<Project> specification, Pageable pageable);

    Optional<Project> findById(long id);

    Optional<Project> findById(Specification specification); //todo no solution found

    Optional<Project> findByIdAndIsDeletedEquals(long id, boolean isDeleted);

    void save(Project project);

    boolean update(Project project);

    boolean softDelete(Project project);

    boolean existsById(Long id);
    
}
