package com.mercateo.sso;

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
