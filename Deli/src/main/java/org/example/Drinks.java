package org.example;

public class Drinks extends Sides {
    private String size;

    public Drinks(String name) {
        super(name);
        this.size = size;
    }


    @Override
    public double getPrice() {
        return switch (size) {
            case "Small" -> 2.00;
            case "Medium" -> 2.50;
            case "Large" -> 3.00;
            default -> 0.0;
        };
    }
}


