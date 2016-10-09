package com.mercateo.kitchenapp.pages.meals;

enum MealField {

    TITLE("title"), DESCRIPTION("description"), PRICES("prices");

    private final String displayedValue;

    private MealField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    String displayedValue() {
        return displayedValue;
    }

}
