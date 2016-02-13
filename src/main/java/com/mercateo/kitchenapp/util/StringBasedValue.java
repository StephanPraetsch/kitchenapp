package com.mercateo.kitchenapp.util;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class StringBasedValue {

    private final String value;

    public StringBasedValue(String value) {
        this.value = checkNotNull(value);
    }

    public String asString() {
        return value;
    }

}
