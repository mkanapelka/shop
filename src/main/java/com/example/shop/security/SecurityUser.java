package com.example.shop.security;

import com.example.shop.entity.Status;
import com.example.shop.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
public class SecurityUser implements UserDetails {

    private final String name;
    private final String password;
    private final Set<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String name, String password, Set<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.name = name;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

//    public static UserDetails fromUser(User user) {
//        SecurityUser securityUser = new SecurityUser(
//                user.getName(),
//                user.getPassword(),
//                user.getAuthorities(),
//                user.getStatus().contains(Status.ACTIVE)
//        );
//        return securityUser;
//    }

    public static UserDetails fromUser(User user){
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getStatus().contains(Status.ACTIVE),
                user.getStatus().contains(Status.ACTIVE),
                user.getStatus().contains(Status.ACTIVE),
                user.getStatus().contains(Status.ACTIVE),
                user.getRoles()
        );
    }
}
