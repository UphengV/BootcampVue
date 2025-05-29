package org.example;

public class RegularTopping extends Toppings {

    public RegularTopping(String name) {
        super(name);
    }

    @Override
    public double getPrice(String size, Boolean isExtra) {
        return 0;
    }
}
