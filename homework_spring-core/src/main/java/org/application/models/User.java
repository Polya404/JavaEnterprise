package org.application.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
public class User {
    private final int id;
    private List<Integer> tasksId = new ArrayList<>();
    private String fullName;

    public void addTask(int id){
        tasksId.add(id);
    }
}
