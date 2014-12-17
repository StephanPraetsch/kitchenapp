package com.mercateo.sso;

import com.mercateo.util.StringBasedValue;

public class UserRole extends StringBasedValue {

    // TODO for annotations
    public static final String ADMIN = "ADMIN";

    // TODO for annotations
    public static final String EDITOR = "EDITOR";

    public UserRole(String value) {
        super(value);
    }

}
