package org.example;

public class SalesContract extends Contract {
    private double vehiclePrice;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, String vehicleSold, double vehiclePrice, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.vehiclePrice = vehiclePrice;
        this.isFinanced = isFinanced;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        double salesTax = vehiclePrice * 0.05;
        double recordingFee = 100.0;
        double processingFee;
        if (vehiclePrice < 10000) {
            processingFee = 295.0;
        } else {
            processingFee = 495.0;
        }
        return vehiclePrice + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment(){
        if (!isFinanced){
            return 0.0;
        }
        double totalPrice = getTotalPrice();
        double interestRate;
        int months;

        if (vehiclePrice >= 10000) {
            interestRate = 0.0425;
            months = 48;
        } else {
            interestRate = 0.0525;
            months = 24;
        }

        double monthlyRate = interestRate / 12;
        return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, - months));
    }
}
