package com.dhbinh.yummybites.base.security.service;

import com.dhbinh.yummybites.base.security.entity.Role;
import com.dhbinh.yummybites.base.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private GrantedAuthority authority;

    public static UserDetail build(User user) {
        Role roles = user.getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority(roles.name());

        return new UserDetail(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetail user = (UserDetail) o;
        return Objects.equals(id, user.id);
    }
}
