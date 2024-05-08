package org.application.repository;

import org.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findUsersByBirthDateBetween(LocalDate from, LocalDate to);
}
