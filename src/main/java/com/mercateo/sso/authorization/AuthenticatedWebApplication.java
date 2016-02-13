package com.mercateo.sso.authorization;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

public abstract class AuthenticatedWebApplication extends WebApplication implements
        IUnauthorizedComponentInstantiationListener {

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public final void onUnauthorizedInstantiation(final Component component) {
        if (component instanceof Page) {
            if (!AbstractAuthenticatedWebSession.get().isSignedIn()) {
                restartResponseAtSignInPage();
            } else {
                onUnauthorizedPage((Page) component);
            }
        } else {
            throw new UnauthorizedInstantiationException(component.getClass());
        }
    }

    public void restartResponseAtSignInPage() {
        throw new RestartResponseAtInterceptPageException(getSignInPageClass());
    }

    protected abstract Class<? extends WebPage> getSignInPageClass();

    protected void onUnauthorizedPage(final Page page) {
        throw new UnauthorizedInstantiationException(page.getClass());
    }

}
