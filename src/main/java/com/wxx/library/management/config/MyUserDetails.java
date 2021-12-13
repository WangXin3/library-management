package com.wxx.library.management.config;

import com.wxx.library.management.constant.Num;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author wangxin
 * @since 2021/12/13
 */
@Data
public class MyUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final String id;
    private final Integer enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(String username, String password, String id, Integer enabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public MyUserDetails(String username, String id) {
        this.username = username;
        this.password = null;
        this.id = id;
        this.enabled = null;
        this.authorities = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Num.Y.v().equals(this.enabled);
    }
}
