package com.mercateo.sso.authorization;

import java.util.Set;

import org.apache.wicket.request.Request;

import com.mercateo.profile.Email;
import com.mercateo.profile.Password;

public class BasicAuthenticationSession extends AuthenticatedWebSession {

    private final Authenticator authenticator;

    private final UserRolesProvider userRolesProvider;

    private Email email;

    private Password password;

    public BasicAuthenticationSession(Request request) {
        super(request);
        this.authenticator = new Authenticator();
        this.userRolesProvider = new UserRolesProvider();
    }

    @Override
    public boolean authenticate(String email, String password) {
        setEmail(email);
        setPassword(password);
        return authenticate();
    }

    private void setPassword(String password) {
        this.password = Password.of(password);
    }

    private void setEmail(String email) {
        this.email = Email.of(email);
    }

    private boolean authenticate() {
        return authenticator.authenticate(email, password);
    }

    @Override
    public Set<UserRole> getRoles() {
        if (isSignedIn()) {
            return userRolesProvider.provide(email);
        } else {
            return null;
        }
    }

    @Override
    public void signOut() {
        super.signOut();
        unsetEmail();
        unsetPassword();
    }

    private void unsetEmail() {
        this.email = null;
    }

    private void unsetPassword() {
        this.password = null;
    }

}
