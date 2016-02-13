package com.mercateo;

import org.apache.wicket.markup.html.WebPage;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.pages.PagesRegistry;
import com.mercateo.sso.authorization.AbstractAuthenticatedWebSession;
import com.mercateo.sso.authorization.AuthenticatedWebApplication;
import com.mercateo.sso.authorization.BasicAuthenticationSession;

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
        configureUserAccess();
    }

    private void configureUserAccess() {
        System.setProperty(UserAccessFactory.USER_ACCESS_FACTORY, UserAccessFactoryForMongoDb.class
                .getCanonicalName());
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return BasicAuthenticationSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return inj.getInstance(PagesRegistry.class).getSignInPageClass();
    }

}
