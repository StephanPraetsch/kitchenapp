package com.mercateo.kitchenapp.pages.admin;

enum UserField {

    EMAIL("email"), PASSWORD("password");

    private final String displayedValue;

    private UserField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    String displayedValue() {
        return displayedValue;
    }

}
