package by.ilyin.manager.service;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.repository.specification.SpecificationBuilder;
import by.ilyin.manager.util.validator.RequestValidator;

import java.util.List;

public interface PageableFilterService {

    PageableFilterService initializePageable(SessionRequestContent sessionRequestContent, RequestValidator requestValidator);

    PageableFilterService addSoftDeleteCriteriaSpecification(SpecificationBuilder builder, String currentUserRole);

    PageableFilterService addCriteria(SpecificationBuilder builder, boolean isCorrectConditions, String fieldName, String operation, Object value);

    PageableFilterService addCriteria(SpecificationBuilder builder, boolean isCorrectConditions, String fieldName, String operation, String index, List list);

}
