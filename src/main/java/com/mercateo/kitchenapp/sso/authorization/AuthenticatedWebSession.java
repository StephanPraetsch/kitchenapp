package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.wicket.Session;
import org.apache.wicket.request.Request;

import com.mercateo.kitchenapp.data.User;

public abstract class AuthenticatedWebSession extends AbstractAuthenticatedWebSession {
    private static final long serialVersionUID = 1L;

    public static AuthenticatedWebSession get() {
        return (AuthenticatedWebSession) Session.get();
    }

    private volatile boolean signedIn;

    public AuthenticatedWebSession(Request request) {
        super(request);
    }

    public final boolean signIn(User user) {

        checkNotNull(user);

        signedIn = authenticate(user);
        if (signedIn) {
            bind();
        }

        return signedIn;

    }

    public abstract boolean authenticate(User user);

    protected final void signIn(boolean value) {
        signedIn = value;
    }

    @Override
    public final boolean isSignedIn() {
        return signedIn;
    }

    public void signOut() {
        signedIn = false;
    }

    @Override
    public void invalidate() {
        signOut();
        super.invalidate();
    }

}