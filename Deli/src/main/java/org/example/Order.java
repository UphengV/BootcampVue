package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Sides> sides = new ArrayList<>();

    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }

    public void addSide(Sides s) {
        sides.add(s);
    }

    public double getTotal() {
        double total = 0;
        for (Sandwich s : sandwiches) total += s.calculatePrice();
        for (Sides s : sides) total += s.getPrice();
        return total;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder("Order Summary:\n");
        for (Sandwich s : sandwiches) {
            sb.append(s.getDescription()).append("\nPrice: $").append(s.calculatePrice()).append("\n");
        }
        for (Sides s : sides) {
            sb.append("Side: ").append(s.getName()).append(" - $").append(s.getPrice()).append("\n");
        }
        sb.append("Total: $").append(getTotal()).append("\n");
        return sb.toString();
    }
}
