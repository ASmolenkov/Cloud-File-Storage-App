package io.github.asmolenkov.cloudfilestorage.service;

import io.github.asmolenkov.cloudfilestorage.dto.request.RegisterRequest;
import io.github.asmolenkov.cloudfilestorage.dto.response.AuthResponse;
import io.github.asmolenkov.cloudfilestorage.enums.RoleName;
import io.github.asmolenkov.cloudfilestorage.exception.RoleNotFoundException;
import io.github.asmolenkov.cloudfilestorage.exception.UserExistsException;
import io.github.asmolenkov.cloudfilestorage.model.Role;
import io.github.asmolenkov.cloudfilestorage.model.User;
import io.github.asmolenkov.cloudfilestorage.repository.RoleRepository;
import io.github.asmolenkov.cloudfilestorage.repository.UserRepositories;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepositories userRepositories;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse singUp(RegisterRequest registerRequest) {
        if (userRepositories.existsByName(registerRequest.getUsername())) {
            log.info("User with name {} already exists", registerRequest.getUsername());
            throw new UserExistsException("Username: %s is already taken".formatted(registerRequest.getUsername()));
        }

        User user = new User(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
        Role role = roleRepository.findByName(RoleName.ROLE_USER.name())
                                  .orElseThrow(() -> new RoleNotFoundException("Such a role does not exist."));
        user.addRole(role);

        userRepositories.save(user);
        log.info("User {} has been registered successfully", registerRequest.getUsername());
        return new AuthResponse(registerRequest.getUsername());
    }
}
