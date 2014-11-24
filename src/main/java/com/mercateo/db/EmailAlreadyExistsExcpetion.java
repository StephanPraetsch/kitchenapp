package com.mercateo.db;

import com.mercateo.profile.User;

public class EmailAlreadyExistsExcpetion extends Exception {

    public EmailAlreadyExistsExcpetion(User user) {
        super("user already exists: " + user);
    }

}
