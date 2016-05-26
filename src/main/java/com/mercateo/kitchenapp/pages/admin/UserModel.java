package com.mercateo.kitchenapp.pages.admin;

import org.apache.wicket.model.LoadableDetachableModel;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserDao;

public class UserModel extends LoadableDetachableModel<User> {

    private final UserDao users;

    private final Email id;

    public UserModel(UserDao users, User user) {
        super(user);
        this.users = users;
        this.id = user.getEmail();
    }

    @Override
    protected User load() {
        return users.get(id) //
                .orElseThrow(() -> new RuntimeException("missing user: " + id));
    }

}
