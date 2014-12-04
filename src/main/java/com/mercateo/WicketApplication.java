package com.mercateo;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

import com.mercateo.db.UserAccessFactory;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.sso.BasicAuthenticationSession;
import com.mercateo.sso.SignInPage;

public class WicketApplication extends AuthenticatedWebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
        System.setProperty(UserAccessFactory.USER_ACCESS_FACTORY,
                UserAccessFactoryForMongoDb.class.getCanonicalName());
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return BasicAuthenticationSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return SignInPage.class;
    }

}
