package com.pluralsight.dealership.dealership_api.model;

import java.time.LocalDate;

public class SalesContract extends Contract {

    private LocalDate salesDate;
    private double price;
    private int contractID;

    // Constructor
    public SalesContract(int contractID, String vin, int customerId, int salespersonId, LocalDate salesDate, double price) {
        super(vin, customerId, salespersonId);  // Call to parent constructor (Contract)
        this.contractID = contractID;
        this.salesDate = salesDate;
        this.price = price;
    }

    public SalesContract() {
        super();
    }

    public SalesContract(int id, String vin, int customerId, LocalDate contractDate, double price, int salespersonId) {

    }

    @Override
    public String getContractType() {
        return "Sales";
    }

    @Override
    public String formatContractDetails() {
        return "Sales Contract Details: " +
                "\nID: " + getContractID() +
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

    public void setContractID(int contractID) {
        this.contractID = contractID;
    }

    public int getContractID() {
        return contractID;
    }
}
