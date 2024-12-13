package com.pluralsight.dealership.dealership_api.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao {

    // Create (INSERT)
    public boolean addInventoryRecord(int dealershipId, String vin) {
        String query = "INSERT INTO inventory (dealership_id, vin) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dealershipId);
            stmt.setString(2, vin);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all records (SELECT)
    public List<Inventory> getAllInventory() {
        String query = "SELECT * FROM inventory";
        List<Inventory> inventoryRecords = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                inventoryRecords.add(new Inventory(
                        rs.getInt("dealership_id"),
                        rs.getString("vin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryRecords;
    }

    // Retrieve inventory by dealership ID (SELECT)
    public List<Inventory> getInventoryByDealership(int dealershipId) {
        String query = "SELECT * FROM inventory WHERE dealership_id = ?";
        List<Inventory> inventoryRecords = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dealershipId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    inventoryRecords.add(new Inventory(
                            rs.getInt("dealership_id"),
                            rs.getString("vin")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventoryRecords;
    }

    // Update a record (UPDATE)
    public boolean updateInventoryRecord(int dealershipId, String oldVin, String newVin) {
        String query = "UPDATE inventory SET vin = ? WHERE dealership_id = ? AND vin = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newVin);
            stmt.setInt(2, dealershipId);
            stmt.setString(3, oldVin);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a record (DELETE)
    public boolean deleteInventoryRecord(int dealershipId, String vin) {
        String query = "DELETE FROM inventory WHERE dealership_id = ? AND vin = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, dealershipId);
            stmt.setString(2, vin);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
