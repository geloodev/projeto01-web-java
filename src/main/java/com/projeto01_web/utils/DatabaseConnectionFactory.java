package com.projeto01_web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/gym_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error on database connection: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error on Class.forName: " + e.getMessage());
        }
    }
}
