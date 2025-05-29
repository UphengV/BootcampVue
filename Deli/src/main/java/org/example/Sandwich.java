package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String bread;
    private String size;
    private boolean toasted;
    private List<Toppings> toppings = new ArrayList<>();

    public Sandwich(String bread, String size, boolean toasted) {
        this.bread = bread;
        this.size = size;
        this.toasted = toasted;
    }

    public void addTopping(Toppings topping) {
        toppings.add(topping);
    }

    public double calculatePrice() {
        double base = switch (size) {
            case "4" -> 5.50;
            case "8" -> 7.00;
            case "12" -> 8.50;
            default -> 0.0;
        };
        for (Toppings t : toppings) base += t.getPrice(size, false);
        return base;
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder(size + "\" " + bread + (toasted ? " (Toasted)" : ""));
        for (Toppings t : toppings) {
            sb.append("\n  - ").append(t.getName());
        }
        return sb.toString();
    }
}
