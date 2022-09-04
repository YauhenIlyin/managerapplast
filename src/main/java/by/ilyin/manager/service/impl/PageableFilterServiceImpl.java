package by.ilyin.manager.service.impl;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.repository.specification.SearchCriteria;
import by.ilyin.manager.repository.specification.SpecificationBuilder;
import by.ilyin.manager.security.AuthDataManager;
import by.ilyin.manager.service.PageableFilterService;
import by.ilyin.manager.util.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PageableFilterServiceImpl implements PageableFilterService {

    private static final String IS_DELETED_FIELD_NAME = "isDeleted";
    private final AuthDataManager authDataManager;

    @Autowired
    public PageableFilterServiceImpl(AuthDataManager authDataManager) {
        this.authDataManager = authDataManager;
    }

    @Override
    public PageableFilterService initializePageable(SessionRequestContent sessionRequestContent, RequestValidator requestValidator) {
        Pageable pageable;
        HashMap<String, String> requestParam = sessionRequestContent.getRequestParameters();
        String currentPageNumberStr = requestParam.get(KeyWordsSessionRequest.PAGE_CURRENT_NUMBER);
        String itemsOnPageStr = requestParam.get(KeyWordsSessionRequest.PAGE_ITEMS_COUNT_ON_ONE);
        String sortTypeStr = requestParam.get(KeyWordsSessionRequest.SORT_TYPE);
        String sortFieldStr = requestParam.get(KeyWordsSessionRequest.SORT_SORT_FIELD);
        long currentPageNumberValue;
        long itemsOnPageValue;
        currentPageNumberValue = parseNumberRequestParam(currentPageNumberStr, KeyWordsSessionRequest.PAGE_DEFAULT_CURRENT_NUMBER) - 1;
        itemsOnPageValue = parseNumberRequestParam(itemsOnPageStr, KeyWordsSessionRequest.PAGE_DEFAULT_COUNT_ITEMS_ON_ONE);
        boolean isCurrectfulSort = requestValidator.isValidFieldName(sortFieldStr);
        Sort.Direction currentSortType;
        if (isCurrectfulSort) {
            if (sortTypeStr != null && sortTypeStr.equals(KeyWordsSessionRequest.DESC_SORT_VALUE)) {
                currentSortType = Sort.Direction.DESC;
            } else {
                currentSortType = Sort.Direction.ASC;
            }
            pageable = PageRequest.of((int) currentPageNumberValue, (int) itemsOnPageValue, Sort.by(currentSortType, sortFieldStr));
        } else {
            pageable = PageRequest.of((int) currentPageNumberValue, (int) itemsOnPageValue);
        }
        sessionRequestContent.setPageable(pageable);
        return this;
    }

    @Override
    public PageableFilterService addSoftDeleteCriteriaSpecification(SpecificationBuilder builder, String currentUserRole) {
        if (builder == null) {
            builder = new SpecificationBuilder();
        }
        try {
            currentUserRole = this.authDataManager.getCurrentUserRole();
        } catch (ManagerAppAuthException e) {
            //todo log
        }
        boolean isCorrectConditions = currentUserRole != null && !currentUserRole.equals(KeyWordsApp.ROLE_ADMIN_VALUE);
        return addCriteria(builder, isCorrectConditions, IS_DELETED_FIELD_NAME,
                KeyWordsSessionRequest.FILTER_OPERATION_EQUALS, Boolean.FALSE);
    }

    @Override
    public PageableFilterService addCriteria(SpecificationBuilder builder, boolean isCorrectConditions, String fieldName,
                                             String operation, Object value) {
        if (isCorrectConditions) {
            if (builder == null) {
                builder = new SpecificationBuilder();
            }
            addCriteria(builder, fieldName, operation, value);
        }
        return this;
    }

    @Override
    public PageableFilterService addCriteria(SpecificationBuilder builder, boolean isCorrectConditions, String fieldName,
                                             String operation, String index, List list) {
        if (isCorrectConditions) {
            if (builder == null) {
                builder = new SpecificationBuilder();
            }
            Object value = list.get(Integer.parseInt(index));
            addCriteria(builder, fieldName, operation, value);
        }
        return this;
    }

    private PageableFilterService addCriteria(SpecificationBuilder builder, String fieldName, String operation, Object value) {
        SearchCriteria searchCriteria = new SearchCriteria(fieldName, operation, value);
        builder.with(searchCriteria);
        return this;
    }

    private long parseNumberRequestParam(String value, long defaultValue) {
        long currentValue;
        if (value != null) {
            try {
                currentValue = Long.parseLong(value);
            } catch (NumberFormatException e) {
                currentValue = defaultValue;
            }
        } else {
            currentValue = defaultValue;
        }
        return currentValue < 1 ? 1 : currentValue;
    }
}
