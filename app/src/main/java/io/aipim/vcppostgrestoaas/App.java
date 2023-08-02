package io.aipim.vcppostgrestoaas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        // JDBC URL, username, and password of PostgreSQL server
        String url = "jdbc:postgresql://192.168.0.33:5437/mod";
        String user = "sa";
        String password = "1";

        // Query to be executed
        String query = "SELECT * FROM category";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String column1 = resultSet.getString("id_code");
                String column2 = resultSet.getString("level");
                System.out.println("Column1: " + column1 + ", Column2: " + column2);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }
}
