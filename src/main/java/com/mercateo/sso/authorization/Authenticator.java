package com.mercateo.sso.authorization;

import javax.inject.Inject;

import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.profile.Email;
import com.mercateo.profile.Password;
import com.mercateo.profile.User;

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
