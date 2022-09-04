package by.ilyin.manager.security;

import by.ilyin.manager.exception.ManagerAppAuthException;

public interface AuthDataManager {

    String getCurrentUsername() throws ManagerAppAuthException;

    long getCurrentUserId() throws ManagerAppAuthException;

    String getCurrentUserRole() throws ManagerAppAuthException;

}
