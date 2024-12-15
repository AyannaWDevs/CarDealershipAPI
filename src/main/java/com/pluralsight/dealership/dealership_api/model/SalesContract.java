package com.pluralsight.dealership.dealership_api.model;

import java.time.LocalDate;

public class SalesContract extends Contract {
    private LocalDate salesDate;
    private double price;

    public SalesContract(int id, String vin, int customerId, int salespersonId, LocalDate salesDate, double price) {
        super(id, vin, customerId, salespersonId);
        this.salesDate = salesDate;
        this.price = price;
    }

    @Override
    public String getContractType() {
        return "Sales";
    }

    @Override
    public String formatContractDetails() {
        return "Sales Contract Details: " +
                "\nID: " + getId() +
                "\nVIN: " + getVin() +
                "\nCustomer ID: " + getCustomerId() +
                "\nSalesperson ID: " + getSalespersonId() +
                "\nSales Date: " + salesDate +
                "\nPrice: $" + price;
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

    public void setContractID(int id) {

    }
}
