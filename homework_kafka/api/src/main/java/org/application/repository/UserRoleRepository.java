package org.application.repository;

import org.application.model.UserRole;
import org.application.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.user.id = :userId")
    @Transactional
    void deleteByUserId(@Param("userId") Long userId);
}
