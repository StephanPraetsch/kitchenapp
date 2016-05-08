package com.mercateo.kitchenapp.data;

public enum Chip {

    RED(3.75f), YELLOW(3.00f), GREEN(2.00f), BLUE(1.00f);

    private final float price;

    private Chip(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

}
