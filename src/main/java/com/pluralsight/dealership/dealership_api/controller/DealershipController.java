package com.pluralsight.dealership.dealership_api.controller;

import com.pluralsight.dealership.dealership_api.dao.Dealership;
import com.pluralsight.dealership.dealership_api.dao.DealershipDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealerships")
public class DealershipController {

    private final DealershipDao dealershipDao;

    @Autowired
    public DealershipController(DealershipDao dealershipDao) {
        this.dealershipDao = dealershipDao;
    }

    @GetMapping
    public ResponseEntity<List<Dealership>> getAllDealerships() {
        List<Dealership> dealerships = dealershipDao.getAllDealerships();
        return new ResponseEntity<>(dealerships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dealership> getDealershipById(@PathVariable int id) {
        Dealership dealership = dealershipDao.getDealershipById(id);
        if (dealership == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dealership, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addDealership(@RequestBody Dealership dealership) {
        boolean added = dealershipDao.addDealership(dealership);
        if (added) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDealership(@PathVariable int id, @RequestBody Dealership dealership) {
        boolean updated = dealershipDao.updateDealership(id, dealership);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable int id) {
        boolean deleted = dealershipDao.deleteDealership(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
