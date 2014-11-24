package com.mercateo.db;

import java.util.List;

import com.mercateo.profile.User;

public interface UserAccess {

    public boolean existsUser(User user);

    public void addUser(User user) throws UserAlreadyExistsException, EmailAlreadyExistsExcpetion;

    public List<User> listAllUsers();

}
