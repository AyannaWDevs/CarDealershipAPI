package com.pluralsight.dealership.dealership_api.controller;

import com.pluralsight.dealership.dealership_api.dao.SalesContractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SalesContractController {

    private final SalesContractDao salesContractDao;

    @Autowired
    public SalesContractController(SalesContractDao salesContractDao) {
        this.salesContractDao = salesContractDao;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalesContract> getAllSalesContracts(@RequestParam(required = false) Integer id) {
        List<SalesContract> contracts = salesContractDao.findAllSalesContracts();
        return contracts.stream()
                .filter(c -> id == null || id.equals(c.getContractID()))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalesContract addSalesContract(@RequestBody SalesContract salesContract) {
        SalesContractDaoImpl.addSalesContract(salesContract);
        return salesContract;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesContract updateSalesContract(@PathVariable("id") int id, @RequestBody SalesContract updatedContract) {
        SalesContract existing = SalesContractDaoImpl.getSalesContractById(id);
        if (existing != null) {
            SalesContractDaoImpl.updateSalesContractPrice(updatedContract);
            return updatedContract;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales Contract not found");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSalesContract(@PathVariable("id") int id) {
        SalesContractDaoImpl.deleteSalesContract(id);
    }
}
