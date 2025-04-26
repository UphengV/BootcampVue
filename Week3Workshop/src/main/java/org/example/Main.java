package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Loading csv file");
        //List<Product> products = FileLoader.readFile();
        //for (Product product : products) {
        //System.out.println(product.getProductName());
        Scanner scanner = new Scanner(System.in);
        List<Product> products = FileLoader.readFile();
        List<Product> allProducts = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();


        while (true) {
            System.out.println("Welcome to the store! Choose an option:");
            System.out.println("1. View all products");
            System.out.println("2. Search by SKU");
            System.out.println("3. Search by price range");
            System.out.println("4. Search by name");
            System.out.println("5. Add to cart");
            System.out.println("6. Remove from cart");
            System.out.println("7. View cart");
            System.out.println("8. Checkout");
            System.out.println("9. Exit");


            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Here is the list of product!");
                    displayProducts(products);
                    break;
                case 2:
                    System.out.print("Enter SKU");
                    String sku = scanner.nextLine();
                    Product foundProduct = findBySku(products, sku);
                    break;
                case 3:
                    System.out.print("Enter minimum price");
                    double min = scanner.nextDouble();
                    System.out.print("Enter maximum price");
                    double max = scanner.nextDouble();
                    scanner.nextLine();
                    List<Product> filtered = filterByPriceRange(products, min, max);
                    break;
                case 4:
                    System.out.println("Enter Name");
                    String productName = scanner.nextLine();
                    List<Product> nameMatches = searchByName(products, productName);
                    break;
                case 5:
                    System.out.print("Enter SKU to add to cart: ");
                    String skuToAdd = scanner.nextLine();
                    Product productToAdd = findBySku(products, skuToAdd);

                    if (productToAdd != null) {
                        shoppingCart.addProductToCart(productToAdd);
                        System.out.println("Product added to cart!");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter SKU to remove from cart: ");
                    String skuToRemove = scanner.nextLine();
                    shoppingCart.removeProduct(skuToRemove);
                    System.out.println("If the product existed, it was removed!");
                    break;
                case 7:
                    if (shoppingCart.getCartItems().isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        System.out.println("Items in your cart:");
                        for (Product product : shoppingCart.getCartItems()) {
                            System.out.println("SKU " + product.getSku());
                            System.out.println("Name " + product.getProductName());
                            System.out.println("Price " + product.getPrice());
                        }
                    }
                    break;
                case 8:
                    double total = shoppingCart.getCartTotal();
                    System.out.println("Your total is: $" + total);
                    System.out.println("Thank you for shopping with us!");
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }

        }
    }
        public static void displayProducts(List<Product> products) {

            for (Product product : products){
                System.out.println("Sku: " + product.getSku());
                System.out.println("Name: " + product.getProductName());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Department: " + product.getDepartment());
            }
        }

    private static List<Product> filterByPriceRange(List<Product> products, double min, double max) {
        List<Product> matches = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                System.out.println("Products: " + product.getPrice() + " " +product.getProductName());
                matches.add(product);
            }

        }
        return matches;
    }


    public static List<Product> searchByName(List<Product> products, String name) {
        List<Product> matches = new ArrayList<>();
        // TODO: Loop through products
        // If product name contains the search string, add to matches
        List<Product> allProducts = new ArrayList<>();
        for (Product Product : products){
            if (Product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Product: " + Product.getProductName());
                matches.add(Product);
            }
        }
        return null;
    }

    public static Product findBySku(List<Product> products, String sku) {

        for (Product Product : products) {
            if (Product.getSku().equalsIgnoreCase(sku)) {
                System.out.println("Product Found!");
                System.out.println("Product: " + Product.getProductName());
                return Product;
            }
        }
        return null;
    }
}


