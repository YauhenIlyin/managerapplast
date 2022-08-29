package by.ilyin.manager.service.impl;


import by.ilyin.manager.entity.User;
import by.ilyin.manager.repository.CustomUserRepository;
import by.ilyin.manager.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserServiceImpl implements CustomUserService {

    private final CustomUserRepository customUserRepository;

    @Autowired
    public CustomUserServiceImpl(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    public void save(User user) {
        customUserRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return customUserRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return customUserRepository.findById(id);
    }


    public Optional<User> findByEmail(String email) {
        return customUserRepository.findByEmail(email);
    }
}
