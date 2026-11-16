package io.github.asmolenkov.cloudfilestorage.security;

import io.github.asmolenkov.cloudfilestorage.model.Role;
import io.github.asmolenkov.cloudfilestorage.model.User;
import lombok.Value;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Set;
import java.util.stream.Collectors;

@Value
public class CustomUserDetails implements UserDetails {

    Long id;
    String username;
    String password;
    Set<GrantedAuthority> authorities;

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public static CustomUserDetails from(User user) {
        return new CustomUserDetails(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getRoles().stream()
                    .map(Role::getName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toUnmodifiableSet())
        );
    }
}
