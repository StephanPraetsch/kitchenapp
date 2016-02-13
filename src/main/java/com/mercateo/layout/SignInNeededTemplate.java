package com.mercateo.layout;

import org.apache.wicket.RestartResponseAtInterceptPageException;

import com.mercateo.WicketGuiceHelper;
import com.mercateo.pages.PagesRegistry;
import com.mercateo.sso.authorization.AuthenticatedWebSession;

public abstract class SignInNeededTemplate extends HeaderMiddleFooterTemplate {

    @Override
    protected void onConfigure() {

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            throw new RestartResponseAtInterceptPageException(WicketGuiceHelper.get().getInstance(
                    PagesRegistry.class).getSignInPageClass());
        }

    }

}
