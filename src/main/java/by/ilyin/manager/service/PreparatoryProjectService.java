package by.ilyin.manager.service;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.exception.ManagerAppAuthException;

public interface PreparatoryProjectService {

    void buildFindAllCriteriaSpecification(SessionRequestContent sessionRequestContent) throws ManagerAppAuthException

    void buildPageable(SessionRequestContent sessionRequestContent);

}
