package org.application.lesson_15_springsecurity.repository;

import org.application.lesson_15_springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
