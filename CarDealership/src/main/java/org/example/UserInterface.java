package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner;

    public UserInterface(Dealership dealership) {
        this.dealership = dealership;
        scanner = new Scanner(System.in);
    }

    public void display() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- DealerShip Menu ---");
            System.out.println("1.)View all vehicles");
            System.out.println("2.)Search by price");
            System.out.println("3.)Search by make and model");
            System.out.println("4.)Sear by year");
            System.out.println("5.)Search by color");
            System.out.println("7.)Search by vehicle type");
            System.out.println("8.)Add a vehicle");
            System.out.println("9.)Remove a vehicle");
            System.out.println("0.)Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice){
                    case 1:
                        processAllVehiclesRequest();
                        break;
                    case 2:
                        processGetByPriceRequest();
                        break;
                    case 3:
                        processGetByMakeModelRequest();
                    case 4:
                        processGetByYearRequest();
                        break;

                }
            }

        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        List<Vehicle> results = dealership.getVehiclesByPrice(min, max);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();
        List<Vehicle> results = dealership.getVehiclesByMakeModel(make, model);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByYearRequest() {
        System.out.print("Enter min year: ");
        int min = scanner.nextInt();
        System.out.print("Enter max year: ");
        int max = scanner.nextInt();
        List<Vehicle> results = dealership.getVehiclesByYear(min, max);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();
        List<Vehicle> results = dealership.getVehiclesByColor(color);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter min mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter max mileage: ");
        int max = scanner.nextInt();
        List<Vehicle> results = dealership.getVehiclesByMileage(min, max);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type (CAR, TRUCK, SUV, etc.): ");
        String type = scanner.next().toUpperCase();
        List<Vehicle> results = dealership.getVehiclesByType(VehicleType.valueOf(type));
        for (Vehicle v : results) System.out.println(v);
    }

    public void processAllVehiclesRequest() {
        List<Vehicle> all = dealership.getAllVehicles();
        for (Vehicle v : all) System.out.println(v);
    }

    public void processAddVehicleRequest(List<Vehicle> vehicles) {

        try {
            System.out.println("Enter vehicle VIN (int): ");
            int vin = scanner.nextInt();

            System.out.println("Enter vehicle year (int): ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter make: ");
            String make = scanner.nextLine();

            System.out.println("Enter model: ");
            String model = scanner.nextLine();

            System.out.println("Enter vehicle type (e.g., SEDAN, SUV, TRUCK): ");
            String typeInput = scanner.nextLine().toUpperCase();
            VehicleType vehicleType;
            try {
                vehicleType = VehicleType.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Defaulting to SEDAN.");
                vehicleType = VehicleType.SEDAN;
            }

            System.out.println("Enter color: ");
            String color = scanner.nextLine();

            System.out.println("Enter odometer (int): ");
            int odometer = scanner.nextInt();

            System.out.println("Enter price (double): ");
            double price = scanner.nextDouble();

            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.addVehicle(vehicle);
            DealershipFileManager.saveDealership(dealership);

            System.out.println("Vehicle added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter numbers where required.");
            scanner.nextLine();
        }

    }

    public void processRemoveVehicleRequest() {

        System.out.print("Enter VIN to remove: ");
        int vin = scanner.nextInt();
        Vehicle toRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }
        if (toRemove != null) {
            dealership.removeVehicle(toRemove);
            System.out.println("Vehicle removed.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}

