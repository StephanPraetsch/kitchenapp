package com.mercateo.profile;

import com.mercateo.util.StringBasedValue;

public class Username extends StringBasedValue {

    private Username(String name) {
        super(name);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Username [name=");
        builder.append(asString());
        builder.append("]");
        return builder.toString();
    }

    public static Username of(String username) {
        return new Username(username);
    }

}
