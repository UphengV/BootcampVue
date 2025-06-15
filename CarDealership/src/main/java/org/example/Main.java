package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String dbUserName = args[0];
        String dbPassword = args[1];
        String connectionString = "jdbc:mysql://localhost:3306/dealership";

        try (Connection connection = DriverManager.getConnection(connectionString, dbUserName, dbPassword)) {

            VehicleDao vehicleDao = new VehicleDao(connection);

            Dealership dealership = new Dealership();

            UserInterface ui = new UserInterface(dealership, vehicleDao);

            ui.display();

        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}