package io.github.asmolenkov.cloudfilestorage.repository;

import io.github.asmolenkov.cloudfilestorage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    boolean existsByName(String name);
}
