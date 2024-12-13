package com.pluralsight.dealership.dealership_api.dao;

import java.util.List;

public interface VehicleDao {
    boolean addVehicle(Vehicle vehicle);
    List<Vehicle> findAllVehicles();
    List<Vehicle> findVehicleByPrice(double minPrice, double maxPrice);
    List<Vehicle> findVehicleByMakeModel(String make, String model);
    List<Vehicle> findVehicleByYear(int year);
    List<Vehicle> findVehicleByColor(String color);
    boolean deleteVehicle(String vin);
}
