package com.mercateo.layout;

import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;

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
