package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VehicleDao {
    private final Connection connection;
    private String userName;
    private String password;
    private List<Vehicle> allVehicles;

    public VehicleDao(Connection connection) {
        this.connection = connection;
        this.userName = userName;
        this.password = password;
    }
    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE Price BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapResultSetToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE Make = ? AND Model = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, make);
            stmt.setString(2, model);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapResultSetToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE Year BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, minYear);
            stmt.setInt(2, maxYear);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapResultSetToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE Color = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, color);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapResultSetToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE Odometer BETWEEN ? AND ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, minMileage);
            stmt.setInt(2, maxMileage);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapResultSetToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE VehicleType = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, type);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapResultSetToVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Phase 2 - Add Vehicle

    public boolean addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (VIN, Make, Model, Year, Color, Sold, Odometer, Price, VehicleType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getOdometer());
            stmt.setDouble(7, vehicle.getPrice());
            stmt.setString(8, vehicle.getVehicleType().toString());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phase 2 - Remove Vehicle

    public boolean removeVehicle(String vin) {
        String sql = "DELETE FROM vehicles WHERE VIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, vin);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Vehicle mapResultSetToVehicle(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle(); // Use no-arg constructor

        // Set each property one at a time using setters
        vehicle.setVin(rs.getString("VIN"));
        vehicle.setYear(rs.getInt("Year"));
        vehicle.setMake(rs.getString("Make"));
        vehicle.setModel(rs.getString("Model"));

        // Convert string to enum (make sure the database value matches the enum name)
        String type = rs.getString("VehicleType");
        vehicle.setVehicleType(VehicleType.valueOf(type.toUpperCase()));

        vehicle.setColor(rs.getString("Color"));
        vehicle.setOdometer(rs.getInt("Odometer"));
        vehicle.setPrice(rs.getDouble("Price"));

        return vehicle;
    }

    public List<Vehicle> getAllVehicles() {
        return allVehicles;
    }

    public void setAllVehicles(List<Vehicle> allVehicles) {
        this.allVehicles = allVehicles;
    }
}