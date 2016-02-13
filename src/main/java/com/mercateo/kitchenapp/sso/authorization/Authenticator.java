package com.mercateo.kitchenapp.sso.authorization;

import javax.inject.Inject;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccessCreationException;
import com.mercateo.kitchenapp.db.UserAccessFactory;

public class Authenticator {

    private final UserAccessFactory userAccessFactory;

    @Inject
    Authenticator(UserAccessFactory userAccessFactory) {
        this.userAccessFactory = userAccessFactory;
    }

    public boolean authenticate(Email email, Password password) {
        try {
            User user = User.of(email, password);
            return userAccessFactory.create().existsUser(user);
        } catch (UserAccessCreationException e) {
            return false;
        }
    }

}
