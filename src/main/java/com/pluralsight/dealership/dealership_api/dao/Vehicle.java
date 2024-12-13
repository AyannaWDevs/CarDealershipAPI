package com.pluralsight.dealership.dealership_api.dao;

public class Vehicle {
    private String vin;
    private String make;
    private String model;
    private int year;
    private double price;
    private boolean sold;
    private String color;
    private String bodyStyle;
    private int mileage;

    // Constructor
    public Vehicle(String vin, String make, String model, int year, double price, boolean sold, String color, String bodyStyle, int mileage) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.sold = sold;
        this.color = color;
        this.bodyStyle = bodyStyle;
        this.mileage=mileage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", sold=" + sold +
                ", color='" + color + '\'' +
                ", bodyStyle='" + bodyStyle + '\'' +
                ", mileage=" + mileage +
                '}';
    }

    // Getters and Setters
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
