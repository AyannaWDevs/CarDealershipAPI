package com.pluralsight.dealership.dealership_api.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class VehicleDaoImpl implements VehicleDao {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private DataSource dataSource;
    public VehicleDaoImpl(@Value("${db.url}") String dbUrl,
                          @Value("${db.username}") String dbUsername,
                          @Value("${db.password}") String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vin, make, model, year, price, sold, color, body_style) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection()){
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setDouble(5, vehicle.getPrice());
            stmt.setBoolean(6, vehicle.isSold());
            stmt.setString(7, vehicle.getColor());
            stmt.setString(8, vehicle.getBodyStyle());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        String query = "SELECT * FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                vehicles.add(new Vehicle(
                        //(String vin, String make, String model, int year, double price, boolean sold, String color, String bodyStyle,int mileage)
                        rs.getString("vin"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getBoolean("sold"),
                        rs.getString("color"),
                        rs.getString("body_style"),
                        rs.getInt("mileage")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> findVehicleByPrice(double minPrice, double maxPrice) {
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(new Vehicle(
                            rs.getString("vin"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getDouble("price"),
                            rs.getBoolean("sold"),
                            rs.getString("color"),
                            rs.getString("body_style"),
                            rs.getInt("mileage")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> findVehicleByMakeModel(String make, String model) {
        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(new Vehicle(
                            rs.getString("vin"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getDouble("price"),
                            rs.getBoolean("sold"),
                            rs.getString("color"),
                            rs.getString("body_style"),
                            rs.getInt("mileage")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> findVehicleByYear(int year) {
        String query = "SELECT * FROM vehicles WHERE year = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, year);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(new Vehicle(
                            rs.getString("vin"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getDouble("price"),
                            rs.getBoolean("sold"),
                            rs.getString("color"),
                            rs.getString("body_style"),
                            rs.getInt("mileage")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> findVehicleByColor(String color) {
        String query = "SELECT * FROM vehicles WHERE color = ?";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, color);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(new Vehicle(
                            rs.getString("vin"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getDouble("price"),
                            rs.getBoolean("sold"),
                            rs.getString("color"),
                            rs.getString("body_style"),
                            rs.getInt("mileage")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public boolean deleteVehicle(String vin) {
        String query = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, vin);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateVehicle(Vehicle updatedVehicle, Integer oldVin) {
        String query = "UPDATE vehicles SET make = ?, model = ?, year = ?, price = ?, color = ?, mileage = ?, type = ? WHERE vin = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, updatedVehicle.getMake());
            stmt.setString(2, updatedVehicle.getModel());
            stmt.setInt(3, updatedVehicle.getYear());
            stmt.setDouble(4, updatedVehicle.getPrice());
            stmt.setString(5, updatedVehicle.getColor());
            stmt.setString(7, updatedVehicle.getType());
            stmt.setInt(8, oldVin);  // Set the VIN to identify the vehicle

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Returns true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of error
        }
    }
    public Vehicle findVehicleByVin(Integer vehicleVin) {
        String query = "SELECT * FROM vehicles WHERE vin = ?";
        Vehicle vehicle = null;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, vehicleVin);  // Set the VIN parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle(
                            rs.getString("vin"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getDouble("price"),
                            rs.getBoolean("sold"),
                            rs.getString("color"),
                            rs.getString("bodyStyle"),
                            rs.getInt("mileage")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;  // Return the vehicle or null if not found
    }



}
