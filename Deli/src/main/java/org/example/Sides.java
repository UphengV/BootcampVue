package org.example;

public abstract class Sides {
    private String name;

    public Sides(String name){
        this.name = name;

    }

    public String getName() {
        return name;
    }
    public abstract double getPrice();
}
