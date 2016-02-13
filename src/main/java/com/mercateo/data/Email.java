package com.mercateo.data;

import com.mercateo.util.StringBasedValue;

public class Email extends StringBasedValue {

    private Email(String email) {
        super(email);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Email [email=");
        builder.append(asString());
        builder.append("]");
        return builder.toString();
    }

    public static Email of(String email) {
        return new Email(email);
    }

}
