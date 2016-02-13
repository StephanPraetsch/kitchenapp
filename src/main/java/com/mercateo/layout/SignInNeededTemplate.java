package com.mercateo.layout;

import org.apache.wicket.protocol.http.WebApplication;

import com.mercateo.KitchenApp;
import com.mercateo.sso.authorization.AuthenticatedWebSession;

public abstract class SignInNeededTemplate extends HeaderMiddleFooterTemplate {

    @Override
    protected void onConfigure() {

        KitchenApp app = (KitchenApp) WebApplication.get();

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            app.restartResponseAtSignInPage();
        }

    }

}
