package com.mercateo.db;

import com.mercateo.sso.User;

public class EmailAlreadyExistsExcpetion extends Exception {

    public EmailAlreadyExistsExcpetion(User user) {
        super("user already exists: " + user);
    }

}
