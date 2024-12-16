package com.pluralsight.dealership.dealership_api.dao;

import com.pluralsight.dealership.dealership_api.config.DatabaseConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DealershipDao {
    private DataSource dataSource;

    @Autowired
    public DealershipDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean addDealership(Dealership dealership) {
        String query = "INSERT INTO dealerships (name, address, phone) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
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
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
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
        try (Connection conn = DatabaseConfiguration.connect();
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
        try (Connection conn = DatabaseConfiguration.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateDealership(int id, Dealership dealership) {
        String query = "UPDATE dealerships SET name = ?, address = ?, phone = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, dealership.getName());
            stmt.setString(2, dealership.getAddress());
            stmt.setString(3, dealership.getPhone());
            stmt.setInt(4, id);  // Use the provided id to update the correct record

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Return true if the record was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        }
    }


}
