package by.ilyin.manager.util.validator;

import by.ilyin.manager.entity.User;
import by.ilyin.manager.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomUserValidator implements Validator {

    private final int MIN_PASS_LENGTH = 4;
    private final int MAX_PASS_LENGTH = 36;

    private SignUpService signUpService;

    @Autowired
    public CustomUserValidator(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        validateByExistUserByUsername(user, errors);
        validateByExistUserByEmail(user, errors);
        validatePasswords(user, errors);
    }

    private void validateByExistUserByUsername(User user, Errors errors) {
        boolean isExistUser = signUpService.isExistUserByUsername(user.getUsername());
        if (isExistUser) {
            errors.rejectValue("username", "", "User with the same username already exists");
        }
    }

    private void validateByExistUserByEmail(User user, Errors errors) {
        boolean isExistEmail = signUpService.isExistUserByEmail(user.getEmail());
        if (isExistEmail) {
            errors.rejectValue("email", "", "User with the same email already exists");
        }
    }

    private void validatePasswords(User user, Errors errors) {
        String password = user.getPassword();
        String repeatPassword = user.getRepeatPassword();
        if (!password.equals(repeatPassword)) {
            errors.rejectValue("password", "", "Passwords are not equals");
        }
        int lengthPassword = password.length();
        if (lengthPassword < MIN_PASS_LENGTH || lengthPassword > MAX_PASS_LENGTH) {
            errors.rejectValue("password", "", "Password must be between " + MIN_PASS_LENGTH + " and " + MAX_PASS_LENGTH + " characters");
        }
    }
}
