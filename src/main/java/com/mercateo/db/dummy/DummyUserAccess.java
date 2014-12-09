package com.mercateo.db.dummy;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.mercateo.db.EmailDoesNotExistException;
import com.mercateo.db.UserAccess;
import com.mercateo.profile.Email;
import com.mercateo.profile.User;
import com.mercateo.sso.UserRole;

public class DummyUserAccess implements UserAccess {

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
    public Set<UserRole> getUserRoles(Email email) throws EmailDoesNotExistException {
        return Collections.emptySet();
    }

}
