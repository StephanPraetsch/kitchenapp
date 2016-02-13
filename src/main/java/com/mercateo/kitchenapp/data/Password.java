package com.mercateo.kitchenapp.data;

import com.mercateo.kitchenapp.util.StringBasedValue;

public class Password extends StringBasedValue {

    private Password(String password) {
        super(password);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Password [password=");
        builder.append(asString());
        builder.append("]");
        return builder.toString();
    }

    public static Password of(String password) {
        return new Password(password);
    }

}
