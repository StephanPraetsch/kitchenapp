package com.mercateo.kitchenapp.pages.admin;

public enum UserField {

    EMAIL("email"), PASSWORD("password");

    private final String displayedValue;

    private UserField(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String displayedValue() {
        return displayedValue;
    }

}
