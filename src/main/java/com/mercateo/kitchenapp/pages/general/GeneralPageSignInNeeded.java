package com.mercateo.kitchenapp.pages.general;

import javax.inject.Inject;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;

public abstract class GeneralPageSignInNeeded extends GeneralPage {

    private static final long serialVersionUID = 1L;

    @Inject
    private PagesRegistry pagesRegistry;

    public GeneralPageSignInNeeded(PageParameters params) {
        super(params);
    }

    @Override
    protected void onConfigure() {

        if (!AuthenticatedWebSession.get().isSignedIn()) {
            throw new RestartResponseAtInterceptPageException(pagesRegistry.getSignInPage());
        }

    }

}
