package com.pluralsight.dealership.dealership_api.dao;

import com.pluralsight.dealership.dealership_api.model.SalesContract;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDao {

    private final DataSource dataSource;

    // Constructor to inject DataSource
    public SalesContractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Retrieve all sales contracts
    public List<SalesContract> findAllSalesContracts() {
        List<SalesContract> salesContracts = new ArrayList<>();
        String sql = "SELECT * FROM sales_contracts";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SalesContract contract = new SalesContract();
                contract.setContractID(rs.getInt("id"));
                contract.setVin(rs.getString("vin"));
                contract.setCustomerId(rs.getInt("customer_id"));
                contract.setSalesDate(rs.getDate("sales_date").toLocalDate());
                contract.setPrice(rs.getDouble("price"));
                contract.setSalespersonId(rs.getInt("salesperson_id"));
                salesContracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesContracts;
    }

    // Find sales contract by ID
    public SalesContract findSalesContractById(int id) {
        SalesContract contract = null;
        String sql = "SELECT * FROM sales_contracts WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    contract = new SalesContract();
                    contract.setContractID(rs.getInt("id"));
                    contract.setVin(rs.getString("vin"));
                    contract.setCustomerId(rs.getInt("customer_id"));
                    contract.setSalesDate(rs.getDate("sales_date").toLocalDate());
                    contract.setPrice(rs.getDouble("price"));
                    contract.setSalespersonId(rs.getInt("salesperson_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contract;
    }

    // Add a new sales contract
    public void addSalesContract(SalesContract salesContract) {
        String sql = "INSERT INTO sales_contracts (vin, customer_id, sales_date, price, salesperson_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, salesContract.getVin());
            stmt.setInt(2, salesContract.getCustomerId());
            stmt.setDate(3, Date.valueOf(salesContract.getSalesDate().toString()));
            stmt.setDouble(4, salesContract.getPrice());
            stmt.setInt(5, salesContract.getSalespersonId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing sales contract
    public void updateSalesContract(SalesContract salesContract) {
        String sql = "UPDATE sales_contracts SET vin = ?, customer_id = ?, sales_date = ?, price = ?, salesperson_id = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, salesContract.getVin());
            stmt.setInt(2, salesContract.getCustomerId());
            stmt.setDate(3, Date.valueOf(salesContract.getSalesDate().toString()));
            stmt.setDouble(4, salesContract.getPrice());
            stmt.setInt(5, salesContract.getSalespersonId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a sales contract by ID
    public void deleteSalesContract(int id) {
        String sql = "DELETE FROM sales_contracts WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
