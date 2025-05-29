package org.example;

public class Drinks extends Sides {
    private String size;

    public Drinks(String name) {
        super(name);
        this.size = name;
    }


    @Override
    public double getPrice() {

        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0;
        };
    }
}


