package by.ilyin.manager.security.impl;

import by.ilyin.manager.exception.ManagerAppAuthException;
import by.ilyin.manager.security.AuthDataManager;
import by.ilyin.manager.security.CustomUserDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AuthDataManagerImpl implements AuthDataManager {

    private CustomUserDetails userDetails = null;

    public AuthDataManagerImpl() {
    }

    public String getCurrentUsername() throws ManagerAppAuthException {
        initializeCurrentUserDetails();
        return userDetails.getUsername();
    }

    public long getCurrentUserId() throws ManagerAppAuthException {
        initializeCurrentUserDetails();
        return userDetails.getId();
    }

    @Override
    public String getCurrentUserRole() throws ManagerAppAuthException {
        initializeCurrentUserDetails();
        String userRole = userDetails.getAuthorities().toString();
        if (userRole != null && userRole.length() > 2) {
            userRole = userRole.substring(1, userRole.length() - 1);
        } else {
            throw new ManagerAppAuthException("AuthException. User authorities is null or have incorrect value");
        }
        return userRole;
    }

    private void initializeCurrentUserDetails() throws ManagerAppAuthException {
        if (userDetails == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                this.userDetails = (CustomUserDetails) principal;
            } else {
                throw new ManagerAppAuthException("AuthException: Attempt to obtain data by an unauthorized user");
            }
        }
    }

}
