package com.codecool.webshop;

public class Item {

    private static int NEXT_ID = 1;
    private int id;
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.id = NEXT_ID;
        NEXT_ID++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
