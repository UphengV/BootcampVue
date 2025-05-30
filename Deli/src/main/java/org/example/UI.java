package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class UI {
    public static class DeliUI {
        private Scanner scanner = new Scanner(System.in);
        private Order currentOrder;

        public void run() {
            while (true) {
                System.out.println("Welcome to Up Yours!");
                System.out.println("\n1) New Order\n0) Exit");
                String choice = scanner.nextLine();
                if (choice.equals("1")) startOrder();
                else if (choice.equals("0")) break;
            }
        }

        private void startOrder() {
            currentOrder = new Order();
            while (true) {
                System.out.println("\n1) Add Sandwich\n2) Add Drink\n3) Add Chips\n4) Checkout\n0) Cancel Order");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1" -> addSandwich();
                    case "2" -> addDrink();
                    case "3" -> addChips();
                    case "4" -> checkout();
                    case "0" -> {
                        System.out.println("Order canceled.");
                        return;
                    }
                }
            }
        }

        private void addSandwich() {
            String[] validBreads = {"white", "wheat", "rye", "wrap"};
            String bread = "";
            while (true){
            System.out.println("Choose bread (white/wheat/rye/wrap) or type home to return:");
            bread = scanner.nextLine().trim().toLowerCase();
                if (bread.equalsIgnoreCase("home")) return;
                if (Arrays.asList(validBreads).contains(bread)) {
                    break;
                }
                System.out.println("Invalid bread choice. Please try again.");
            }

            String size = "";
            while (true){
            System.out.println("Choose size (4/8/12) or type home to return:");
            size = scanner.nextLine().trim();
                if (size.equalsIgnoreCase("home")) return;
                if (size.equals("4") || size.equals("8") || size.equals("12")) {
                    break;
                }
                System.out.println("Invalid size. Please enter 4, 8, or 12.");
            }

            boolean toasted = false;
            while (true){
            System.out.println("Toasted? (yes/no) or type home to return:");
            String input = scanner.nextLine().trim().toLowerCase();
                if (input.equalsIgnoreCase("home")) return;
                if (input.equals("yes")) {
                    toasted = true;
                    break;
                } else if (input.equals("no")) {
                    toasted = false;
                    break;
                } else {
                    System.out.println("Please answer 'yes' or 'no'.");
                }
            }

            Sandwich sandwich = new Sandwich(bread, size, toasted);

            //meats
            String[] validMeats = {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
            System.out.println("Add meats (type 'done' to stop, or type home to return):");
            System.out.println("Meats\n" +
                    "- steak\n" +
                    "- ham\n" +
                    "- salami\n" +
                    "- roast beef\n" +
                    "- chicken\n" +
                    "- bacon");
            while (true) {
                String meat = scanner.nextLine().trim().toLowerCase();
                if (meat.equalsIgnoreCase("home")) return;
                if (meat.equalsIgnoreCase("done")) break;
                if (Arrays.asList(validMeats).contains(meat)) {
                    sandwich.addTopping(new Meats(meat));
                } else {
                    System.out.println("Invalid meat. Choose from list.");
                }
            }

            //Cheese
            String[] validCheeses = {"american", "provolone", "cheddar", "swiss"};
            System.out.println("Add cheeses (type 'done' to stop, or type home to return):");
            System.out.println("Cheese\n" +
                    "- american\n" +
                    "- provolone\n" +
                    "- cheddar\n" +
                    "- swiss");
            while (true) {
                String cheese = scanner.nextLine().toLowerCase();
                if (cheese.equalsIgnoreCase("home")) return;
                if (cheese.equalsIgnoreCase("done")) break;
                if (Arrays.asList(validCheeses).contains(cheese)) {
                    sandwich.addTopping(new Cheese(cheese));
                } else {
                    System.out.println("Invalid cheese. Please choose from list!");
                }
            }

            //regular toppings
            String[] validToppings = {
                    "lettuce", "peppers", "onions", "tomatoes", "jalapenos",
                    "cucumbers", "pickles", "guacamole", "mushrooms"
            };
            System.out.println("Add regular toppings (type 'done' to stop or type home to return):");
            System.out.println(" Regular Toppings\n" +
                    "- lettuce\n" +
                    "- peppers\n" +
                    "- onions\n" +
                    "- tomatoes\n" +
                    "- jalapeños\n" +
                    "- cucumbers\n" +
                    "- pickles\n" +
                    "- guacamole\n" +
                    "- mushrooms");
            while (true) {
                String topping = scanner.nextLine().trim().toLowerCase();
                if (topping.equalsIgnoreCase("home")) return;
                if (topping.equalsIgnoreCase("done")) break;
                if (Arrays.asList(validToppings).contains(topping)) {
                    sandwich.addTopping(new RegularTopping(topping));
                } else {
                    System.out.println("Invalid topping! Please choose from the list");
                }
            }

            currentOrder.addSandwich(sandwich);
            System.out.println("Sandwich added to your order!");
        }

        private void addDrink() {
            String[] validSizes = {"small", "medium", "large"};
            while (true) {
                System.out.println("Enter drink Size (Small/Medium/Large) or type home to return:");
                String sizeInput = scanner.nextLine().trim().toLowerCase();

                if (sizeInput.equalsIgnoreCase("home")) return;

                if (Arrays.asList(validSizes).contains(sizeInput)) {
                    currentOrder.addSide(new Drinks(sizeInput));
                    System.out.println("Drink Added!");
                    break;
                } else {
                    System.out.println("Invalid drink size. Please enter Small, Medium, or Large.");
                }
            }
        }

        private void addChips() {
            String[] validChips = {"plain", "bbq", "sour cream", "cheddar", "jalapeno"};
            while (true) {
                System.out.println("Enter chip type or type 'home' to return: ");
                System.out.println("plain, bbq, sour cream , cheddar, jalapeno");
                String chip = scanner.nextLine().trim().toLowerCase();

                if (chip.equalsIgnoreCase("home")) return;

                if (Arrays.asList(validChips).contains(chip)) {
                    currentOrder.addSide(new Chips(chip));
                    System.out.println("Chips added");
                    break;
                } else {
                    System.out.println("Invalid chip type. Please choose from list!");
                }
            }
        }

        private void checkout() {
            System.out.println(currentOrder.getSummary());
            System.out.println("1) Confirm\n0) Cancel");
            String choice = scanner.nextLine();
            if (choice.equals("1")) {
                ReceiptFileManager.saveReceipt(currentOrder);
                System.out.println("Order complete!");
            } else {
                System.out.println("Order canceled.");
            }
        }
    }
}
