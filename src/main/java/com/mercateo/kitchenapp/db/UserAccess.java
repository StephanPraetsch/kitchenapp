package com.mercateo.kitchenapp.db;

import java.util.List;
import java.util.Optional;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;

public interface UserAccess {

    public boolean existsUser(User user);

    public void addUser(User user) throws AlreadyExistsExcpetion;

    public List<User> listAllUsers();

    public Optional<User> get(Email email, Password password);

    public Optional<User> get(Email email);

}
