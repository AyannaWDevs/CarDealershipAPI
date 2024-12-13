package com.pluralsight.dealership.dealership_api.dao;

import java.time.LocalDate;

public class SalesContract {
    private int id;
    private String vin;
    private int customerId;
    private LocalDate salesDate;
    private double price;
    private int salespersonId;

    public SalesContract(int id, String vin, int customerId, LocalDate salesDate, double price, int salespersonId) {
        this.id = id;
        this.vin = vin;
        this.customerId = customerId;
        this.salesDate = salesDate;
        this.price = price;
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

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSalespersonId() {
        return salespersonId;
    }

    public void setSalespersonId(int salespersonId) {
        this.salespersonId = salespersonId;
    }

    @Override
    public String toString() {
        return "SalesContract{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", customerId=" + customerId +
                ", salesDate=" + salesDate +
                ", price=" + price +
                ", salespersonId=" + salespersonId +
                '}';
    }
}
