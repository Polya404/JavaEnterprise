package org.application.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@AllArgsConstructor(onConstructor_ = {@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)})
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Task> tasksId;
    private String fullName;

    public User(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.tasksId = new ArrayList<>();
    }

    public User() {
    }

    public void addTasks(List<Task> tasks) {
        if (tasksId == null) {
            tasksId = new ArrayList<>();
        }
        tasksId.addAll(tasks);
    }
}
