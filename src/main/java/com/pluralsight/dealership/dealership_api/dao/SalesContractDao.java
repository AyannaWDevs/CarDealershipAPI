package com.pluralsight.dealership.dealership_api.dao;

import com.pluralsight.dealership.dealership_api.model.SalesContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class SalesContractDao {

    private static final Logger logger = LoggerFactory.getLogger(SalesContractDao.class);
    private final DataSource dataSource;

    public SalesContractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Common method to set SalesContract fields
    private void setSalesContractFields(SalesContract salesContract, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, salesContract.getVin());
        stmt.setInt(2, salesContract.getCustomerId());
        stmt.setDate(3, Date.valueOf(salesContract.getSalesDate().toString()));
        stmt.setDouble(4, salesContract.getPrice());
        stmt.setInt(5, salesContract.getSalespersonId());
    }

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
            logger.error("Error retrieving sales contracts", e);
        }
        return salesContracts;
    }

    public void addSalesContract(SalesContract salesContract) {
        String sql = "INSERT INTO sales_contracts (vin, customer_id, sales_date, price, salesperson_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setSalesContractFields(salesContract, stmt);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error adding sales contract", e);
        }
    }

    public void updateSalesContract(SalesContract salesContract) {
        String sql = "UPDATE sales_contracts SET vin = ?, customer_id = ?, sales_date = ?, price = ?, salesperson_id = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setSalesContractFields(salesContract, stmt);
            stmt.setInt(6, salesContract.getContractID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating sales contract", e);
        }
    }

    public void deleteSalesContract(int id) {
        String sql = "DELETE FROM sales_contracts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting sales contract", e);
        }
    }
    public SalesContract findSalesContractById(int id) {
        SalesContract contract = null;
        String sql = "SELECT * FROM sales_contracts WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Set the ID parameter in the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Create a new SalesContract object from the result set
                    contract = new SalesContract(
                            rs.getInt("id"),
                            rs.getString("vin"),
                            rs.getInt("customer_id"),
                            rs.getInt("salesperson_id"),
                            rs.getDate("sales_date").toLocalDate(),
                            rs.getDouble("price")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
        return contract;
    }
}
