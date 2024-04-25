package org.application.repositories;

import org.application.models.UserTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTasksRepository extends JpaRepository<UserTasks, Integer> {
}
