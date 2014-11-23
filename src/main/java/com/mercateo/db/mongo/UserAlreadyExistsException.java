package com.mercateo.db.mongo;

import com.mercateo.sso.User;

public class UserAlreadyExistsException extends UserException {

    public UserAlreadyExistsException(User user) {
        super("user already exists: " + user);
    }

}
