package com.pluralsight.dealership.dealership_api.controller;

import com.pluralsight.dealership.dealership_api.dao.LeaseContractDaoImpl;
import com.pluralsight.dealership.dealership_api.model.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dealership/lease_contracts")
public class LeaseContractController {

    private final LeaseContractDaoImpl leaseContractDao;

    @Autowired
    public LeaseContractController(LeaseContractDaoImpl leaseContractDao) {
        this.leaseContractDao = leaseContractDao;
    }

    // Retrieve a lease contract by its ID
    @GetMapping("/{id}")
    public ResponseEntity<LeaseContract> getLeaseContractById(@PathVariable int id) {
        LeaseContract contract = leaseContractDao.getContractById(id);
        if (contract == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Contract not found
        }
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    // Add a new lease contract
    @PostMapping
    public ResponseEntity<Void> addLeaseContract(@RequestBody LeaseContract leaseContract) {
        boolean added = leaseContractDao.addContract(leaseContract);
        if (added) {
            return new ResponseEntity<>(HttpStatus.CREATED);  // Successfully created
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed to create
        }
    }

    // Retrieve all lease contracts
    @GetMapping
    public ResponseEntity<List<LeaseContract>> getAllLeaseContracts() {
        List<LeaseContract> contracts = leaseContractDao.getAllLeaseContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    // Update details of an existing lease contract
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLeaseContractDetails(@PathVariable int id, @RequestBody String details) {
        boolean updated = leaseContractDao.updateContractDetails(id, details);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);  // Successfully updated
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Contract not found
        }
    }

    // Delete a lease contract by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaseContract(@PathVariable int id) {
        boolean deleted = leaseContractDao.deleteContract(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Successfully deleted
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Contract not found
        }
    }

    // Retrieve lease contracts within a date range and by dealer ID
    @GetMapping("/byDateRange")
    public ResponseEntity<List<LeaseContract>> getLeaseContractsByDateRange(
            @RequestParam int dealerID,
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        // Assuming that your LeaseContractDaoImpl has a method to fetch by date range
        List<LeaseContract> contracts = leaseContractDao.getLeaseInformationWithDateRange(dealerID, startDate, endDate);
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }
}
