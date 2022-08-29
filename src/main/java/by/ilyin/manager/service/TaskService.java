package by.ilyin.manager.service;

import by.ilyin.manager.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    Page findAllByProjectId(long id, Pageable pageable);

    Page findAllByProjectIdAndIsDeletedEquals(long id, boolean isDeleted, Pageable pageable);

    void save(Task task);

}
