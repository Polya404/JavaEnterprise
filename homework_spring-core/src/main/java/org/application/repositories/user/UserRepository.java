package org.application.repositories.user;

import org.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u JOIN Task t ON u.id = t.userId WHERE t.id = :taskId")
    User findUserByTaskId(@Param("taskId") Integer taskId);
}
