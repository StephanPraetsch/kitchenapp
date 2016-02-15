package com.mercateo.kitchenapp;

import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.session.SessionProvider;

public class KitchenApp extends WebApplication {

    private Injector inj;

    @Override
    public Class<? extends WebPage> getHomePage() {
        return inj.getInstance(PagesRegistry.class).getHomePage();
    }

    @Override
    public void init() {
        this.inj = createInjector();
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, inj));
        WicketGuiceHelper.set(inj);
        config();
    }

    @VisibleForTesting
    public Injector createInjector() {
        return Guice.createInjector(new KitchenAppModule());
    }

    private void config() {

        getSecuritySettings().setAuthorizationStrategy(inj.getInstance(
                IAuthorizationStrategy.class));

        // securitySettings.setAuthenticationStrategy(authorizationStrategy);

        getSecuritySettings().setUnauthorizedComponentInstantiationListener(inj.getInstance(
                IUnauthorizedComponentInstantiationListener.class));

        getApplicationSettings().setAccessDeniedPage(inj.getInstance(PagesRegistry.class)
                .getAccessDeniedPage());

    }

    @Override
    public Session newSession(Request request, Response response) {
        return inj.getInstance(SessionProvider.class).newSession(request, response);
    }

}
