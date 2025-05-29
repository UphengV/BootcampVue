package org.example;

public class Chips extends Sides{
    public Chips(String name){
        super(name);
    }

    @Override
    public double getPrice() {
        return 1.50;
    }
}
