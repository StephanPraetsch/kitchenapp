package com.mercateo.kitchenapp.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

public abstract class StringValue implements Serializable, Comparable<StringValue> {

    private final String value;

    public StringValue(String value) {
        this.value = checkNotNull(value);
    }

    public String asString() {
        return value;
    }

    @Override
    public int compareTo(StringValue o) {
        return value.compareTo(o.value);
    }

}
