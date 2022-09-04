package by.ilyin.manager.service;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.exception.ManagerAppAuthException;

public interface PreparatoryProjectService {

    void deleteProject(SessionRequestContent sessionRequestContent);

    void updateProject(SessionRequestContent sessionRequestContent);

    void findProjectById(SessionRequestContent sessionRequestContent);

    void findAllProjects(SessionRequestContent sessionRequestContent);

    void createProject(SessionRequestContent sessionRequestContent);

    void buildFindAllCriteriaSpecification(SessionRequestContent sessionRequestContent);

    void buildPageable(SessionRequestContent sessionRequestContent);

}
