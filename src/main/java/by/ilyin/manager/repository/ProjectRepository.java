package by.ilyin.manager.repository;

import by.ilyin.manager.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    List<Project> findAll(Specification specification);

    Page findAll(Specification specification, Pageable pageable);

    Optional<Project> findByIdAndIsDeletedEquals(long id, boolean isDeleted);

    Optional<Project> findById(long id);

    Optional<Project> findById(Specification specification);

    @Override
    boolean existsById(Long aLong);
}
