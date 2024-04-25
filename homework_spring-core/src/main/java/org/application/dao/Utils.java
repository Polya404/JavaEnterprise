package org.application.dao;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.application.models.Status;
import org.application.models.Task;
import org.application.models.User;

import java.sql.ResultSet;
import java.time.LocalDate;

@UtilityClass
public class Utils {

    @SneakyThrows
    Task extractTaskFromResultSet(ResultSet resultSet) {
        int id = resultSet.getInt("id");
        User user = (User) resultSet.getObject("user_id");
        String name = resultSet.getString("name");
        Status status = Status.valueOf(resultSet.getString("status"));
        String description = resultSet.getString("description");
        LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
        int priority = resultSet.getInt("priority");
        return Task.builder()
                .id(id)
                .name(name)
                .status(status)
                .description(description)
                .deadline(deadline)
                .priority(priority)
                .user(user)
                .build();
    }
}
