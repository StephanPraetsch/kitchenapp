package com.mercateo.sso;

import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactoryCache;
import com.mercateo.profile.Password;
import com.mercateo.profile.User;
import com.mercateo.profile.Username;

public class Authenticator {

    public boolean authenticate(Username username, Password password) {
        try {
            User userOfUsername = User.of(username, password);
            return UserAccessFactoryCache.get().create().existsUser(userOfUsername);
        } catch (UserAccessCreationException e) {
            return false;
        }
    }

}
