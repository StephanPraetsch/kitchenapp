package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;

import com.mercateo.kitchenapp.pages.PagesRegistry;

public class UnauthorizedListenerImpl implements IUnauthorizedComponentInstantiationListener {

    private final PagesRegistry pages;

    @Inject
    UnauthorizedListenerImpl(PagesRegistry pages) {
        this.pages = checkNotNull(pages);
    }

    @Override
    public final void onUnauthorizedInstantiation(Component component) {
        if (component instanceof Page) {
            if (!AuthenticatedWebSession.get().isSignedIn()) {
                restartResponseAtSignInPage();
            } else {
                onUnauthorizedPage((Page) component);
            }
        } else {
            onUnauthorizedPage((Page) component);
        }
    }

    public void restartResponseAtSignInPage() {
        throw new RestartResponseAtInterceptPageException(pages.getSignInPage());
    }

    protected void onUnauthorizedPage(Page page) {
        /*
         * TODO wieso wird diese exception geloggt?
         */
        throw new UnauthorizedInstantiationException(page.getClass());
    }

}
