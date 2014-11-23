package com.mercateo.db.mongo;

import com.mercateo.sso.User;

public interface UserAccess {

    public boolean userExists(User user);

    public void addUser(User user) throws UserAlreadyExistsException;

}
