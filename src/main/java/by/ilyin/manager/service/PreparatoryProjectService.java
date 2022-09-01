package by.ilyin.manager.service;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.exception.ManagerAppAuthException;

public interface PreparatoryProjectService {

    void findAllProjects(SessionRequestContent sessionRequestContent);

    void buildFindAllCriteriaSpecification(SessionRequestContent sessionRequestContent);

    void buildPageable(SessionRequestContent sessionRequestContent);

}
