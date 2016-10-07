package com.mercateo.kitchenapp.pages.meals;

public enum MealField {

    TITLE("title"), DESCRIPTION("description"), PRICES("prices");

    private final String displayedValue;

    private MealField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String displayedValue() {
        return displayedValue;
    }

}
