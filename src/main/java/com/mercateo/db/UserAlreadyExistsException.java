package com.mercateo.db;

import com.mercateo.profile.User;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(User user) {
        super("user already exists: " + user);
    }

}
