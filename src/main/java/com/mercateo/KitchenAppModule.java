package com.mercateo;

import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.settings.IApplicationSettings;
import org.apache.wicket.settings.ISecuritySettings;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.db.mongo.MongoDbConfiguration;
import com.mercateo.db.mongo.UserAccessFactoryForMongoDb;
import com.mercateo.pages.PagesRegistry;
import com.mercateo.sso.authorization.AnnotationsRoleAuthorizationStrategy;
import com.mercateo.sso.authorization.UnauthorizedListenerImpl;
import com.mercateo.sso.roles.RoleCheckingStrategy;
import com.mercateo.sso.roles.RoleCheckingStrategyImpl;

public class KitchenAppModule extends AbstractModule {

    private final ISecuritySettings securitySettings;

    private final IApplicationSettings applicationSettings;

    public KitchenAppModule(ISecuritySettings securitySettings,
            IApplicationSettings applicationSettings) {
        this.securitySettings = securitySettings;
        this.applicationSettings = applicationSettings;
    }

    @Override
    protected void configure() {

        bind(IUnauthorizedComponentInstantiationListener.class) //
                .to(UnauthorizedListenerImpl.class) //
                .in(Scopes.SINGLETON);

        bind(UserAccessFactory.class) //
                .to(UserAccessFactoryForMongoDb.class) //
                .in(Scopes.SINGLETON);

    }

    @Provides
    @Singleton
    public MongoDbConfiguration provideMongoDbConfig() {
        return new MongoDbConfiguration();
    }

    @Provides
    @Singleton
    public RoleCheckingStrategy provideIRoleCheckingStrategy() {
        return new RoleCheckingStrategyImpl();
    }

    @Provides
    @Singleton
    public IAuthorizationStrategy provideIAuthorizationStrategy(RoleCheckingStrategy roleChecking,
            IUnauthorizedComponentInstantiationListener listener, PagesRegistry pages) {

        IAuthorizationStrategy authorizationStrategy = null;
        // IAuthorizationStrategy authorizationStrategy = new
        // MetaDataRoleAuthorizationStrategy(this);
        // MetaDataRoleAuthorizationStrategy.authorize(AdminPage.class,
        // Roles.ADMIN);

        // authorizationStrategy = new RoleAuthorizationStrategy(roleChecking);
        authorizationStrategy = new AnnotationsRoleAuthorizationStrategy(roleChecking);

        securitySettings.setAuthorizationStrategy(authorizationStrategy);
        // securitySettings.setAuthenticationStrategy(authorizationStrategy);
        securitySettings.setUnauthorizedComponentInstantiationListener(listener);

        applicationSettings.setAccessDeniedPage(pages.getAccessDeniedPage());

        return authorizationStrategy;

    }

}
