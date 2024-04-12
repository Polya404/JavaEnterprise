package org.application.repositories.task;

import org.application.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t ORDER BY CASE WHEN :sortField = 'id' THEN t.id END, " +
            "CASE WHEN :sortField = 'userId' THEN t.userId END, " +
            "CASE WHEN :sortField = 'name' THEN t.name END, " +
            "CASE WHEN :sortField = 'status' THEN t.status END, " +
            "CASE WHEN :sortField = 'description' THEN t.description END, " +
            "CASE WHEN :sortField = 'deadline' THEN t.deadline END, " +
            "CASE WHEN :sortField = 'priority' THEN t.priority END")
    List<Task> getAllTaskSortedByFieldName(@Param("sortField") String sortField);
    List<Task> getTasksByUserId(Integer userId);
}
