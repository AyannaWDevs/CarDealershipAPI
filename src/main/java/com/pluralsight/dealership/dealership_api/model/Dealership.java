package com.pluralsight.dealership.dealership_api.model;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private int dealershipId; // Primary key
    private String name; // Dealership name
    private String address; // Dealership address
    private String phone; // Dealership phone number
    private List<Vehicle> inventory; // Vehicles in the dealership's inventory
    private List<Contract> contractsList; // Contracts associated with the dealership

    // Constructor
    public Dealership(int dealershipId, String name, String address, String phone) {
        this.dealershipId = dealershipId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
        this.contractsList = new ArrayList<>();
    }

    // Inventory Management
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public Vehicle getVehicleByVin(String vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin().equalsIgnoreCase(vin)) {
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> getVehiclesByPrice(double minPrice, double maxPrice) {
        List<Vehicle> vehiclesWithinPriceRange = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) {
                vehiclesWithinPriceRange.add(vehicle);
            }
        }
        return vehiclesWithinPriceRange;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> vehiclesByMakeModel = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                vehiclesByMakeModel.add(vehicle);
            }
        }
        return vehiclesByMakeModel;
    }

    public List<Vehicle> getVehiclesByYear(int year) {
        List<Vehicle> vehiclesByYear = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() == year) {
                vehiclesByYear.add(vehicle);
            }
        }
        return vehiclesByYear;
    }

    public List<Vehicle> getVehiclesByMileage(int maxMileage) {
        List<Vehicle> vehiclesByMileage = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMileage() <= maxMileage) {
                vehiclesByMileage.add(vehicle);
            }
        }
        return vehiclesByMileage;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> vehiclesByType = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getType().equalsIgnoreCase(type)) {
                vehiclesByType.add(vehicle);
            }
        }
        return vehiclesByType;
    }

    // Contract Management
    public void addContract(Contract contract) {
        contractsList.add(contract);
    }

    public List<Contract> getLast10Contracts() {
        int size = contractsList.size();
        if (size <= 10) {
            return new ArrayList<>(contractsList);
        } else {
            return new ArrayList<>(contractsList.subList(size - 10, size));
        }
    }

    // Getters and Setters for Dealership Fields
    public int getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "dealershipId=" + dealershipId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", inventory=" + inventory +
                ", contractsList=" + contractsList +
                '}';
    }
}
