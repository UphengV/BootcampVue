package org.example;

import java.util.Scanner;

public class UI {
    public static class DeliUI {
        private Scanner scanner = new Scanner(System.in);
        private Order currentOrder;

        public void run() {
            while (true) {
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
            System.out.println("Choose bread (white/wheat/rye/wrap):");
            String bread = scanner.nextLine();

            System.out.println("Choose size (4/8/12):");
            String size = scanner.nextLine();

            System.out.println("Toasted? (yes/no):");
            boolean toasted = scanner.nextLine().equalsIgnoreCase("yes");

            Sandwich sandwich = new Sandwich(bread, size, toasted);

            System.out.println("Add meats (type 'done' to stop):");
            while (true) {
                String meat = scanner.nextLine();
                if (meat.equalsIgnoreCase("done")) break;
                sandwich.addTopping(new Meats(meat));
            }

            System.out.println("Add cheeses (type 'done' to stop):");
            while (true) {
                String cheese = scanner.nextLine();
                if (cheese.equalsIgnoreCase("done")) break;
                sandwich.addTopping(new Cheese(cheese));
            }

            System.out.println("Add regular toppings (type 'done' to stop):");
            while (true) {
                String topping = scanner.nextLine();
                if (topping.equalsIgnoreCase("done")) break;
                sandwich.addTopping(new RegularTopping(topping));
            }

            currentOrder.addSandwich(sandwich);
        }

        private void addDrink() {
            System.out.println("Enter drink name:");
            String name = scanner.nextLine();
            System.out.println("Size (Small/Medium/Large):");
            String size = scanner.nextLine();
            currentOrder.addSide(new Drinks(name, size));
        }

        private void addChips() {
            System.out.println("Enter chip type:");
            String chip = scanner.nextLine();
            currentOrder.addSide(new Chips(chip));
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
