package com.mercateo.kitchenapp.pages.admin;

import org.apache.wicket.model.LoadableDetachableModel;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserDao;

public class UserModel extends LoadableDetachableModel<User> {

    private final UserDao userAccess;

    private final Email email;

    public UserModel(UserDao repo, User user) {
        super(user);
        this.userAccess = repo;
        this.email = user.getEmail();
    }

    @Override
    protected User load() {
        return userAccess.get(email) //
                .orElseThrow(() -> new RuntimeException("missing user: " + email));
    }

}
