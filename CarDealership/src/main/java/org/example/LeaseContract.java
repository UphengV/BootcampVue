package org.example;

public class LeaseContract extends Contract {
    private double vehiclePrice;

    public LeaseContract(String date, String customerName, String customerEmail, String vehicleSold, double vehiclePrice) {
        super(date, customerName, customerEmail, vehicleSold);
        this.vehiclePrice = vehiclePrice;
    }


    public double getVehiclePrice() {
        return vehiclePrice;
    }

    @Override
    public double getTotalPrice() {
        double expectedEndingValue = vehiclePrice * 0.50;
        double leaseFee = vehiclePrice * 0.07;
        return leaseFee + expectedEndingValue;

    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        double interestRate = 0.04;
        int months = 36;
        double monthlyRate = interestRate / 12;

        return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

}
