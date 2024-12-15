package com.pluralsight.dealership.dealership_api.controller;

import com.pluralsight.dealership.dealership_api.dao.LeaseContractDaoImpl;
import com.pluralsight.dealership.dealership_api.model.LeaseContract;
import com.pluralsight.dealership.dealership_api.service.LeaseContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaseContracts")
public class LeaseContractController {

    @Autowired
    private LeaseContractService leaseContractService;

    // Get all lease contracts
    @GetMapping
    public List<LeaseContract> getAllLeaseContracts() {
        return leaseContractService.getAllLeaseContracts();
    }

    // Get a lease contract by ID
    @GetMapping("/{id}")
    public LeaseContract getLeaseContractById(@PathVariable int id) {
        return leaseContractService.getLeaseContractById(id);
    }

    // Create a new lease contract
    @PostMapping
    public LeaseContract createLeaseContract(@RequestBody LeaseContract leaseContract) {
        return leaseContractService.createLeaseContract(leaseContract);
    }

    // Update an existing lease contract
    @PutMapping("/{id}")
    public LeaseContract updateLeaseContract(@PathVariable int id, @RequestBody LeaseContract leaseContract) {
        return LeaseContractDaoImpl.updateLeaseContract(id, leaseContract);
    }

    // Delete a lease contract by ID
    @DeleteMapping("/{id}")
    public void deleteLeaseContract(@PathVariable int id) {
        LeaseContractDaoImpl.deleteContract(id);
    }
}
