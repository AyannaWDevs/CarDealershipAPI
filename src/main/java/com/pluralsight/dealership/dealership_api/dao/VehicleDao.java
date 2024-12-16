package com.pluralsight.dealership.dealership_api.dao;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface VehicleDao {
    boolean addVehicle(Vehicle vehicle);

    List<Vehicle> findAllVehicles();

    List<Vehicle> findVehicleByPrice(double minPrice, double maxPrice);

    List<Vehicle> findVehicleByMakeModel(String make, String model);

    List<Vehicle> findVehicleByYear(int year);

    List<Vehicle> findVehicleByColor(String color);

    boolean deleteVehicle(String vin);

    Vehicle findVehicleByVin(Integer vehicleVin);
}
