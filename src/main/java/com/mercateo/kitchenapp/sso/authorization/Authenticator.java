package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;

public class Authenticator {

    private final UserAccess userAccess;

    @Inject
    Authenticator(UserAccess userAccess) {
        this.userAccess = checkNotNull(userAccess);
    }

    public boolean authenticate(Email email, Password password) {
        User user = User.of(email, password);
        return userAccess.existsUser(user);
    }

}
