package org.example;

public class Vehicle {
    private String vin;
    private int year;
    private String make;
    private String model;
    private VehicleType vehicleType;
    private String color;
    private int odometer;
    private double price;

    public Vehicle() {
    }

    public Vehicle(String vin, int year, String make, String model, VehicleType vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;

    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "VIN: " + vin +" Year: " + year + " Model: " + model + " Type: " + vehicleType + "Color: " + color + "Price: $" + price;
    }
}
