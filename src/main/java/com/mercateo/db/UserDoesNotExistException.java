package com.mercateo.db;

import com.mercateo.profile.User;

public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(User user) {
        super("user does not exists: " + user);
    }

}
