package com.pluralsight.dealership.dealership_api.model;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private double monthlyPayment;

    public LeaseContract(int id, String vin, int customerId, int salespersonId, LocalDate leaseStartDate, LocalDate leaseEndDate, double monthlyPayment) {
        super(id, vin, customerId, salespersonId);
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String getContractType() {
        return "Lease";
    }

    @Override
    public String formatContractDetails() {
        return "Lease Contract Details: " +
                "\nID: " + getId() +
                "\nVIN: " + getVin() +
                "\nCustomer ID: " + getCustomerId() +
                "\nSalesperson ID: " + getSalespersonId() +
                "\nLease Start Date: " + leaseStartDate +
                "\nLease End Date: " + leaseEndDate +
                "\nMonthly Payment: $" + monthlyPayment;
    }

    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
