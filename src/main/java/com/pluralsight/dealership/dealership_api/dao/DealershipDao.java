package com.pluralsight.dealership.dealership_api.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDao {

    public boolean addDealership(Dealership dealership) {
        String query = "INSERT INTO dealerships (name, address, phone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, dealership.getName());
            stmt.setString(2, dealership.getAddress());
            stmt.setString(3, dealership.getPhone());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Dealership> getAllDealerships() {
        String query = "SELECT * FROM dealerships";
        List<Dealership> dealerships = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                dealerships.add(new Dealership(
                        rs.getInt("dealership_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    public boolean updateDealership(int id, String newName, String newAddress, String newPhone) {
        String query = "UPDATE dealerships SET name = ?, address = ?, phone = ? WHERE dealership_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setString(2, newAddress);
            stmt.setString(3, newPhone);
            stmt.setInt(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDealership(int id) {
        String query = "DELETE FROM dealerships WHERE dealership_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
