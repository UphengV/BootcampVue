package org.example;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Transactions> transactionsList = FileManager.readfile();


        while (true) {

            System.out.println("Home Screen");
            System.out.println("1.)Add Deposit");
            System.out.println("2.)Make Payment");
            System.out.println("3.)Ledger");
            System.out.println("4.)Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addDeposit(transactionsList, scanner);
                    break;
                case 2:
                    makePayment(transactionsList, scanner);
                    break;
                case 3:
                    showLedgerMenu(transactionsList, scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }

    public static void showLedgerMenu(List<Transactions> transactions, Scanner scanner) {


        while (true) {
            System.out.println("1.)Display Entries");
            System.out.println("2.)Deposits");
            System.out.println("3.)Payments");
            System.out.println("4.)Vendor Reports");
            System.out.println("5.)Home Screen");

            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    displayEntries(transactions);
                    break;
                case 2:
                    displayDeposits(transactions);
                    break;
                case 3:
                    displayPayments(transactions);
                    break;
                case 4:
                    searchByVendor(transactions, scanner);
                    break;
                case 5:
                    System.out.println("Are you sure you want to go to Home screen?");
                    String userInput = scanner.nextLine();

                    System.out.println("Are you sure?");
                    String userInput2 = scanner.nextLine();

                    System.out.println("Are you FOR sure?!");
                    String userInput3 = scanner.nextLine();

                    if (userInput.equalsIgnoreCase("Yes") && userInput2.equalsIgnoreCase("Yes") && userInput3.equalsIgnoreCase("Yes")) {

                        return;
                    } else {
                        System.out.println("Looks like you are stuck with me :3");
                    }
                    break;
                default:
                    System.out.println("Invalid!");
                    break;
            }
        }
    }


        public static void searchByVendor (List < Transactions > transactions, Scanner scanner){
            System.out.println("Enter Vendor Name to search ");
            String vendorSearch = scanner.nextLine().toLowerCase();

            boolean found = false;
            System.out.println("Transactions by Vendor " + vendorSearch);
            for (Transactions t : transactions) {
                if (t.getVendor().toLowerCase().contains(vendorSearch)) {
                    System.out.println(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No vendor found");
            }
        }

        public static void displayPayments (List < Transactions > transactions) {

            boolean found = false;
            for (Transactions t : transactions) {
                //comparing amount to 0 (shows negative)
                //english terms If this transaction's amount is less than zero (it's a negative number), then treat it like a payment
                if (t.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No payments found.");
            }
        }

        public static void displayDeposits (List < Transactions > transactions) {

            boolean found = false;
            for (Transactions t : transactions) {
                //comparing amount to 0 (shows positive)
                if (t.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    System.out.println(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No deposits found.");
            }
        }

        public static void displayEntries (List < Transactions > transactions) {
            if (transactions.isEmpty()) {
                System.out.println("No transactions to display.");
                return;
            }

            for (Transactions t : transactions) {
                System.out.println(t.getDate() + " | " + t.getTime() + " | " + t.getDescription() + " | " + t.getVendor() + " | " + t.getAmount());
            }
        }

        public static void makePayment (List < Transactions > Transaction, Scanner scanner){
            try {

                System.out.println("Enter date (yyyy-MM-dd):");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(scanner.nextLine(), dateFormatter);


                System.out.println("Enter time (HH:mm):");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime time = LocalTime.parse(scanner.nextLine(), timeFormatter);


                System.out.println("Enter description:");
                String description = scanner.nextLine();

                System.out.println("Enter vendor:");
                String vendor = scanner.nextLine();

                System.out.println("Enter amount:");
                String amountInput = scanner.nextLine();
                BigDecimal amount = new BigDecimal(amountInput.trim()).negate(); // converts to negative

                Transactions payment = new Transactions(date, time, description, vendor, amount);
                Transaction.add(payment);
                FileManager.appendTransaction(payment);

                System.out.println("Payment recorded!");


            } catch (DateTimeParseException e) {
                System.out.println("Invalid!");
                return;
            }


        }

        public static void addDeposit (List < Transactions > Transaction, Scanner scanner){
            try {

                System.out.println("Enter date (yyyy-MM-dd)");
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(scanner.nextLine(), dateFormatter);


                System.out.println("Enter time (HH:mm)");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime time = LocalTime.parse(scanner.nextLine(), timeFormatter);


                System.out.println("Enter description");
                String description = scanner.nextLine();

                System.out.println("Enter vendor");
                String vendor = scanner.nextLine();

                System.out.println("Enter amount");
                String amountInput = scanner.nextLine();
                BigDecimal amount = new BigDecimal(amountInput.trim());

                Transactions deposit = new Transactions(date, time, description, vendor, amount);
                Transaction.add(deposit);
                FileManager.appendTransaction(deposit);

                System.out.println("Deposit added!");

            } catch (DateTimeParseException e) {
                System.out.println("Invalid!");
                return;
            }


        }
    }

