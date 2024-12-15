package com.pluralsight.dealership.dealership_api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class LeaseContractDaoImpl implements LeaseContractDao {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    @Autowired
    public LeaseContractDaoImpl(
            @Value("${db.url}") String dbUrl,
            @Value("${db.username}") String dbUsername,
            @Value("${db.password}") String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }


    @Override
    public boolean addContract(LeaseContract leaseContract) {
        String query = "INSERT INTO contracts (vin, customer_id, contract_date, details, salesperson_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, leaseContract.getVin());
            stmt.setInt(2, leaseContract.getCustomerId());
            stmt.setDate(3, Date.valueOf(leaseContract.getContractDate()));
            stmt.setString(4, leaseContract.getDetails());
            stmt.setInt(5, leaseContract.getSalespersonId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<LeaseContract> getAllLeaseContracts() {
        String query = "SELECT * FROM lease_contracts";
        List<LeaseContract> leaseContracts = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                leaseContracts.add(new LeaseContract(
                        rs.getInt("id"),
                        rs.getString("vin"),
                        rs.getInt("customer_id"),
                        rs.getDate("contract_date").toLocalDate(),
                        rs.getString("details"),
                        rs.getInt("salesperson_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaseContracts;
    }

    @Override
    public LeaseContract getContractById(int id) {
        String query = "SELECT * FROM contracts WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LeaseContract(
                            rs.getInt("id"),
                            rs.getString("vin"),
                            rs.getInt("customer_id"),
                            rs.getDate("contract_date").toLocalDate(),
                            rs.getString("details"),
                            rs.getInt("salesperson_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateContractDetails(int id, String details) {
        String query = "UPDATE contracts SET details = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, details);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteContract(int id) {
        String query = "DELETE FROM contracts WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LeaseContract> getLeaseInformationWithDateRange(int dealerID, Date startDate, Date endDate) {
        List<LeaseContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts WHERE dealer_id = ? AND contract_date BETWEEN ? AND ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, dealerID);
            stmt.setDate(2, startDate);
            stmt.setDate(3, endDate);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contracts.add(new LeaseContract(
                            rs.getInt("id"),
                            rs.getString("vin"),
                            rs.getInt("customer_id"),
                            rs.getDate("contract_date").toLocalDate(),
                            rs.getString("details"),
                            rs.getInt("salesperson_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }

}
