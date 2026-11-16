package io.github.asmolenkov.cloudfilestorage.security;


import io.github.asmolenkov.cloudfilestorage.repository.UserRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private static final String USERNAME_NOT_FOUND_MESSAGE_TEMPLATE = "User not found with username: %s";
    private final UserRepositories userRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositories.findByName(username).map(CustomUserDetails::from)
                                    .orElseThrow(() -> new UsernameNotFoundException(
                                            USERNAME_NOT_FOUND_MESSAGE_TEMPLATE.formatted(username)));

    }
}
