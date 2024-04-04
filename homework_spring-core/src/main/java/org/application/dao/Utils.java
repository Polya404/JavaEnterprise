package org.application.dao;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.application.models.Status;
import org.application.models.Task;

import java.sql.ResultSet;
import java.time.LocalDate;

@UtilityClass
public class Utils {

    @SneakyThrows
    Task extractTaskFromResultSet(ResultSet resultSet) {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String name = resultSet.getString("name");
        Status status = Status.valueOf(resultSet.getString("status"));
        String description = resultSet.getString("description");
        LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
        int priority = resultSet.getInt("priority");
        return new Task(id, userId, name, status, description, deadline, priority);
    }
}
