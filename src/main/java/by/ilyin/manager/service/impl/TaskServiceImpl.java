package by.ilyin.manager.service.impl;

import by.ilyin.manager.entity.Task;
import by.ilyin.manager.repository.TaskRepository;
import by.ilyin.manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Page findAllByProjectId(long id, Pageable pageable) {
        return taskRepository.findAllByProjectId(id, pageable);
    }

    @Override
    public Page findAllByProjectIdAndIsDeletedEquals(long id, boolean isDeleted, Pageable pageable) {
        return taskRepository.findAllByProjectIdAndIsDeletedEquals(id, isDeleted, pageable);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }
}
