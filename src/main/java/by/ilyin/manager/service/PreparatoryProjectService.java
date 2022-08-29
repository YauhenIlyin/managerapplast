package by.ilyin.manager.service;

import by.ilyin.manager.controller.command.SessionRequestContent;

public interface PreparatoryProjectService {

    void buildFindAllCriteriaSpecification(SessionRequestContent sessionRequestContent);

    void buildSoftDeleteCriteriaSpecification(SessionRequestContent sessionRequestContent);

    void buildFindByIdCriteriaSpecification(SessionRequestContent sessionRequestContent, Long id);

}
