package com.mercateo.kitchenapp.db;

import com.mercateo.kitchenapp.data.User;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(User user) {
        super("user does not exists: " + user);
    }

}
