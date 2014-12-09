package com.mercateo.sso;

import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactoryCache;
import com.mercateo.profile.Email;
import com.mercateo.profile.Password;
import com.mercateo.profile.User;

public class Authenticator {

    public boolean authenticate(Email email, Password password) {
        try {
            User user = User.of(email, password);
            return UserAccessFactoryCache.get().create().existsUser(user);
        } catch (UserAccessCreationException e) {
            return false;
        }
    }

}
