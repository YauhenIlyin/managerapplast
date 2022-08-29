package by.ilyin.manager.security;

import by.ilyin.manager.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final boolean DEFAULT_VALUE_TRUE = true;
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.user.getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return DEFAULT_VALUE_TRUE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return DEFAULT_VALUE_TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return DEFAULT_VALUE_TRUE;
    }

    @Override
    public boolean isEnabled() {
        return DEFAULT_VALUE_TRUE;
    }

    public long getId() {
        return user.getId();
    }
}
