package com.mercateo.kitchenapp.db;

import com.mercateo.kitchenapp.data.User;

public class AlreadyExistsExcpetion extends Exception {

    public AlreadyExistsExcpetion(User user) {
        super("user already exists: " + user);
    }

}
