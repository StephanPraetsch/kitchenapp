package com.mercateo.kitchenapp.util;

public abstract class StringBasedValue {

    private final String value;

    public StringBasedValue(String value) {
        this.value = value;
    }

    public String asString() {
        return value;
    }

}
