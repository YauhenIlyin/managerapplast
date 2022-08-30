package by.ilyin.manager.service;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.util.validator.RequestValidator;

public interface PreparatoryService {

    void initializePageable(SessionRequestContent sessionRequestContent, RequestValidator requestValidator);

}
