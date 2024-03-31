package org.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/schema_name";
        String username = "dev";
        String password = "dev";


        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try(Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM students")) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("name"));
                        System.out.println(resultSet.getString("phone_number"));
                        System.out.println(resultSet.getString("description"));
                    }
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (name, phone_number, description) VALUES (?, ?, ?);")) {
                preparedStatement.setString(1, "Anton");
                preparedStatement.setString(2, "34324");
                preparedStatement.setString(3, "student10");
                System.out.println(preparedStatement.executeUpdate());
            }
        }


    }
}
