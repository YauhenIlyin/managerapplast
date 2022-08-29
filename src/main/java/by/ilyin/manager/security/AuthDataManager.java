package by.ilyin.manager.security;

import by.ilyin.manager.exception.ManagerAppAuthException;

public interface AuthDataManager {

    public String getCurrentUsername() throws ManagerAppAuthException;

    public long getCurrentUserId() throws ManagerAppAuthException;

    public String getCurrentUserRole() throws ManagerAppAuthException;

}
