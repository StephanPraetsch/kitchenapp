package com.mercateo.db;

import com.mercateo.profile.User;

public class DuplicateUserException extends Exception {

    public DuplicateUserException(User user) {
        super("duplicate user found: " + user);
    }

}
