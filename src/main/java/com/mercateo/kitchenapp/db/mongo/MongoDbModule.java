package com.mercateo.kitchenapp.db.mongo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.mongo.users.UserAccessMongoDb;
import com.mercateo.kitchenapp.db.mongo.users.UserCollection;

public class MongoDbModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserAccess.class).to(UserAccessMongoDb.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public MongoDbConfiguration provideMongoDbConfig() {
        return new MongoDbConfiguration();
    }

    @Provides
    @Singleton
    public UserCollection provideDbCollection(MongoDbObjectCreator creator) {
        return creator.getUserCollection();
    }

}
