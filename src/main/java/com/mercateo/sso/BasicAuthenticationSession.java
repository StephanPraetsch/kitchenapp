package com.mercateo.sso;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import com.mercateo.profile.Password;
import com.mercateo.profile.Username;

public class BasicAuthenticationSession extends AuthenticatedWebSession {

    private final Authenticator authenticator;

    public BasicAuthenticationSession(Request request) {
        super(request);
        this.authenticator = new Authenticator();
    }

    @Override
    public boolean authenticate(String username, String password) {
        return authenticator.authenticate(Username.of(username), Password.of(password));
    }

    @Override
    public Roles getRoles() {
        return null;
    }

}
