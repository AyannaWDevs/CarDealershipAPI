package com.pluralsight.dealership.dealership_api.model;

public abstract class Contract {
    private int id; // Primary key
    private String vin;
    private int customerId; // Foreign key
    private int salespersonId; // Foreign key

    // Constructor
    public Contract(int id, String vin, int customerId, int salespersonId) {
        this.id = id;
        this.vin = vin;
        this.customerId = customerId;
        this.salespersonId = salespersonId;
    }

    public Contract() {

    }

    // Abstract methods for subclasses
    public abstract String getContractType(); // e.g., "Sales" or "Lease"
    public abstract String formatContractDetails();

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(int salespersonId) {
        this.salespersonId = salespersonId;
    }
}
