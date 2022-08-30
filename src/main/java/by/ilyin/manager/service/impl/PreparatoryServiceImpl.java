package by.ilyin.manager.service.impl;

import by.ilyin.manager.controller.command.SessionRequestContent;
import by.ilyin.manager.evidence.KeyWordsRequest;
import by.ilyin.manager.service.PreparatoryService;
import by.ilyin.manager.util.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PreparatoryServiceImpl implements PreparatoryService {

    @Autowired
    public PreparatoryServiceImpl() {
    }

    @Override
    public void initializePageable(SessionRequestContent sessionRequestContent, RequestValidator requestValidator) {
        Pageable pageable;
        HashMap<String, String> requestParam = sessionRequestContent.getRequestParameters();
        String currentPageNumberStr = requestParam.get(KeyWordsRequest.PAGE_CURRENT_NUMBER);
        String itemsOnPageStr = requestParam.get(KeyWordsRequest.PAGE_ITEMS_COUNT_ON_ONE);
        String sortTypeStr = requestParam.get(KeyWordsRequest.SORT_TYPE);
        String sortFieldStr = requestParam.get(KeyWordsRequest.SORT_SORT_FIELD);
        long currentPageNumberValue;
        long itemsOnPageValue;
        currentPageNumberValue = parseNumberRequestParam(currentPageNumberStr, KeyWordsRequest.PAGE_DEFAULT_CURRENT_NUMBER) - 1;
        itemsOnPageValue = parseNumberRequestParam(itemsOnPageStr, KeyWordsRequest.PAGE_DEFAULT_COUNT_ITEMS_ON_ONE);
        boolean isCurrectfulSort = requestValidator.isValidFieldName(sortFieldStr);
        Sort.Direction currentSortType;
        if (isCurrectfulSort) {
            if (sortTypeStr != null && sortTypeStr.equals(KeyWordsRequest.DESC_SORT_VALUE)) {
                currentSortType = Sort.Direction.DESC;
            } else {
                currentSortType = Sort.Direction.ASC;
            }
            pageable = PageRequest.of((int) currentPageNumberValue, (int) itemsOnPageValue, Sort.by(currentSortType, sortFieldStr));
        } else {
            pageable = PageRequest.of((int) currentPageNumberValue, (int) itemsOnPageValue);
        }
        sessionRequestContent.setPageable(pageable);
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
