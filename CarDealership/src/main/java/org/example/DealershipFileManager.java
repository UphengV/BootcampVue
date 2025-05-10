package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {


    public static Dealership getDealership() {

        try {
            FileReader fileReader = new FileReader("src/main/resources/DealershipWorkshop1.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String input;

            Dealership dealership = new Dealership("my dealership", "123 main st", "555-1234");
            while ((input = bufferedReader.readLine()) != null) {

                String[] parts = input.split("\\|");

                int vin = Integer.parseInt(parts[0].trim());
                int year = Integer.parseInt(parts[1].trim());
                String make = parts[2].trim();
                String model = parts[3].trim();
                VehicleType type = VehicleType.valueOf(parts[4].trim().toUpperCase());
                String color = parts[5].trim();
                int odometer = Integer.parseInt(parts[6].trim());
                double price = Double.parseDouble(parts[7].trim());

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);

            }

            bufferedReader.close();

            return dealership;
        } catch (IOException ex) {
            System.out.println("CSV FILE FAILED");
            return new Dealership("N/A","N/A","N/A");
        }

    }

    public static void saveDealership(Dealership dealership) {
        String filePath = "src/main/resources/DealershipWorkshop1.txt";
        File file = new File(filePath);
        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }
            boolean fileExists = file.exists();
            boolean isEmpty = !fileExists || file.length() == 0;

            FileWriter writer = new FileWriter(file, true);

            if (isEmpty) {
                writer.write("vin|year|make|model|vehicleType|color|odometer|price\n");
            }
            for (Vehicle vehicle : dealership.getAllVehicles()){
                writer.write(vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "/n");



            }

                    writer.close();
        } catch (IOException ex) {
            System.out.println("Something ain't right");
        }
    }
}
