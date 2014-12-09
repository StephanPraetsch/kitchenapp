package com.mercateo.db;

import com.mercateo.profile.Email;

public class EmailDoesNotExistException extends Exception {

    public EmailDoesNotExistException(Email email) {
        super("email does not exists: " + email);
    }

}
