package com.mercateo.kitchenapp.data;

import java.io.Serializable;

import lombok.Data;

@Data(staticConstructor = "of")
public class Email implements Comparable<Email>, Serializable {

    private final String value;

    public String asString() {
        return value;
    }

    @Override
    public int compareTo(Email o) {
        return value.compareTo(o.value);
    }

}
