package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private static final String FILE_NAME = "contracts.txt";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            String type = (contract instanceof SalesContract) ? "SALE" : "LEASE";

            StringBuilder sb = new StringBuilder();

            sb.append(type).append("|")
                    .append(contract.getDate()).append("|")
                    .append(contract.getCustomerName()).append("|")
                    .append(contract.getCustomerEmail()).append("|")
                    .append(contract.getVehicleSold()).append("|");

            if (contract instanceof SalesContract) {
                SalesContract sc = (SalesContract) contract;

                double salesTax = sc.getVehiclePrice() * 0.05;
                double recordingFee = 100.0;
                double processingFee = (sc.getVehiclePrice() < 10000) ? 295.0 : 495.0;
                double totalPrice = sc.getTotalPrice();
                boolean financed = sc.isFinanced();
                double monthlyPayment = sc.getMonthlyPayment();

                sb.append(String.format("%.2f", salesTax)).append("|")
                        .append(String.format("%.2f", recordingFee)).append("|")
                        .append(String.format("%.2f", processingFee)).append("|")
                        .append(String.format("%.2f", totalPrice)).append("|")
                        .append(financed ? "YES" : "NO").append("|")
                        .append(String.format("%.2f", monthlyPayment));

            } else if (contract instanceof LeaseContract) {
                LeaseContract lc = (LeaseContract) contract;


                double expectedEndingValue = lc.getVehiclePrice() * 0.50;
                double leaseFee = lc.getVehiclePrice() * 0.07;
                double totalPrice = lc.getTotalPrice();
                double monthlyPayment = lc.getMonthlyPayment();

                sb.append(String.format("%.2f", expectedEndingValue)).append("|")
                        .append(String.format("%.2f", leaseFee)).append("|")
                        .append(String.format("%.2f", totalPrice)).append("|")
                        .append(String.format("%.2f", monthlyPayment));
            }

            writer.write(sb.toString());
            writer.newLine();

            System.out.println("Contract saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }

}
