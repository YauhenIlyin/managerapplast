package by.ilyin.manager.service.impl;

import by.ilyin.manager.entity.User;
import by.ilyin.manager.evidence.KeyWordsApp;
import by.ilyin.manager.repository.CustomUserRepository;
import by.ilyin.manager.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final CustomUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignUpServiceImpl(CustomUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(KeyWordsApp.ROLE_USER_VALUE);
        userRepository.save(user);
    }

    @Override
    public Boolean isExistUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.isPresent();
    }

    @Override
    public Boolean isExistUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }
}
