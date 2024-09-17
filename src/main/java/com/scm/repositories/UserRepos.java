package com.scm.repositories;

import com.scm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepos extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
