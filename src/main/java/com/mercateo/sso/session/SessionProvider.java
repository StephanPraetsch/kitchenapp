package com.mercateo.sso.session;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.mercateo.sso.authorization.Authenticator;
import com.mercateo.sso.authorization.BasicAuthenticationSession;
import com.mercateo.sso.authorization.UserRolesProvider;

public class SessionProvider {

    private final Authenticator authenticator;

    private final UserRolesProvider userRolesProvider;

    @Inject
    SessionProvider(Authenticator authenticator, UserRolesProvider userRolesProvider) {
        this.authenticator = authenticator;
        this.userRolesProvider = userRolesProvider;
    }

    public Session newSession(Request request, Response response) {
        return new BasicAuthenticationSession(request, authenticator, userRolesProvider);
    }

}
