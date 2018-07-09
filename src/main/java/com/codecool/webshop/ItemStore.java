package com.codecool.webshop;

import java.util.ArrayList;
import java.util.List;

public class ItemStore {

    public static List<Item> listOfItems = new ArrayList<>();
    public static List<Item> shoppingCart = new ArrayList<>();

    public static void addItem(Item newItem) {
        listOfItems.add(newItem);
    }

    public static void addToCart(int itemId) {
        Item newItem = null;
        for (Item item : listOfItems) {
            if (item.getId() == itemId) {
                newItem = item;
                break;
            }
        }
        if (newItem != null) shoppingCart.add(newItem);
    }

    public static void removeFromCart(int itemId) {
        Item itemToRemove = null;
        for (Item item : listOfItems) {
            if (item.getId() == itemId) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) shoppingCart.remove(itemToRemove);
    }
}
