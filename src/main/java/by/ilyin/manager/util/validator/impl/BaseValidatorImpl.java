package by.ilyin.manager.util.validator.impl;

import by.ilyin.manager.util.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class BaseValidatorImpl implements BaseValidator {

    private BaseValidatorImpl() {
    }

    public boolean isValidStrAsIntegerNumber(String numberStr) {
        boolean isValid = Boolean.TRUE;
        try {
            Long.parseLong(numberStr);
        } catch (NumberFormatException e) {
            isValid = Boolean.FALSE;
        }
        return isValid;
    }
}
