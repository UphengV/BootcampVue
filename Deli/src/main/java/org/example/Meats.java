package org.example;

public class Meats extends Toppings{
    public Meats(String name) {
        super(name);
    }

    @Override
    public double getPrice(String size, Boolean isExtra) {
        double base = switch (size) {
            case "4" -> 1.00;
            case "8" -> 2.00;
            case "12" -> 3.00;
            default -> 0.0;
        };
        if (isExtra) {
            base += switch (size) {
                case "4" -> 0.50;
                case "8" -> 1.00;
                case "12" -> 1.50;
                default -> 0.0;
            };
        }
        return base;
    }
}



