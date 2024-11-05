package org.craftedsw.tripservicekata.sprouts;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCard {

    private List<Item> items = new ArrayList<>();

    public ShoppingCard(List<Item> items) {
        this.items = items;
    }

    /*
        1. New feature add functionality to calculate discount price on eligible items
        2. return total price after discount
     */

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for(Item item : items) {
            totalPrice += item.price();
        }
        return totalPrice;
    }
}


record Item(String name, double price) {}

// V1
//public double calculateTotalPrice() {
//    double totalPrice = 0;
//    for(Item item : items) {
//        totalPrice += item.price();
//    }
//
//    double discountPrice = 0;
//    for(Item item : items) {
//        if(item.price() >= 1000) {
//            totalPrice += item.price() * 0.1;
//        }
//    }
//    return totalPrice + discountPrice;
//}


// V2
//public double calculateTotalPrice() {
//    double totalPrice = 0;
//    for(Item item : items) {
//        totalPrice += item.price();
//    }
//    return totalPrice - calculateDiscountPrice(items);
//}
//
//private double calculateDiscountPrice(List<Item> items) {
//    double discountPrice = 0;
//    for(Item item : items) {
//        if(item.price() >= 1000) {
//            discountPrice += item.price() * 0.1;
//        }
//    }
//    return discountPrice;
//}

// v3
//public double calculateTotalPrice() {
//    return calculateTotalPriceWithoutDiscount() - calculateDiscountPrice(items);
//}
//
//public double calculateTotalPriceWithoutDiscount() {
//    double totalPrice = 0;
//    for(Item item : items) {
//        totalPrice += item.price();
//    }
//    return totalPrice;
//}
//
//private double calculateDiscountPrice(List<Item> items) {
//    double discountPrice = 0;
//    for(Item item : items) {
//        if(item.price() >= 1000) {
//            discountPrice += item.price() * 0.1;
//        }
//    }
//    return discountPrice;
//}
//
//}
