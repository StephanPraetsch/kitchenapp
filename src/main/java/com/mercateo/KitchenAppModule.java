package com.mercateo;

import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.settings.IApplicationSettings;
import org.apache.wicket.settings.ISecuritySettings;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.mercateo.sso.authorization.AnnotationsRoleAuthorizationStrategy;
import com.mercateo.sso.authorization.IRoleCheckingStrategy;
import com.mercateo.sso.authorization.UnauthorizedListenerImpl;
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

    }

    @Provides
    @Singleton
    public IRoleCheckingStrategy provideIRoleCheckingStrategy() {
        return new RoleCheckingStrategyImpl();
    }

    @Provides
    @Singleton
    public IAuthorizationStrategy provideIAuthorizationStrategy(IRoleCheckingStrategy roleChecking,
            IUnauthorizedComponentInstantiationListener listener) {

        IAuthorizationStrategy authorizationStrategy = null;
        // IAuthorizationStrategy authorizationStrategy = new
        // MetaDataRoleAuthorizationStrategy(this);
        // MetaDataRoleAuthorizationStrategy.authorize(AdminPage.class,
        // Roles.ADMIN);

        // authorizationStrategy = new RoleAuthorizationStrategy(roleChecking);
        authorizationStrategy = new AnnotationsRoleAuthorizationStrategy(roleChecking);

        securitySettings.setAuthorizationStrategy(authorizationStrategy);

        securitySettings.setUnauthorizedComponentInstantiationListener(listener);

        applicationSettings.setAccessDeniedPage(AccessDeniedPage.class);

        return authorizationStrategy;

    }

}
