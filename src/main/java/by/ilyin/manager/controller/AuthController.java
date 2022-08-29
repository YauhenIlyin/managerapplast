package by.ilyin.manager.controller;

import by.ilyin.manager.entity.User;
import by.ilyin.manager.service.SignUpService;
import by.ilyin.manager.util.validator.CustomUserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private CustomUserValidator userValidator;
    private SignUpService signUpService;

    public AuthController(CustomUserValidator userValidator, SignUpService signUpService) {
        this.userValidator = userValidator;
        this.signUpService = signUpService;
    }

    @GetMapping("/signup")
    public String signUpPage(@ModelAttribute("user") User user) {
        return "auth/sign_up";
    }

    @PostMapping("/signup")
    public String performSignUp(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        String resultPage;
        if (bindingResult.hasErrors()) {
            resultPage = "/auth/sign_up";
        } else {
            signUpService.register(user);
            resultPage = "redirect:/auth/signin";
        }
        return resultPage;
    }

    @GetMapping("/signin")
    public String signInPage() {
        return "auth/sign_in";
    }
}
