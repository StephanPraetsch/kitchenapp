package com.mercateo.kitchenapp.db;

import com.mercateo.kitchenapp.data.User;

public class EmailAlreadyExistsExcpetion extends Exception {

    public EmailAlreadyExistsExcpetion(User user) {
        super("user already exists: " + user);
    }

}
