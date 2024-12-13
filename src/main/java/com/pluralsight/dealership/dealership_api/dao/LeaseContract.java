package com.pluralsight.dealership.dealership_api.dao;

import java.time.LocalDate;

public class LeaseContract {
    private int id;
    private String vin;
    private int customerId;
    private LocalDate contractDate;
    private String details;
    private int salespersonId;

    public LeaseContract(int id, String vin, int customerId, LocalDate contractDate, String details, int salespersonId) {
        this.id = id;
        this.vin = vin;
        this.customerId = customerId;
        this.contractDate = contractDate;
        this.details = details;
        this.salespersonId = salespersonId;
    }

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

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(int salespersonId) {
        this.salespersonId = salespersonId;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", customerId=" + customerId +
                ", contractDate=" + contractDate +
                ", details='" + details + '\'' +
                ", salespersonId=" + salespersonId +
                '}';
    }
}
