package org.example;

public class Cheese extends Toppings{
    public Cheese(String name) {
        super(name);
    }

    @Override
    public double getPrice(String size, Boolean isExtra) {
        double base = switch (size) {
            case "4" -> 0.75;
            case "8" -> 1.50;
            case "12" -> 2.25;
            default -> 0.0;
        };
        if (isExtra) {
            base += switch (size) {
                case "4" -> 0.30;
                case "8" -> 0.60;
                case "12" -> 0.90;
                default -> 0.0;
            };
        }
        return base;
    }
}





