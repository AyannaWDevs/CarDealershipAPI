package com.pluralsight.dealership.dealership_api.dao;

import com.pluralsight.dealership.dealership_api.config.DatabaseConfiguration;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class SalesContractDaoImpl {

    // Create (INSERT)
    public static boolean addSalesContract(SalesContract salesContract) {
        String query = "INSERT INTO sales_contracts (vin, customer_id, sales_date, price, salesperson_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfiguration.connect()){
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, salesContract.getVin());
            stmt.setInt(2, salesContract.getCustomerId());
            stmt.setDate(3, Date.valueOf(salesContract.getSalesDate()));
            stmt.setDouble(4, salesContract.getPrice());
            stmt.setInt(5, salesContract.getSalespersonId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all records (SELECT)


    // Retrieve by ID (SELECT)
    public static SalesContract getSalesContractById(int id) {
        String query = "SELECT * FROM sales_contracts WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new SalesContract(
                            rs.getInt("id"),
                            rs.getString("vin"),
                            rs.getInt("customer_id"),
                            rs.getDate("sales_date").toLocalDate(),
                            rs.getDouble("price"),
                            rs.getInt("salesperson_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update (UPDATE)
    public static boolean updateSalesContractPrice(int id, double newPrice) {
        String query = "UPDATE sales_contracts SET price = ? WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newPrice);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete (DELETE)
    public static boolean deleteSalesContract(int id) {
        String query = "DELETE FROM sales_contracts WHERE id = ?";
        try (Connection conn = DatabaseConfiguration.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<SalesContract> getAllSalesContracts() {
        String query = "SELECT * FROM sales_contracts";
        List<SalesContract> salesContracts = new ArrayList<>();
        try (Connection conn = DatabaseConfiguration.connect()){
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                salesContracts.add(new SalesContract(// int id, String vin, int customerId, LocalDate salesDate, double price, int salespersonId)
                        rs.getInt("id"),
                        rs.getString("vin"),
                        rs.getInt("customer_id"),
                        rs.getDate("contract_date").toLocalDate(),
                        rs.getDouble("price"),
                        rs.getInt("salesperson_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesContracts;
    }
}
