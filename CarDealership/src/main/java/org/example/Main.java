package org.example;

public class Main {
    public static void main(String[] args) {
     Dealership dealership = DealershipFileManager.getDealership();
     UserInterface ui = new UserInterface(dealership);
     ui.display();
    DealershipFileManager.saveDealership(dealership);
    }
}