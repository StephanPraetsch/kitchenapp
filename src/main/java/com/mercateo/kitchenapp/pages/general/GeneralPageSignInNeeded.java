package com.mercateo.kitchenapp.pages.general;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;

public abstract class GeneralPageSignInNeeded extends GeneralPage {

    private static final long serialVersionUID = 1L;

    public GeneralPageSignInNeeded(PageParameters params) {
        super(params);
    }

    @Override
    protected void onConfigure() {

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            throw new RestartResponseAtInterceptPageException(new PagesRegistry().getSignInPage());
        }

    }

}
