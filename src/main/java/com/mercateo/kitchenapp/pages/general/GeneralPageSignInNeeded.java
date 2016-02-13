package com.mercateo.kitchenapp.pages.general;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;

public abstract class GeneralPageSignInNeeded extends GeneralPage {

    public GeneralPageSignInNeeded(PageParameters params) {
        super(params);
    }

    @Override
    protected void onConfigure() {

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            throw new RestartResponseAtInterceptPageException(WicketGuiceHelper.get().getInstance(
                    PagesRegistry.class).getSignInPage());
        }

    }

}
