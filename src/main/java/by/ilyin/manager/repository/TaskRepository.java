package by.ilyin.manager.repository;

import by.ilyin.manager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    Page findAllByProjectId(long id, Pageable pageable);

    Page findAllByProjectIdAndIsDeletedEquals(long id, boolean isDeleted, Pageable pageable);

}
