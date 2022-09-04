package by.ilyin.manager.service;

import by.ilyin.manager.controller.command.SessionRequestContent;

public interface PreparatoryTaskService {

    void findAllTasks(SessionRequestContent sessionRequestContent);

    void createTask(SessionRequestContent sessionRequestContent);

    void buildPageable(SessionRequestContent sessionRequestContent);

}
