package com.mercateo.kitchenapp.sso.session;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.mercateo.kitchenapp.sso.authorization.Authenticator;
import com.mercateo.kitchenapp.sso.authorization.BasicAuthenticationSession;
import com.mercateo.kitchenapp.sso.roles.UserRolesProvider;

public class SessionProvider {

    private final Authenticator authenticator;

    private final UserRolesProvider userRolesProvider;

    @Inject
    SessionProvider(Authenticator authenticator, UserRolesProvider userRolesProvider) {
        this.authenticator = checkNotNull(authenticator);
        this.userRolesProvider = checkNotNull(userRolesProvider);
    }

    public Session newSession(Request request, Response response) {
        checkNotNull(request);
        checkNotNull(response);
        return new BasicAuthenticationSession(request, authenticator, userRolesProvider);
    }

}
