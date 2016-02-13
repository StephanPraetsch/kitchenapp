package com.mercateo.pages.general;

import org.apache.wicket.RestartResponseAtInterceptPageException;

import com.mercateo.WicketGuiceHelper;
import com.mercateo.pages.PagesRegistry;
import com.mercateo.sso.authorization.AuthenticatedWebSession;

public abstract class GenealPageSignInNeeded extends GeneralPage {

    @Override
    protected void onConfigure() {

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            throw new RestartResponseAtInterceptPageException(WicketGuiceHelper.get().getInstance(
                    PagesRegistry.class).getSignInPageClass());
        }

    }

}
