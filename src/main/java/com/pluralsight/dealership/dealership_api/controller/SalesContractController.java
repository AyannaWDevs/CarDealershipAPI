package com.pluralsight.dealership.dealership_api.controller;

import com.pluralsight.dealership.dealership_api.dao.SalesContractDao;
import com.pluralsight.dealership.dealership_api.model.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/salesContracts")
public class SalesContractController {

    private final SalesContractDao salesContractDao;

    @Autowired
    public SalesContractController(SalesContractDao salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    // Get all sales contracts
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalesContract> getAllSalesContracts() {
        return salesContractDao.findAllSalesContracts();
    }

    // Get a sales contract by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesContract getSalesContractById(@PathVariable int id) {
        SalesContract contract = salesContractDao.findSalesContractById(id);
        if (contract == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Contract not found");
        }
        return contract;
    }

    // Add a new sales contract
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalesContract addSalesContract(@RequestBody SalesContract salesContract) {
        salesContractDao.addSalesContract(salesContract);
        return salesContract;
    }

    // Update an existing sales contract
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesContract updateSalesContract(@PathVariable int id, @RequestBody SalesContract updatedContract) {
        SalesContract existingContract = salesContractDao.findSalesContractById(id);
        if (existingContract == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Contract not found");
        }
        updatedContract.setContractID(id);
        salesContractDao.updateSalesContract(updatedContract);
        return updatedContract;
    }

    // Delete a sales contract by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalesContract(@PathVariable int id) {
        SalesContract contract = salesContractDao.findSalesContractById(id);
        if (contract == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Contract not found");
        }
        salesContractDao.deleteSalesContract(id);
    }
}
