package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Password;

public abstract class AuthenticatedWebSession extends WebSession {

    private static final long serialVersionUID = 1L;

    public static AuthenticatedWebSession get() {
        return (AuthenticatedWebSession) Session.get();
    }

    private volatile boolean signedIn;

    public AuthenticatedWebSession(Request request) {
        super(checkNotNull(request));
    }

    public final boolean signIn(Email email, Password password) {

        checkNotNull(email);
        checkNotNull(password);

        signedIn = authenticate(email, password);
        if (signedIn) {
            bind();
        }

        return signedIn;

    }

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

    public abstract boolean authenticate(Email email, Password password);

}
