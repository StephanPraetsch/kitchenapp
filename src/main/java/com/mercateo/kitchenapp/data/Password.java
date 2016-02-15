package com.mercateo.kitchenapp.data;

import com.mercateo.kitchenapp.util.StringValue;

public class Password extends StringValue {

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
