package com.mercateo.kitchenapp.data;

import lombok.Data;

@Data(staticConstructor = "of")
public class Email implements Comparable<Email> {

    private final String value;

    public String asString() {
        return value;
    }

    @Override
    public int compareTo(Email o) {
        return value.compareTo(o.value);
    }

}
