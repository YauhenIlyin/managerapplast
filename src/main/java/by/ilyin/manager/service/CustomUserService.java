package by.ilyin.manager.service;

import by.ilyin.manager.entity.User;

import java.util.Optional;

public interface CustomUserService {

    void save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);
}
