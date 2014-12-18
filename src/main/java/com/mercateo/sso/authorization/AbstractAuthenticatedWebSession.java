package com.mercateo.sso.authorization;

import java.util.Set;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public abstract class AbstractAuthenticatedWebSession extends WebSession {

    private static final long serialVersionUID = 1L;

    public static AbstractAuthenticatedWebSession get() {
        return (AbstractAuthenticatedWebSession) Session.get();
    }

    public AbstractAuthenticatedWebSession(final Request request) {
        super(request);
    }

    public abstract Set<UserRole> getRoles();

    public abstract boolean isSignedIn();

}
