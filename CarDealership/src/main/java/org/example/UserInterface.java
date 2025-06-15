package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private VehicleDao vehicleDao;
    private Scanner scanner;
    private ContractDataManager contractManager;

    public UserInterface(Dealership dealership, VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
        scanner = new Scanner(System.in);
        contractManager = new ContractDataManager();
    }

    public void display() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- DealerShip Menu ---");
            System.out.println("1.)View all vehicles");
            System.out.println("2.)Search by price");
            System.out.println("3.)Search by make and model");
            System.out.println("4.)Search by year");
            System.out.println("5.)Search by color");
            System.out.println("6.)Search by Mileage");
            System.out.println("7.)Search by vehicle type");
            System.out.println("8.)Add a vehicle");
            System.out.println("9.)Remove a vehicle");
            System.out.println("10.)Add Contract");
            System.out.println("0.)Exit");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        processAllVehiclesRequest();
                        break;
                    case 2:
                        processGetByPriceRequest();
                        break;
                    case 3:
                        processGetByMakeModelRequest();
                        break;
                    case 4:
                        processGetByYearRequest();
                        break;
                    case 5:
                        processGetByColorRequest();
                        break;
                    case 6:
                        processGetByMileageRequest();
                        break;
                    case 7:
                        processGetByVehicleTypeRequest();
                        break;
                    case 8:
                        processAddVehicleRequest();
                        break;
                    case 9:
                        processRemoveVehicleRequest();
                        break;
                    case 10:
                        processAddContractRequest();
                        break;
                    case 11:
                        System.exit(0);
                    default:
                        System.out.println("what are you up to..?");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a number bro");
            }

        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        List<Vehicle> results = vehicleDao.searchByPriceRange(min, max);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();
        List<Vehicle> results = vehicleDao.searchByMakeModel(make, model);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByYearRequest() {
        System.out.print("Enter min year: ");
        int min = scanner.nextInt();
        System.out.print("Enter max year: ");
        int max = scanner.nextInt();
        List<Vehicle> results = vehicleDao.searchByYearRange(min, max);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();
        List<Vehicle> results = vehicleDao.searchByColor(color);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter min mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter max mileage: ");
        int max = scanner.nextInt();
        List<Vehicle> results = vehicleDao.searchByMileageRange(min, max);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type (TRUCK, SUV, etc.): ");
        String type = scanner.next().toUpperCase();
        List<Vehicle> results = vehicleDao.searchByType(type);
        for (Vehicle v : results) System.out.println(v);
    }

    public void processAllVehiclesRequest() {
        List<Vehicle> all = vehicleDao.getAllVehicles();
        for (Vehicle v : all) System.out.println(v);
    }

    public void processAddVehicleRequest() {

        try {
            System.out.println("Enter vehicle VIN (string): ");
            String vin = scanner.nextLine().trim();

            System.out.println("Enter vehicle year (int): ");
            int year = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Enter make: ");
            String make = scanner.nextLine().trim();

            System.out.println("Enter model: ");
            String model = scanner.nextLine().trim();

            System.out.println("Enter vehicle type (e.g., SEDAN, SUV, TRUCK): ");
            String typeInput = scanner.nextLine().trim().toUpperCase();
            VehicleType vehicleType;
            try {
                vehicleType = VehicleType.valueOf(typeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Defaulting to SEDAN.");
                vehicleType = VehicleType.SEDAN;
            }

            System.out.println("Enter color: ");
            String color = scanner.nextLine().trim();

            System.out.println("Enter odometer (int): ");
            int odometer = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Enter price (double): ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

            boolean added = vehicleDao.addVehicle(vehicle);
            if (added) {
                System.out.println("Vehicle added successfully.");
            } else {
                System.out.println("Failed to add vehicle.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input type. Please enter numbers where required.");
        }
    }
        public void processRemoveVehicleRequest() {
            System.out.print("Enter VIN to remove: ");
            String vin = scanner.nextLine().trim();

            boolean removed = vehicleDao.removeVehicle(vin);
            if (removed) {
                System.out.println("Vehicle removed.");
            } else {
                System.out.println("Vehicle not found.");
            }
        }
    public void processAddContractRequest() {
        System.out.println("Enter contract type (SALE or LEASE):");
        String contractType = scanner.nextLine().trim().toUpperCase();

        System.out.println("Enter contract date (YYYYMMDD):");
        String date = scanner.nextLine().trim();

        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine().trim();

        System.out.println("Enter customer email:");
        String customerEmail = scanner.nextLine().trim();

        System.out.println("Enter vehicle VIN:");
        String vehicleSold = scanner.nextLine().trim();

        System.out.println("Enter vehicle price:");
        double vehiclePrice;
        try {
            vehiclePrice = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price. Contract cancelled.");
            return;
        }

        Contract contract = null;

        if (contractType.equals("SALE")) {
            System.out.println("Is the vehicle financed? (YES or NO):");
            String financedInput = scanner.nextLine().trim().toUpperCase();
            boolean isFinanced = financedInput.equals("YES");

            contract = new SalesContract(date, customerName, customerEmail, vehicleSold, vehiclePrice, isFinanced);

        } else if (contractType.equals("LEASE")) {
            contract = new LeaseContract(date, customerName, customerEmail, vehicleSold, vehiclePrice);

        } else {
            System.out.println("Invalid contract type entered.");
            return;
        }

        contractManager.saveContract(contract);
        System.out.println("Contract saved successfully.");
    }
    }






