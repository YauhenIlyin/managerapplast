package by.ilyin.manager.service;

import by.ilyin.manager.entity.User;

public interface SignUpService {

    void register(User user);

    Boolean isExistUserByUsername(String username);

    Boolean isExistUserByEmail(String email);

}
