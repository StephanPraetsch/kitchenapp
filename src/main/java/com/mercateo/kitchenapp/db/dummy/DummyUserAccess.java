package com.mercateo.kitchenapp.db.dummy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserDao;

public class DummyUserAccess implements UserDao {

    static final DummyUserAccess INSTANCE = new DummyUserAccess();

    private DummyUserAccess() {
        // hide constructor
    }

    @Override
    public boolean existsUser(User user) {
        return false;
    }

    @Override
    public void addUser(User user) {
        // nothing
    }

    @Override
    public List<User> listAllUsers() {
        return Collections.emptyList();
    }

    @Override
    public Optional<User> get(Email email, Password password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> get(Email email) {
        return Optional.empty();
    }

}
