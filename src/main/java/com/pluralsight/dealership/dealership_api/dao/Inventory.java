package com.pluralsight.dealership.dealership_api.dao;

public class Inventory {
    private int dealershipId;
    private String vin;

    public Inventory(int dealershipId, String vin) {
        this.dealershipId = dealershipId;
        this.vin = vin;
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "dealershipId=" + dealershipId +
                ", vin='" + vin + '\'' +
                '}';
    }
}
