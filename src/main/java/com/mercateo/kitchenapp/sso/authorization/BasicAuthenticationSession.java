package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;
import java.util.Set;

import org.apache.wicket.request.Request;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;
import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.sso.roles.UserRole;
import com.mercateo.kitchenapp.sso.roles.UserRolesProvider;

public class BasicAuthenticationSession extends AuthenticatedWebSession {

    private final Authenticator authenticator;

    private final UserRolesProvider userRolesProvider;

    private User user;

    public BasicAuthenticationSession(Request request, Authenticator authenticator,
            UserRolesProvider userRolesProvider) {
        super(request);
        this.authenticator = checkNotNull(authenticator);
        this.userRolesProvider = checkNotNull(userRolesProvider);
    }

    @Override
    public boolean authenticate(String email, String password) {
        this.user = User.of(Email.of(email), Password.of(password));
        return authenticator.authenticate(user);
    }

    @Override
    public Set<UserRole> getRoles() {
        if (isSignedIn()) {
            return userRolesProvider.provide(user);
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public void signOut() {
        super.signOut();
    }

}
