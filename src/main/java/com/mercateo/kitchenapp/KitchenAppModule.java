package com.mercateo.kitchenapp;

import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.sso.authorization.AuthorizationStrategyImpl;
import com.mercateo.kitchenapp.sso.authorization.UnauthorizedListenerImpl;

public class KitchenAppModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(IUnauthorizedComponentInstantiationListener.class) //
                .to(UnauthorizedListenerImpl.class) //
                .in(Scopes.SINGLETON);

        install(new MongoDbModule());

        requireBinding(UserAccess.class);

    }

    @Provides
    @Singleton
    public IAuthorizationStrategy provideAuthorizationStrategy() {
        return new AuthorizationStrategyImpl();
    }

}
