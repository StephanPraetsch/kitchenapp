package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.sso.roles.UserRole;
import com.mercateo.kitchenapp.sso.roles.UserRolesProvider;

public class UserWebSession extends AuthenticatedWebSession {

    public static UserWebSession get() {
        return (UserWebSession) Session.get();
    }

    private final Authenticator authenticator;

    private final UserRolesProvider userRolesProvider;

    private User user;

    public UserWebSession(Request request, Authenticator authenticator,
            UserRolesProvider userRolesProvider) {
        super(request);
        this.authenticator = checkNotNull(authenticator);
        this.userRolesProvider = checkNotNull(userRolesProvider);
    }

    @Override
    public boolean authenticate(User user) {
        this.user = checkNotNull(user);
        return authenticator.authenticate(user);
    }

    public Set<UserRole> getRoles() {
        if (isSignedIn()) {
            return userRolesProvider.provide(user);
        } else {
            return Collections.emptySet();
        }
    }

    public User getUser() {
        return user;
    }

}
