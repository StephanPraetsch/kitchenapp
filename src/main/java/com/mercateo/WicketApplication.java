package com.mercateo;

import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;

import com.mercateo.db.UserAccessFactory;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.sso.SignInPage;
import com.mercateo.sso.authorization.AbstractAuthenticatedWebSession;
import com.mercateo.sso.authorization.AnnotationsRoleAuthorizationStrategy;
import com.mercateo.sso.authorization.AuthenticatedWebApplication;
import com.mercateo.sso.authorization.BasicAuthenticationSession;

public class WicketApplication extends AuthenticatedWebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        configureUserAccess();
        configureAuthorization();
        configureAuthentication();
    }

    private void configureUserAccess() {
        System.setProperty(UserAccessFactory.USER_ACCESS_FACTORY,
                UserAccessFactoryForMongoDb.class.getCanonicalName());
    }

    private void configureAuthorization() {
        IAuthorizationStrategy authorizationStrategy = null;
        // IAuthorizationStrategy authorizationStrategy = new
        // MetaDataRoleAuthorizationStrategy(this);
        // MetaDataRoleAuthorizationStrategy.authorize(AdminPage.class,
        // Roles.ADMIN);

        authorizationStrategy = new AnnotationsRoleAuthorizationStrategy(this);

        getSecuritySettings().setAuthorizationStrategy(authorizationStrategy);

        getSecuritySettings().setUnauthorizedComponentInstantiationListener(this);

        getApplicationSettings().setAccessDeniedPage(AccessDeniedPage.class);

    }

    private void configureAuthentication() {
        // getSecuritySettings().setAuthenticationStrategy(authorizationStrategy);
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
