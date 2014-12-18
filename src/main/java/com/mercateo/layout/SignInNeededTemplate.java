package com.mercateo.layout;

import com.mercateo.sso.authorization.AuthenticatedWebApplication;
import com.mercateo.sso.authorization.AuthenticatedWebSession;

public abstract class SignInNeededTemplate extends HeaderMiddleFooterTemplate {

    @Override
    protected void onConfigure() {

        AuthenticatedWebApplication app = (AuthenticatedWebApplication) AuthenticatedWebApplication
                .get();

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            app.restartResponseAtSignInPage();
        }

    }

}
