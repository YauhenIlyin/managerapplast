package by.ilyin.manager.controller.command;

import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.evidence.KeyWordsRequest;
import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.repository.specification.ProjectSpecificationBuilder;
import by.ilyin.manager.security.AuthDataManager;
import by.ilyin.manager.util.AppBaseDataCore;
import by.ilyin.manager.util.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionRequestContent {

    private final AppBaseDataCore appBaseDataCore;
    private final AuthDataManager authDataManager;

    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> requestAttributes;
    private Pageable pageable;
    private boolean isSuccessfulResult = false;
    private boolean isFiltering = false;
    private String currentRole;
    private ProjectSpecificationBuilder projectSpecificationBuilder;

    @Autowired
    public SessionRequestContent(AppBaseDataCore appBaseDataCore, AuthDataManager authDataManager,
                                 ProjectSpecificationBuilder projectSpecificationBuilder) {
        this.appBaseDataCore = appBaseDataCore;
        this.authDataManager = authDataManager;
        this.projectSpecificationBuilder = projectSpecificationBuilder;
        this.requestParameters = new HashMap<>();
        this.requestAttributes = new HashMap<>();
        this.pageable = PageRequest.of((int) KeyWordsRequest.PAGE_DEFAULT_CURRENT_NUMBER, (int) KeyWordsRequest.PAGE_DEFAULT_COUNT_ITEMS_ON_ONE);
    }

    public AppBaseDataCore getAppBaseDataCore() {
        return appBaseDataCore;
    }

    public AuthDataManager getAuthDataManager() {
        return authDataManager;
    }

    public HashMap<String, String> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(HashMap<String, String> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(HashMap<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public boolean isSuccessfulResult() {
        return isSuccessfulResult;
    }

    public void setSuccessfulResult(boolean successfulResult) {
        isSuccessfulResult = successfulResult;
    }

    public boolean isFiltering() {
        return isFiltering;
    }

    public void setFiltering(boolean filtering) {
        isFiltering = filtering;
    }

    public String getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(String currentRole) {
        this.currentRole = currentRole;
    }

    public ProjectSpecificationBuilder getProjectSpecificationBuilder() {
        return projectSpecificationBuilder;
    }

    public void setProjectSpecificationBuilder(ProjectSpecificationBuilder projectSpecificationBuilder) {
        this.projectSpecificationBuilder = projectSpecificationBuilder;
    }

    public void initialize(HttpServletRequest request) {
        String isFilteringParam = request.getParameter("isFiltering");
        if (isFilteringParam != null && isFilteringParam.equals("true")) {
            isFiltering = true;
        }
        Iterator<String> iterator;
        String keyWord;
        requestParameters = new HashMap<>();
        iterator = request.getParameterNames().asIterator();
        while (iterator.hasNext()) {
            keyWord = iterator.next();
            requestParameters.put(keyWord, request.getParameter(keyWord));
        }
        requestAttributes = new HashMap<>();
        iterator = request.getAttributeNames().asIterator();
        while (iterator.hasNext()) {
            keyWord = iterator.next();
            requestAttributes.put(keyWord, request.getAttribute(keyWord));
        }
        try {
            currentRole = authDataManager.getCurrentUserRole();
        } catch (ManagerAppAuthException e) {
            //todo log
            currentRole = KeyWordsApp.ROLE_USER_VALUE;
        }
    }

    public void initializePage(HttpServletRequest request, RequestValidator requestValidator) {
        String currentPageNumberStr = request.getParameter(KeyWordsRequest.PAGE_CURRENT_NUMBER);
        String itemsOnPageStr = request.getParameter(KeyWordsRequest.PAGE_ITEMS_COUNT_ON_ONE);
        String sortTypeStr = request.getParameter(KeyWordsRequest.SORT_TYPE);
        String sortFieldStr = request.getParameter(KeyWordsRequest.SORT_SORT_FIELD);
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
            this.pageable = PageRequest.of((int) currentPageNumberValue, (int) itemsOnPageValue, Sort.by(currentSortType, sortFieldStr));
        } else {
            this.pageable = PageRequest.of((int) currentPageNumberValue, (int) itemsOnPageValue);
        }
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
