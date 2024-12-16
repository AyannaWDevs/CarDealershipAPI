package com.pluralsight.dealership.dealership_api.controller;

import com.pluralsight.dealership.dealership_api.dao.Vehicle;
import com.pluralsight.dealership.dealership_api.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.pluralsight.dealership.dealership_api.dao.VehicleDaoImpl;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {

    private final VehicleDao vehicleDao;
    @Autowired
    private VehicleDaoImpl vehicleDaoImpl;

    @Autowired
    public VehicleController(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
        return vehicle;
    }

    @RequestMapping(path = "/{vin}")
    @ResponseStatus(code = HttpStatus.OK)
    public Vehicle findByVin(@PathVariable("vin") Integer vehicleVin) {
        Vehicle vehicle = vehicleDao.findVehicleByVin(vehicleVin);
        if (vehicle == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
        return vehicle;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Vehicle> findAll() {
        return vehicleDao.findAllVehicles();
    }

    @DeleteMapping(path = "/delete/{vin}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable("vin") String vehicleVin) {
        vehicleDao.deleteVehicle(vehicleVin);
    }

    @PutMapping(path = "/update/{vin}")
    @ResponseStatus(code = HttpStatus.OK)
    public Vehicle updateVehicle(@PathVariable("vin") Integer oldVin, @RequestBody Vehicle updatedVehicle) {
        Vehicle existingVehicle = vehicleDao.findVehicleByVin(oldVin);
        if (existingVehicle != null) {
            vehicleDaoImpl.updateVehicle(updatedVehicle, oldVin);
            return updatedVehicle;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found");
        }
    }

    @GetMapping("/search")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Vehicle> searchVehicles(
            @RequestParam(required = false) Integer vin,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer minYear,
            @RequestParam(required = false) Integer maxYear,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer minMiles,
            @RequestParam(required = false) Integer maxMiles,
            @RequestParam(required = false) String type) {

        List<Vehicle> vehicles = vehicleDao.findAllVehicles();

        return vehicles.stream()
                .filter(v -> vin == null || Objects.equals(v.getVin(), vin))
                .filter(v -> minPrice == null || v.getPrice() >= minPrice)
                .filter(v -> maxPrice == null || v.getPrice() <= maxPrice)
                .filter(v -> make == null || (v.getMake() != null && v.getMake().equalsIgnoreCase(make)))
                .filter(v -> model == null || (v.getModel() != null && v.getModel().equalsIgnoreCase(model)))
                .filter(v -> minYear == null || v.getYear() >= minYear)
                .filter(v -> maxYear == null || v.getYear() <= maxYear)
                .filter(v -> color == null || (v.getColor() != null && v.getColor().equalsIgnoreCase(color)))
                .filter(v -> type == null || (v.getType() != null && v.getType().equalsIgnoreCase(type)))
                .collect(Collectors.toList());
    }
}

