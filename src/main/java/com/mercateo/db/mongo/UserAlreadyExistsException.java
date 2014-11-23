package com.mercateo.db.mongo;

import com.mercateo.sso.User;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(User user) {
        super("user already exists: " + user);
    }

}
