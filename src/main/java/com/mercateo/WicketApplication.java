package com.mercateo;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.pages.PagesRegistry;
import com.mercateo.sso.authorization.AuthenticatedWebApplication;
import com.mercateo.sso.session.SessionProvider;

public class WicketApplication extends AuthenticatedWebApplication {

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
        configureUserAccess();
    }

    private void configureUserAccess() {
        System.setProperty(UserAccessFactory.USER_ACCESS_FACTORY, UserAccessFactoryForMongoDb.class
                .getCanonicalName());
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return inj.getInstance(PagesRegistry.class).getSignInPageClass();
    }

    @Override
    public Session newSession(Request request, Response response) {
        return inj.getInstance(SessionProvider.class).newSession(request, response);
    }

}
