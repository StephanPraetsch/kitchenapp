package com.mercateo.kitchenapp.pages.general;

import org.apache.wicket.RestartResponseAtInterceptPageException;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;

public abstract class GenealPageSignInNeeded extends GeneralPage {

    @Override
    protected void onConfigure() {

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            throw new RestartResponseAtInterceptPageException(WicketGuiceHelper.get().getInstance(
                    PagesRegistry.class).getSignInPageClass());
        }

    }

}
