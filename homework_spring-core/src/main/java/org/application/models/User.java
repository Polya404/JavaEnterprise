package org.application.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ElementCollection
    private List<Integer> tasksId;
    private String fullName;

    public User(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.tasksId = new ArrayList<>();
    }

    public void addTasks(List<Task> tasks) {
        if (tasksId == null) {
            tasksId = new ArrayList<>();
        }
        for (Task task : tasks) {
            tasksId.add(task.getId());
        }
    }
}
