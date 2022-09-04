package by.ilyin.manager.controller.command;

import by.ilyin.manager.repository.specification.SpecificationBuilder;
import by.ilyin.manager.security.AuthDataManager;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionRequestContent {

    private HashMap<String, String> requestParameters;
    private HashMap<String, Object> requestAttributes;
    private final AuthDataManager authDataManager;
    private Pageable pageable = null;
    private SpecificationBuilder specificationBuilder = null;
    private boolean isSuccessfulResult = false;
    private ModelAndView modelAndViewResult;
    private BindingResult bindingResult;

    public SessionRequestContent(AuthDataManager authDataManager) {
        this.authDataManager = authDataManager;
        this.requestParameters = new HashMap<>();
        this.requestAttributes = new HashMap<>();
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

    public AuthDataManager getAuthDataManager() {
        return authDataManager;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public SpecificationBuilder getSpecificationBuilder() {
        return specificationBuilder;
    }

    public void setSpecificationBuilder(SpecificationBuilder specificationBuilder) {
        this.specificationBuilder = specificationBuilder;
    }

    public boolean isSuccessfulResult() {
        return isSuccessfulResult;
    }

    public void setSuccessfulResult(boolean successfulResult) {
        isSuccessfulResult = successfulResult;
    }

    public ModelAndView getModelAndViewResult() {
        return modelAndViewResult;
    }

    public void setModelAndViewResult(ModelAndView modelAndViewResult) {
        this.modelAndViewResult = modelAndViewResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    public void setBindingResult(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public void initialize(HttpServletRequest request) {
        Iterator<String> iterator;
        String keyWord;
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
    }
}
