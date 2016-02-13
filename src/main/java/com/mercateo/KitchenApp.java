package com.mercateo;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mercateo.pages.PagesRegistry;
import com.mercateo.sso.session.SessionProvider;

public class KitchenApp extends WebApplication {

    private Injector inj;

    @Override
    public Class<? extends WebPage> getHomePage() {
        return inj.getInstance(PagesRegistry.class).getHomePage();
    }

    @Override
    public void init() {
        this.inj = Guice.createInjector(new KitchenAppModule(getSecuritySettings(),
                getApplicationSettings()));
        WicketGuiceHelper.set(inj);
    }

    @Override
    public Session newSession(Request request, Response response) {
        return inj.getInstance(SessionProvider.class).newSession(request, response);
    }

    public void restartResponseAtSignInPage() {
        throw new RestartResponseAtInterceptPageException(inj.getInstance(PagesRegistry.class)
                .getSignInPageClass());
    }

}
